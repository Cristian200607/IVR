package interactions.comunes;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static userinterfaces.IVRPage.BTN_COLGAR;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.actions.Click;
import net.thucydides.core.annotations.Step;
import utils.EvidenciaUtils;

/** Cuelga la llamada dejando antes captura de la duración como evidencia. */
public class Colgar implements Interaction {

  public static final String PASO_DURACION = "Duración de la llamada antes de finalizar";

  public static Colgar laLlamada() {
    return instrumented(Colgar.class);
  }

  @Override
  @Step("Colgar la llamada")
  public <T extends Actor> void performAs(T actor) {
    EvidenciaUtils.registrarCaptura(PASO_DURACION);
    actor.attemptsTo(Click.on(BTN_COLGAR));
  }
}
