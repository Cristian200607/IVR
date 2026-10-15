package hooks;

import cucumber.api.java.After;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import utils.Constantes;

/**
 * Deja el celular limpio al terminar cada escenario (pase o falle): cuelga la llamada si quedó
 * activa, cierra la app de llamadas y vuelve al inicio. Así la app no queda abierta ni la llamada
 * sigue en curso cuando la prueba falla antes de colgar.
 */
public class CierreAppHook {

  private static final long TIMEOUT_SEGUNDOS = 10;

  // order alto = se ejecuta antes que los demás @After (cuelga lo antes posible)
  @After(order = 20000)
  public void cerrarAppYLlamada() {
    try {
      if (hayLlamadaActiva()) {
        System.out.println("📞 Llamada aún activa al terminar el escenario, colgando...");
        adb("shell", "input", "keyevent", "KEYCODE_ENDCALL");
      }
      adb("shell", "am", "force-stop", Constantes.APP_MARCADOR);
      adb("shell", "input", "keyevent", "KEYCODE_HOME");
      System.out.println("✅ App de llamadas cerrada.");
    } catch (Exception e) {
      System.err.println("No se pudo cerrar la app de llamadas: " + e.getMessage());
    }
  }

  /** mCallState: 0 = sin llamada, 1 = timbrando, 2 = en llamada. */
  private boolean hayLlamadaActiva() {
    String salida = adb("shell", "dumpsys", "telephony.registry");
    return salida.contains("mCallState=1") || salida.contains("mCallState=2");
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
