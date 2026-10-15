package hooks;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import utils.Constantes;
import utils.EstadoPrueba;

/**
 * Cuando un escenario falla, guarda en Diagnosticos/ lo que pasó en el celular (crashes de
 * UiAutomator2, procesos matados, etc.) para no tener que buscarlo en Appium ni correr adb a mano.
 */
public class DiagnosticoFalloHook {

  private static final String CARPETA = "Diagnosticos";
  private static final long TIMEOUT_SEGUNDOS = 15;
  private static final Pattern LINEAS_RELEVANTES =
      Pattern.compile(
          "(?i)uiautomator|instrumentation|AndroidRuntime|FATAL|has died|Process crashed"
              + "|lowmemorykiller|Killing|ANR in");

  /** Limpia el buffer de crashes para que el diagnóstico solo muestre los de este escenario. */
  @Before
  public void limpiarCrashesAnteriores() {
    adb("logcat", "-b", "crash", "-c");
  }

  // order mayor que CierreAppHook: guarda el diagnóstico antes de colgar y cerrar la app
  @After(order = 30000)
  public void guardarDiagnostico(Scenario scenario) {
    if (!scenario.isFailed()) {
      return;
    }
    try {
      String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
      File archivo = new File(CARPETA, "fallo_" + fecha + ".log");
      archivo.getParentFile().mkdirs();

      StringBuilder sb = new StringBuilder();
      sb.append("Escenario: ").append(scenario.getName()).append('\n');
      sb.append("Fecha:     ").append(fecha).append('\n');
      sb.append("Paso:      ").append(EstadoPrueba.pasoFallido).append("\n\n");

      sb.append("===== CRASHES EN EL CELULAR (adb logcat -b crash) =====\n");
      String crashes = adb("logcat", "-d", "-b", "crash");
      sb.append(crashes.isBlank() ? "(ninguno)\n" : crashes).append('\n');

      sb.append("===== LOGCAT FILTRADO (uiautomator, crashes, procesos matados) =====\n");
      for (String linea : adb("logcat", "-d", "-t", "5000").split("\n")) {
        if (LINEAS_RELEVANTES.matcher(linea).find()) {
          sb.append(linea).append('\n');
        }
      }

      Files.write(archivo.toPath(), sb.toString().getBytes(StandardCharsets.UTF_8));
      System.out.println("🩺 Diagnóstico del fallo guardado en: " + archivo.getAbsolutePath());
    } catch (Exception e) {
      System.err.println("No se pudo guardar el diagnóstico del fallo: " + e.getMessage());
    }
  }

  private String adb(String... args) {
    List<String> comando = new ArrayList<>(Arrays.asList("adb", "-s", Constantes.UDID));
    comando.addAll(Arrays.asList(args));
    Process proceso = null;
    try {
      proceso = new ProcessBuilder(comando).redirectErrorStream(true).start();
      ByteArrayOutputStream salida = new ByteArrayOutputStream();
      try (InputStream in = proceso.getInputStream()) {
        in.transferTo(salida);
      }
      proceso.waitFor(TIMEOUT_SEGUNDOS, TimeUnit.SECONDS);
      return salida.toString(StandardCharsets.UTF_8);
    } catch (Exception e) {
      return "";
    } finally {
      if (proceso != null) {
        proceso.destroy();
      }
    }
  }
}
