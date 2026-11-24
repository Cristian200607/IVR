package utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.text.Normalizer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

/**
 * Genera el reporte Word de cada escenario con la plantilla estándar de los proyectos
 * (ruta/PlantillaInforme.docx, la misma de Portal-Pagos_Empresas): datos de la prueba,
 * conclusión, una evidencia por página, la transcripción del audio y, si falló, el detalle del
 * error. Se genera siempre, pase o falle la prueba.
 */
public class WordAppium {

  private static final Logger LOGGER = Logger.getLogger(WordAppium.class.getName());
  private static final String PLANTILLA =
      System.getProperty("user.dir") + File.separator + "ruta" + File.separator + "PlantillaInforme.docx";
  private static final String CAPTURAS_DIR = "Capturas/";
  private static final String REPORTES_DIR =
      System.getProperty("user.dir") + File.separator + "reportes";
  private static final DateTimeFormatter FORMATO_ARCHIVO =
      DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
  private static final DateTimeFormatter FORMATO_FECHA =
      DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
  private static final double ANCHO_MAXIMO_IMAGEN = 440.0;
  private static final double ALTO_MAXIMO_IMAGEN = 560.0;
  private static final int LARGO_MAXIMO_ERROR = 4000;

  public static void generarReporte(
      String nombreEscenario,
      String[] pasosEjecutados,
      String linea,
      String duracionFormato,
      String pasoFallido,
      String estadoFinal,
      String transcripcion,
      String textoEsperado) {

    boolean fallo = "FAILED".equalsIgnoreCase(estadoFinal);
    File[] capturas = new File(CAPTURAS_DIR).listFiles();
    if (capturas == null) {
      capturas = new File[0];
    }

    new File(REPORTES_DIR).mkdirs();
    File destino =
        new File(
            REPORTES_DIR,
            "Reporte_"
                + normalizarNombre(nombreEscenario)
                + "_"
                + (fallo ? "FALLIDO" : "EXITOSO")
                + "_"
                + LocalDateTime.now().format(FORMATO_ARCHIVO)
                + ".docx");

    try (FileInputStream plantilla = new FileInputStream(PLANTILLA);
        XWPFDocument documento = new XWPFDocument(plantilla)) {

      String fecha =
          EstadoPrueba.horaInicio.isBlank()
              ? FORMATO_FECHA.format(LocalDateTime.now())
              : EstadoPrueba.horaInicio;
      String conclusion =
          generarConclusion(nombreEscenario, linea, pasosEjecutados.length, pasoFallido, fallo);

      reemplazarMarcador(documento, "{{ESCENARIO}}", nombreEscenario);
      reemplazarMarcador(documento, "{{FECHA}}", fecha);
      reemplazarMarcador(documento, "{{CONCLUSION}}", conclusion);
      reemplazarMarcador(documento, "{{LINEA}}", linea);
      reemplazarMarcador(documento, "{{LINEA_PROBADA}}", linea);
      reemplazarMarcador(documento, "{{HORA_INICIO}}", fecha);
      reemplazarMarcador(documento, "{{DURACION}}", duracionFormato);

      agregarEvidencias(documento, pasosEjecutados, capturas);
      agregarTranscripcion(documento, transcripcion, textoEsperado);
      if (fallo) {
        agregarDetalleError(documento, pasoFallido);
      }

      try (FileOutputStream salida = new FileOutputStream(destino)) {
        documento.write(salida);
      }
      System.out.println("REPORTE_WORD_GENERADO=" + destino.getAbsolutePath());
    } catch (IOException | InvalidFormatException | RuntimeException e) {
      // Se conservan las capturas para no perder la evidencia si el Word no se pudo generar
      LOGGER.severe("Error generando el reporte Word: " + e.getMessage());
      return;
    }

    eliminarCapturas(capturas);
  }

  private static String generarConclusion(
      String escenario, String linea, int evidencias, String pasoFallido, boolean fallo) {
    StringBuilder texto =
        new StringBuilder()
            .append("Se ejecuta la prueba \"")
            .append(escenario)
            .append("\" llamando desde la línea ")
            .append(linea)
            .append(". Se registraron ")
            .append(evidencias)
            .append(" evidencias. ");
    if (fallo) {
      texto.append("La prueba finalizó con errores en el paso: ").append(pasoFallido).append(". ");
      String error = primeraLinea(EstadoPrueba.error);
      if (!error.isBlank()) {
        texto.append("Error: ").append(error).append(" ");
      }
      texto.append("(ver el detalle del error al final del documento).");
    } else {
      texto.append("Todas las opciones se marcaron correctamente");
      if (!EstadoPrueba.coincidencia.isBlank()) {
        texto
            .append(" y la transcripción del audio coincide con el texto esperado (")
            .append(EstadoPrueba.coincidencia)
            .append(")");
      }
      texto.append(". La prueba finalizó exitosamente.");
    }
    return texto.toString();
  }

  private static void agregarEvidencias(XWPFDocument documento, String[] pasos, File[] capturas)
      throws IOException, InvalidFormatException {
    agregarTituloSeccion(documento, "EVIDENCIAS", true, null);

    if (pasos.length == 0) {
      agregarParrafo(documento, "(No se registraron evidencias en esta ejecución)", false, 11);
      return;
    }

    for (int indice = 0; indice < pasos.length; indice++) {
      String paso = pasos[indice];
      XWPFParagraph titulo = documento.createParagraph();
      if (indice > 0) {
        titulo.setPageBreak(true);
      }
      titulo.setSpacingAfter(120);
      XWPFRun tituloRun = titulo.createRun();
      tituloRun.setBold(true);
      tituloRun.setFontSize(11);
      // Los pasos llegan como "N. descripción"
      tituloRun.setText("Evidencia " + paso.replaceFirst("^(\\d+)\\.\\s*", "$1 - "));

      File imagen = buscarImagenDePaso(paso, capturas);
      if (imagen == null) {
        agregarParrafo(documento, "(No se pudo tomar la captura de este paso)", false, 10);
      } else {
        agregarImagen(documento, imagen);
      }
    }
  }

  private static void agregarImagen(XWPFDocument documento, File archivo)
      throws IOException, InvalidFormatException {
    BufferedImage imagen = ImageIO.read(archivo);
    if (imagen == null) {
      agregarParrafo(
          documento, "(La captura no es una imagen válida: " + archivo.getName() + ")", false, 10);
      return;
    }
    double escala =
        Math.min(
            ANCHO_MAXIMO_IMAGEN / imagen.getWidth(), ALTO_MAXIMO_IMAGEN / imagen.getHeight());
    XWPFParagraph parrafo = documento.createParagraph();
    parrafo.setAlignment(ParagraphAlignment.CENTER);
    try (InputStream entrada = Files.newInputStream(archivo.toPath())) {
      parrafo
          .createRun()
          .addPicture(
              entrada,
              Document.PICTURE_TYPE_PNG,
              archivo.getName(),
              Units.toEMU(imagen.getWidth() * escala),
              Units.toEMU(imagen.getHeight() * escala));
    }
  }

  private static void agregarTranscripcion(
      XWPFDocument documento, String transcripcion, String esperado) {
    agregarTituloSeccion(documento, "TRANSCRIPCIÓN DEL AUDIO", true, null);
    agregarParrafo(documento, "Texto transcrito de la llamada:", true, 12);
    agregarParrafo(
        documento, vacioSiNulo(transcripcion, "(No se obtuvo transcripción del audio)"), false, 11);
    agregarParrafo(documento, "Texto esperado:", true, 12);
    agregarParrafo(
        documento, vacioSiNulo(esperado, "(No se llegó a validar la transcripción)"), false, 11);
    agregarParrafo(documento, "Porcentaje de coincidencia:", true, 12);
    agregarParrafo(documento, vacioSiNulo(EstadoPrueba.coincidencia, "(No calculado)"), false, 11);
  }

  private static void agregarDetalleError(XWPFDocument documento, String pasoFallido) {
    agregarTituloSeccion(documento, "DETALLE DEL ERROR", false, "C00000");
    agregarParrafo(documento, "Paso en el que falló:", true, 12);
    agregarParrafo(documento, vacioSiNulo(pasoFallido, "(no identificado)"), false, 11);
    agregarParrafo(documento, "Mensaje del error:", true, 12);
    String error = vacioSiNulo(EstadoPrueba.error, "(sin detalle del error)");
    if (error.length() > LARGO_MAXIMO_ERROR) {
      error = error.substring(0, LARGO_MAXIMO_ERROR) + "...";
    }
    agregarParrafo(documento, error, false, 10);
  }

  private static void agregarTituloSeccion(
      XWPFDocument documento, String titulo, boolean nuevaPagina, String color) {
    XWPFParagraph encabezado = documento.createParagraph();
    encabezado.setPageBreak(nuevaPagina);
    if (!nuevaPagina) {
      encabezado.setSpacingBefore(360);
    }
    encabezado.setSpacingAfter(180);
    XWPFRun run = encabezado.createRun();
    run.setBold(true);
    run.setFontSize(16);
    if (color != null) {
      run.setColor(color);
    }
    run.setText(titulo);
  }

  /** Agrega un párrafo respetando los saltos de línea del texto. */
  private static void agregarParrafo(
      XWPFDocument documento, String texto, boolean negrita, int tamano) {
    XWPFParagraph parrafo = documento.createParagraph();
    parrafo.setAlignment(ParagraphAlignment.BOTH);
    parrafo.setSpacingAfter(120);
    XWPFRun run = parrafo.createRun();
    run.setBold(negrita);
    run.setFontSize(tamano);
    String[] lineas = texto.split("\\r?\\n");
    for (int i = 0; i < lineas.length; i++) {
      if (i > 0) {
        run.addBreak();
      }
      run.setText(lineas[i]);
    }
  }

  private static File buscarImagenDePaso(String paso, File[] capturas) {
    // Prefijo exacto "<paso>_<timestamp>.png": con contains() el paso "1. X" tomaba la imagen de "11. X"
    String prefijo = CapturaDePantallaMovil.normalizar(paso) + "_";
    File encontrada = null;
    for (File img : capturas) {
      String nombre = img.getName().toLowerCase();
      if (nombre.endsWith(".png")
          && nombre.startsWith(prefijo)
          && (encontrada == null || nombre.compareTo(encontrada.getName().toLowerCase()) > 0)) {
        encontrada = img;
      }
    }
    return encontrada;
  }

  private static void eliminarCapturas(File[] capturas) {
    for (File captura : capturas) {
      try {
        Files.deleteIfExists(captura.toPath());
      } catch (IOException e) {
        LOGGER.warning("No se pudo eliminar la captura: " + captura.getName());
      }
    }
  }

  // ---- Reemplazo de marcadores (aunque Word los parta en varios runs) ----

  private static void reemplazarMarcador(XWPFDocument documento, String marcador, String valor) {
    String texto = valor == null ? "" : valor;
    for (XWPFParagraph parrafo : documento.getParagraphs()) {
      reemplazarEnParrafo(parrafo, marcador, texto);
    }
    for (XWPFTable tabla : documento.getTables()) {
      reemplazarEnTabla(tabla, marcador, texto);
    }
  }

  private static void reemplazarEnTabla(XWPFTable tabla, String marcador, String valor) {
    for (XWPFTableRow fila : tabla.getRows()) {
      for (XWPFTableCell celda : fila.getTableCells()) {
        for (XWPFParagraph parrafo : celda.getParagraphs()) {
          reemplazarEnParrafo(parrafo, marcador, valor);
        }
        for (XWPFTable anidada : celda.getTables()) {
          reemplazarEnTabla(anidada, marcador, valor);
        }
      }
    }
  }

  private static void reemplazarEnParrafo(XWPFParagraph parrafo, String marcador, String valor) {
    while (true) {
      List<XWPFRun> runs = parrafo.getRuns();
      StringBuilder completo = new StringBuilder();
      for (XWPFRun run : runs) {
        completo.append(textoRun(run));
      }
      int inicioMarcador = completo.indexOf(marcador);
      if (inicioMarcador < 0) {
        return;
      }
      int finMarcador = inicioMarcador + marcador.length();
      int posicion = 0;
      boolean insertado = false;
      for (XWPFRun run : runs) {
        String textoRun = textoRun(run);
        int inicioRun = posicion;
        int finRun = inicioRun + textoRun.length();
        if (finRun > inicioMarcador && inicioRun < finMarcador) {
          String prefijo = textoRun.substring(0, Math.max(0, inicioMarcador - inicioRun));
          String sufijo = textoRun.substring(Math.min(textoRun.length(), finMarcador - inicioRun));
          if (!insertado) {
            establecerTexto(run, prefijo + valor + (finMarcador <= finRun ? sufijo : ""));
            insertado = true;
          } else {
            establecerTexto(run, finMarcador <= finRun ? sufijo : "");
          }
        }
        posicion = finRun;
      }
    }
  }

  private static String textoRun(XWPFRun run) {
    StringBuilder texto = new StringBuilder();
    for (int i = 0; i < run.getCTR().sizeOfTArray(); i++) {
      String fragmento = run.getCTR().getTArray(i).getStringValue();
      if (fragmento != null) {
        texto.append(fragmento);
      }
    }
    return texto.toString();
  }

  private static void establecerTexto(XWPFRun run, String texto) {
    int cantidad = run.getCTR().sizeOfTArray();
    if (cantidad == 0) {
      run.setText(texto);
      return;
    }
    run.getCTR().getTArray(0).setStringValue(texto);
    for (int i = cantidad - 1; i > 0; i--) {
      run.getCTR().removeT(i);
    }
  }

  private static String primeraLinea(String texto) {
    if (texto == null) {
      return "";
    }
    String linea = texto.split("\\r?\\n", 2)[0].trim();
    return linea.length() > 300 ? linea.substring(0, 300) + "..." : linea;
  }

  private static String vacioSiNulo(String texto, String porDefecto) {
    return texto == null || texto.isBlank() ? porDefecto : texto;
  }

  private static String normalizarNombre(String valor) {
    String sinAcentos =
        Normalizer.normalize(valor, Normalizer.Form.NFD).replaceAll("\\p{M}+", "");
    String nombre = sinAcentos.replaceAll("[^A-Za-z0-9]+", "_").replaceAll("(^_|_$)", "");
    return nombre.isEmpty() ? "escenario" : nombre;
  }
}
