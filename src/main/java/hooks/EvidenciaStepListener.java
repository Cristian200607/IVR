package hooks;

import net.thucydides.core.model.DataTable;
import net.thucydides.core.model.Story;
import net.thucydides.core.model.TestOutcome;
import net.thucydides.core.steps.ExecutedStepDescription;
import net.thucydides.core.steps.StepFailure;
import net.thucydides.core.steps.StepListener;
import utils.EstadoPrueba;
import utils.EvidenciaUtils;

/**
 * Guarda para el reporte Word el primer error del escenario (paso y mensaje) y toma la captura del
 * celular en ese mismo momento, antes de que los @After cuelguen y cierren la app.
 */
public class EvidenciaStepListener implements StepListener {

  private static final int LARGO_MAXIMO_NOMBRE_PASO = 80;

  @Override
  public void stepFailed(StepFailure failure) {
    try {
      if (EstadoPrueba.fallo) {
        return; // Serenity avisa el mismo error en cada nivel de pasos anidados: basta el primero
      }
      String paso =
          failure.getDescription() != null ? failure.getDescription().getName() : "Paso no identificado";
      EstadoPrueba.fallo = true;
      EstadoPrueba.pasoFallido = paso;
      EstadoPrueba.error = mensaje(failure.getException());

      String nombre = paso.length() > LARGO_MAXIMO_NOMBRE_PASO
          ? paso.substring(0, LARGO_MAXIMO_NOMBRE_PASO)
          : paso;
      EvidenciaUtils.registrarCaptura("Error en el paso - " + nombre);
      EstadoPrueba.capturaDeErrorTomada = true;
    } catch (Exception e) {
      System.err.println("No se pudo registrar la evidencia del error: " + e.getMessage());
    }
  }

  @Override
  public void lastStepFailed(StepFailure failure) {
    stepFailed(failure);
  }

  static String mensaje(Throwable error) {
    if (error == null) {
      return "(sin detalle del error)";
    }
    Throwable raiz = error;
    while ((raiz.getMessage() == null || raiz.getMessage().isBlank()) && raiz.getCause() != null) {
      raiz = raiz.getCause();
    }
    String texto = raiz.getMessage() != null ? raiz.getMessage() : "";
    return raiz.getClass().getSimpleName() + (texto.isBlank() ? "" : ": " + texto);
  }

  @Override public void testSuiteStarted(Class<?> storyClass) {}
  @Override public void testSuiteStarted(Story story) {}
  @Override public void testSuiteFinished() {}
  @Override public void testStarted(String description) {}
  @Override public void testStarted(String description, String id) {}
  @Override public void testFinished(TestOutcome result) {}
  @Override public void testRetried() {}
  @Override public void stepStarted(ExecutedStepDescription description) {}
  @Override public void skippedStepStarted(ExecutedStepDescription description) {}
  @Override public void stepFinished() {}
  @Override public void stepIgnored() {}
  @Override public void stepPending() {}
  @Override public void stepPending(String message) {}
  @Override public void assumptionViolated(String message) {}
  @Override public void testRunFinished() {}
  @Override public void addNewExamplesFrom(DataTable table) {}
  @Override public void useExamplesFrom(DataTable table) {}
  @Override public void notifyScreenChange() {}
  @Override public void testIsManual() {}
  @Override public void testPending() {}
  @Override public void testSkipped() {}
  @Override public void testIgnored() {}
  @Override public void testFailed(TestOutcome result, Throwable cause) {}
  public void testFinished(TestOutcome result, boolean isDataDriven, boolean stateExhausted) {}
  public void exampleFinished() {}
  public void exampleStarted(java.util.Map<String, String> data) {}
}
