package tasks;

import interactions.Click.FastClick;
import interactions.comunes.WaitFor;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import utils.CapturaDePantallaMovil;
import utils.EvidenciaUtils;
import static interactions.Click.FastClick.fastOn;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static userinterfaces.IVRPage.*;

public class RealizarLlamada implements Task {

  private final String numero;

  public RealizarLlamada(String numero) {
    this.numero = "*611";
  }

  @Override
  public <T extends Actor> void performAs(T actor) {
    actor.attemptsTo(
            FastClick.fastOn(TECLADO_TELEFONO),
            FastClick.fastOn(BOTON_ASTERISCO),
            FastClick.fastOn(TECLADO_6),
            FastClick.fastOn(TECLADO_1),
            FastClick.fastOn(TECLADO_1),
            FastClick.fastOn(BTN_LLAMAR),
            FastClick.fastOn(BTN_TRES_PUNTOS),
            FastClick.fastOn(BTN_CONFIRMAR_GRABAR_LLAMADA),
            FastClick.fastOn(BTN_SALIR_MENU_TRES_PUNTOS),
            FastClick.fastOn(BOTON_SILENCIAR)
    );
    EvidenciaUtils.registrarCaptura("Marcar *611");
  }

  public static Performable Llamar(String numero) {
    return instrumented(RealizarLlamada.class, numero);
  }
}