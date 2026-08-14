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

  public RealizarLlamada(String numero) {
    this.numero = "*611";
  }

  @Override
  public <T extends Actor> void performAs(T actor) {

    // 🔹 Marca el número
    actor.attemptsTo(
            Click.on(TECLADO_TELEFONO),
            Click.on(BOTON_ASTERISCO_EN_GRABACION),
            Click.on(TECLADO_6),
            Click.on(TECLADO_1),
            Click.on(TECLADO_1),
            Click.on(BTN_LLAMAR),
            Click.on(BTN_TRES_PUNTOS),
            Click.on(BTN_CONFIRMAR_GRABAR_LLAMADA),
            Click.on(BTN_SALIR_MENU_TRES_PUNTOS)

    );
    EvidenciaUtils.registrarCaptura("Marcar *611");
  }

  public static Performable Llamar(String numero) {
    return instrumented(RealizarLlamada.class, numero);
  }
}