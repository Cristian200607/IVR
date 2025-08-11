package tasks;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static userinterfaces.IVRPage.*;

import hooks.ReportHooks;
import interactions.comunes.WaitFor;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import org.openqa.selenium.By;
import utils.CapturaDePantallaMovil;

public class RealizarLlamada implements Task {

  private final String numero;

  public RealizarLlamada(String numero) {
    this.numero = "*611";
  }

  public static Performable alNumero(String numero) {
    return instrumented(RealizarLlamada.class, numero);
  }

  @Override
  public <T extends Actor> void performAs(T actor) {

    // 🔹 Marca el número
    actor.attemptsTo(
        Click.on(TECLADO_TELEFONO),
        Enter.theValue(numero).into(By.id("digits")),
        Click.on(BTN_LLAMAR));

    actor.attemptsTo(WaitFor.aTime(7000));

    CapturaDePantallaMovil.tomarCapturaPantalla("prueba");
    ReportHooks.registrarPaso("prueba");

    actor.attemptsTo(Click.on(BTN_COLGAR));
    System.out.println("📞 Llamada realizada y finalizada correctamente.");
  }
}
