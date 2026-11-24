package interactions.comunes;

import static net.serenitybdd.screenplay.Tasks.instrumented;

import net.thucydides.core.annotations.Step;
import net.serenitybdd.core.time.InternalSystemClock;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import utils.EvidenciaUtils;

public class WaitFor implements Interaction {

  private final int var;

  protected WaitFor(int var) {
    this.var = var;
  }

  public static WaitFor aTime(int var) {
    return instrumented(WaitFor.class, var);
  }

  @Override
  @Step("{0}")
  public <T extends Actor> void performAs(T actor) {
    // Descuenta lo que tardaron las capturas de evidencia tomadas desde la última espera
    long espera = Math.max(0, var - EvidenciaUtils.tomarTiempoPorDescontar());
    new InternalSystemClock().pauseFor(espera);
  }
}
