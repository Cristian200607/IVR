package tasks;

import interactions.comunes.WaitFor;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import org.openqa.selenium.By;
import utils.CapturaDePantallaMovil;
import utils.EvidenciaUtils;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static userinterfaces.IVRPage.*;

public class RealizarLlamada implements Task {

  private final String numero;
  private static final String paso = "Marcar *611";

  public RealizarLlamada(String numero) {
    this.numero = "*611";
  }

  @Override
  public <T extends Actor> void performAs(T actor) {

    // 🔹 Marca el número
    actor.attemptsTo(
            Click.on(TECLADO_TELEFONO),
            Enter.theValue(numero).into(By.id("digits"))
    );
    EvidenciaUtils.registrarCaptura(paso);
    actor.attemptsTo(
            Click.on(BTN_LLAMAR)
    );
  }

  public static Performable Llamar(String numero) {
    return instrumented(RealizarLlamada.class, numero);
  }
}