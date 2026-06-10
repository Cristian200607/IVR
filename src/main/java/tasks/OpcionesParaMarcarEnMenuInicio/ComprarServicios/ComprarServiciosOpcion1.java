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
import static utils.IVRTiempos.MENU_PRINCIPAL;

public class ComprarServiciosOpcion1 implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        EvidenciaUtils.registrarCaptura("Pulsar Grabar Llamada");
        actor.attemptsTo(
                WaitFor.aTime(30),
                ClickSostenido.sobre(BTN_GRABAR, 1),
                Click.on(BOTON_SILENCIAR_EN_LLAMADA),
                WaitFor.aTime(MENU_PRINCIPAL),
                Click.on(TECLADO_TELEFONO_EN_GRABACION),
                Click.on(BOTON_2_EN_GRABACION),
                WaitFor.aTime(28000)
        );
        EvidenciaUtils.registrarCaptura("Se Empieza A Grabar Y Marca Opcion 2");
        actor.attemptsTo(
                Click.on(BOTON_1_EN_GRABACION),
                WaitFor.aTime(3000)
        );
        EvidenciaUtils.registrarCaptura("Marca Opcion 1");
        actor.attemptsTo(
                Click.on(BTN_COLGAR),
                WaitForResponse.withText(FIN_LLAMADA_GRABACION)
        );
        EvidenciaUtils.registrarCaptura("Finalizar Llamada");
    }

    public static Performable marcar() {
        return instrumented(ComprarServiciosOpcion1.class);
    }
}
