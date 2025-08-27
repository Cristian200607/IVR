package tasks.OpcionesParaMarcarEnMenuInicio;

import interactions.Click.ClickSostenido;
import interactions.comunes.WaitFor;
import interactions.comunes.WaitForResponse;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import utils.EvidenciaUtils;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static userinterfaces.IVRPage.*;
import static userinterfaces.IVRPage.BTN_GRABAR;
import static utils.Constantes.*;

public class MenuPrincipalOpcionUno implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {

        EvidenciaUtils.registrarCaptura("Pulsar Grabar Llamada");
        actor.attemptsTo(
                WaitFor.aTime(400),
                ClickSostenido.sobre(BTN_GRABAR, 1),
                WaitFor.aTime(15000),
                Click.on(TECLADO_TELEFONO_EN_GRABACION),
                Click.on(BOTON_1_EN_GRABACION)
        );
        EvidenciaUtils.registrarCaptura("Se Empieza A Grabar Y Marca Opcion 1");
        actor.attemptsTo(
                WaitForResponse.withText(ATENCION_AL_CLIENTE)
        );
        EvidenciaUtils.registrarCaptura("Finalizar Llamada");

    }

    public static Performable marcar() {
        return instrumented(MenuPrincipalOpcionUno.class);
    }
}
