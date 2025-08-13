package tasks.OpcionesParaMarcar;

import interactions.Click.ClickSostenido;
import interactions.comunes.WaitFor;
import interactions.comunes.WaitForResponse;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static userinterfaces.IVRPage.*;
import static userinterfaces.IVRPage.BTN_COLGAR;
import static userinterfaces.IVRPage.BTN_GRABAR;
import static utils.Constantes.*;

public class MarcarOpcionUno implements Task {
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitFor.aTime(1000),
                ClickSostenido.sobre(BTN_GRABAR, 1),
                WaitFor.aTime(18000),
                Click.on(TECLADO_TELEFONO_EN_GRABACION),
                Click.on(BOTON_1_EN_GRABACION),
                WaitForResponse.withText(FIN_LLAMADA_GRABACION)
        );
    }

    public static Performable marcar() {
        return instrumented(MarcarOpcionUno.class);
    }
}
