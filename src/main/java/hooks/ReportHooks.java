package hooks;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import java.util.ArrayList;
import java.util.List;
import net.thucydides.core.steps.StepEventBus;
import utils.EstadoPrueba;
import utils.EvidenciaUtils;
import utils.WordAppium;

public class ReportHooks {

  private static final List<String> pasosEjecutados = new ArrayList<>();
  private static final ThreadLocal<Boolean> listenerRegistrado = ThreadLocal.withInitial(() -> false);
  private static String lineaUsada = "Sin datos";
  private static String ultimoPaso = "";

  public static void registrarPaso(String paso) {
    pasosEjecutados.add(paso);
    ultimoPaso = paso;
  }

  public static String getUltimoPaso() {
    return ultimoPaso;
  }

  public static void setLinea(String linea) {
    lineaUsada = linea;
  }

  @Before(order = 0)
  public void beforeEachScenario() {
    if (!listenerRegistrado.get() && StepEventBus.getEventBus() != null) {
      StepEventBus.getEventBus().registerListener(new EvidenciaStepListener());
      listenerRegistrado.set(true);
    }
    EstadoPrueba.inicio = System.currentTimeMillis();
    pasosEjecutados.clear();
    ultimoPaso = "";
    EvidenciaUtils.reiniciarContador();
    EstadoPrueba.fallo = false;
    EstadoPrueba.pasoFallido = "";
    EstadoPrueba.error = "";
    EstadoPrueba.capturaDeErrorTomada = false;
  }

  /**
   * Respaldo por si el listener no alcanzó a tomar la captura del error. order mayor que
   * CierreAppHook (20000): se toma antes de colgar y cerrar la app.
   */
  @After(order = 25000)
  public void capturarPantallaDelError(Scenario scenario) {
    if (scenario.isFailed() && !EstadoPrueba.capturaDeErrorTomada) {
      EvidenciaUtils.registrarCaptura("Pantalla al momento del error");
      EstadoPrueba.capturaDeErrorTomada = true;
    }
  }

  @After
  public void generarReporteFinal(Scenario scenario) {
    EstadoPrueba.fin = System.currentTimeMillis();

    long duracionTotal = (EstadoPrueba.fin - EstadoPrueba.inicio) / 1000;
    String duracionFormato =
        String.format("%02d:%02d:%02d", duracionTotal / 3600, (duracionTotal % 3600) / 60, duracionTotal % 60);

    boolean fallo = EstadoPrueba.fallo || scenario.isFailed();
    String pasoFallido = null;
    if (fallo) {
      pasoFallido = EstadoPrueba.pasoFallido.isBlank()
          ? (ultimoPaso.isBlank() ? "Paso no identificado" : "Después de: " + ultimoPaso)
          : EstadoPrueba.pasoFallido;
    }

    WordAppium.generarReporte(
        scenario.getName(),
        pasosEjecutados.toArray(new String[0]),
        EstadoPrueba.linea.isBlank() ? lineaUsada : EstadoPrueba.linea,
        duracionFormato,
        pasoFallido,
        fallo ? "FAILED" : "PASSED",
        EstadoPrueba.transcripcion,
        EstadoPrueba.textoEsperado);

    // Limpiar estado para el siguiente escenario
    pasosEjecutados.clear();
    EstadoPrueba.fallo = false;
    EstadoPrueba.pasoFallido = "";
    EstadoPrueba.error = "";
  }
}
