package interactions.scroll;

import static net.serenitybdd.screenplay.Tasks.instrumented;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import utils.AndroidObject;

public class ScrollInicio extends AndroidObject implements Interaction {

  @Override
  @Step("Realiza Scroll una vez hacia abajo en el inicio de la App")
  public <T extends Actor> void performAs(T actor) {
    UnScrollArribaInicio(actor);
  }

  public static Interaction scrollUnaVista() {
    return instrumented(ScrollInicio.class);
  }
}
