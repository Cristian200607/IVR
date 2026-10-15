package utils;

import net.thucydides.core.webdriver.SerenityWebdriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Toma capturas de evidencia del celular.
 *
 * <p>Nunca lanza excepción: una captura fallida no debe tumbar la prueba. Primero intenta con el
 * driver de Appium (con reintentos) y, si falla, usa "adb exec-out screencap" como respaldo.
 */
public class CapturaDePantallaMovil {

  private static final Logger LOGGER = Logger.getLogger(CapturaDePantallaMovil.class.getName());
  private static final String CAPTURAS_DIR = "Capturas/";
  private static final int INTENTOS_DRIVER = 2;
  private static final long ESPERA_ENTRE_INTENTOS_MS = 400;
  private static final long TIMEOUT_ADB_SEGUNDOS = 10;

  public static String tomarCapturaPantalla(String nombreCaptura) {
    String nombreNormalizado = normalizar(nombreCaptura);
    String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
    String rutaDestino = CAPTURAS_DIR + nombreNormalizado + "_" + timestamp + ".png";

    try {
      new File(CAPTURAS_DIR).mkdirs();

      byte[] png = capturarConDriver();
      if (png == null) {
        png = capturarConAdb();
      }
      if (png == null) {
        LOGGER.severe("No se pudo tomar la captura (driver ni adb): " + nombreCaptura);
        return "";
      }

      BufferedImage imagen = ImageIO.read(new ByteArrayInputStream(png));
      if (imagen == null) {
        LOGGER.severe("La captura no es una imagen válida: " + nombreCaptura);
        return "";
      }

      ImageIO.write(conBorde(imagen), "png", new File(rutaDestino));
      LOGGER.info("Captura de pantalla guardada: " + rutaDestino);
      return rutaDestino;
    } catch (Exception e) {
      // Cualquier error (WebDriverException, IO, etc.) se registra pero no rompe la prueba
      LOGGER.log(Level.SEVERE, "Error al tomar o guardar la captura de pantalla: " + nombreCaptura, e);
      return "";
    }
  }

  /** Mismo formato usado por WordAppium para encontrar la imagen de cada paso. */
  public static String normalizar(String nombre) {
    return nombre.toLowerCase().replaceAll("[^a-z0-9]", "_");
  }

  private static byte[] capturarConDriver() {
    for (int intento = 1; intento <= INTENTOS_DRIVER; intento++) {
      try {
        WebDriver driver = SerenityWebdriverManager.inThisTestThread().getCurrentDriver();
        if (!(driver instanceof TakesScreenshot)) {
          return null;
        }
        byte[] png = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        if (png != null && png.length > 0) {
          return png;
        }
      } catch (Exception e) {
        LOGGER.warning("Captura con driver falló (intento " + intento + "): " + e.getMessage());
      }
      dormir(ESPERA_ENTRE_INTENTOS_MS);
    }
    return null;
  }

  private static byte[] capturarConAdb() {
    Process proceso = null;
    try {
      proceso =
          new ProcessBuilder("adb", "-s", Constantes.UDID, "exec-out", "screencap", "-p")
              .redirectError(ProcessBuilder.Redirect.DISCARD)
              .start();
      ByteArrayOutputStream salida = new ByteArrayOutputStream();
      try (InputStream in = proceso.getInputStream()) {
        in.transferTo(salida);
      }
      if (!proceso.waitFor(TIMEOUT_ADB_SEGUNDOS, TimeUnit.SECONDS) || proceso.exitValue() != 0) {
        return null;
      }
      LOGGER.info("Captura tomada con adb (respaldo).");
      return salida.size() > 0 ? salida.toByteArray() : null;
    } catch (Exception e) {
      LOGGER.warning("Captura con adb falló: " + e.getMessage());
      return null;
    } finally {
      if (proceso != null) {
        proceso.destroy();
      }
    }
  }

  private static BufferedImage conBorde(BufferedImage imagen) {
    BufferedImage imagenConBorde =
        new BufferedImage(imagen.getWidth(), imagen.getHeight(), BufferedImage.TYPE_INT_RGB);
    Graphics2D g2d = imagenConBorde.createGraphics();
    g2d.drawImage(imagen, 0, 0, null);
    g2d.setColor(Color.WHITE);
    g2d.setStroke(new BasicStroke(5));
    g2d.drawRect(2, 2, imagen.getWidth() - 4, imagen.getHeight() - 4);
    g2d.dispose();
    return imagenConBorde;
  }

  private static void dormir(long ms) {
    try {
      Thread.sleep(ms);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
