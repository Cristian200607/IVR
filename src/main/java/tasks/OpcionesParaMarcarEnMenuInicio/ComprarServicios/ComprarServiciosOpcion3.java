package tasks.OpcionesParaMarcarEnMenuInicio.ComprarServicios;

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
import static userinterfaces.IVRPage.BTN_COLGAR;
import static utils.Constantes.FIN_LLAMADA_GRABACION;


public class ComprarServiciosOpcion3 implements Task {
    @Override
    public <T extends Actor> void performAs(T actor) {

        EvidenciaUtils.registrarCaptura("Pulsar Grabar Llamada");
        actor.attemptsTo(
                WaitFor.aTime(30),
                ClickSostenido.sobre(BTN_GRABAR, 1),
                Click.on(BOTON_SILENCIAR_EN_LLAMADA),
                WaitFor.aTime(34000),
                Click.on(TECLADO_TELEFONO_EN_GRABACION),
                Click.on(BOTON_2_EN_GRABACION),
                WaitFor.aTime(28000)
        );
        EvidenciaUtils.registrarCaptura("Se Empieza A Grabar Y Marca Opcion 2");
        actor.attemptsTo(
                Click.on(BOTON_3_EN_GRABACION),
                WaitFor.aTime(1700)
        );
        EvidenciaUtils.registrarCaptura("Marcar Opcion 3");
        actor.attemptsTo(
                Click.on(BTN_COLGAR),
                WaitForResponse.withText(FIN_LLAMADA_GRABACION)
        );
        EvidenciaUtils.registrarCaptura("Finalizar Llamada");

    }

    public static Performable marcar() {
        return instrumented(ComprarServiciosOpcion3.class);
    }
}
