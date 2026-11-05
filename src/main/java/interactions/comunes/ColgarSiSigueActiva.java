package interactions.comunes;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static userinterfaces.IVRPage.BTN_COLGAR;

import java.util.concurrent.TimeUnit;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.actions.Click;
import net.thucydides.core.annotations.Step;
import utils.MyDriver;

/**
 * Cuelga la llamada; si el IVR ya la terminó, sigue sin fallar para poder traer el audio.
 * La validación de la transcripción decide si el mensaje quedó completo.
 */
public class ColgarSiSigueActiva implements Interaction {

  private static final int ESPERA_IMPLICITA_SEGUNDOS = 20;

  public static ColgarSiSigueActiva laLlamada() {
    return instrumented(ColgarSiSigueActiva.class);
  }

  @Override
  @Step("Colgar si la llamada sigue activa")
  public <T extends Actor> void performAs(T actor) {
    boolean sigueActiva;
    MyDriver.getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    try {
      sigueActiva = !BTN_COLGAR.resolveAllFor(actor).isEmpty();
    } finally {
      MyDriver.getDriver().manage().timeouts().implicitlyWait(ESPERA_IMPLICITA_SEGUNDOS, TimeUnit.SECONDS);
    }
    if (sigueActiva) {
      actor.attemptsTo(Click.on(BTN_COLGAR));
    } else {
      System.out.println("⚠️ La llamada ya había terminado: colgó el IVR.");
    }
  }
}
