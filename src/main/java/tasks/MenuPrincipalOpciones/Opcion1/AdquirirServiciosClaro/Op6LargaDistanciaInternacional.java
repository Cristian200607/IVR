package tasks.MenuPrincipalOpciones.Opcion1.AdquirirServiciosClaro;

import MarcarLinea.DialPadHelper;
import coloresTerminal.Click;
import interactions.Click.ClickSostenido;
import interactions.comunes.WaitFor;
import interactions.comunes.WaitForResponse;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import utils.EvidenciaUtils;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static userinterfaces.IVRPage.*;
import static userinterfaces.IVRPage.BOTON_1_EN_GRABACION;
import static userinterfaces.IVRPage.BOTON_3_EN_GRABACION;
import static userinterfaces.IVRPage.BOTON_4_EN_GRABACION;
import static userinterfaces.IVRPage.BOTON_9_EN_GRABACION;
import static utils.Constantes.ATENCION_AL_CLIENTE;



public class Op6LargaDistanciaInternacional implements Task {
    private final String numero;

    public Op6LargaDistanciaInternacional(String numero) {
        this.numero = numero;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        EvidenciaUtils.registrarCaptura("Pulsar Grabar llamada");
        actor.attemptsTo(
                WaitFor.aTime(30),
                ClickSostenido.sobre(BTN_GRABAR, 1),
                Click.on(BOTON_SILENCIAR_EN_LLAMADA),
                WaitFor.aTime(34400),
                Click.on(TECLADO_TELEFONO_EN_GRABACION),
                Click.on(BOTON_3_EN_GRABACION),
                WaitFor.aTime(31000)
        );
        EvidenciaUtils.registrarCaptura("Se Empieza A Grabar Y Marca Opcion 3");
        actor.attemptsTo(
                Click.on(BOTON_4_EN_GRABACION),
                WaitFor.aTime(2000)
        );
        EvidenciaUtils.registrarCaptura("Marca Opcion 4");
        // Marcar los números del parámetro lo más rápido posible
        EvidenciaUtils.registrarCaptura("Marcando número rápido");
        DialPadHelper.marcarNumeroMaximo(actor, numero);

        actor.attemptsTo(
                WaitFor.aTime(16000),
                Click.on(BOTON_1_EN_GRABACION)
        );
        EvidenciaUtils.registrarCaptura("Marca Opcion 1");
        actor.attemptsTo(
                WaitFor.aTime(13000),
                Click.on(BOTON_9_EN_GRABACION)
        );
        EvidenciaUtils.registrarCaptura("Marca Opcion 9");
        actor.attemptsTo(
                WaitFor.aTime(46000),
                Click.on(BOTON_1_EN_GRABACION)
        );
        EvidenciaUtils.registrarCaptura("Marca Opcion 1");
        actor.attemptsTo(
                WaitFor.aTime(41000),
                Click.on(BOTON_4_EN_GRABACION)
        );
        EvidenciaUtils.registrarCaptura("Marca Opcion 4");
        actor.attemptsTo(
                WaitFor.aTime(39000),
                Click.on(BOTON_6_EN_GRABACION)
        );
        EvidenciaUtils.registrarCaptura("Marca Opcion 6");
        actor.attemptsTo(
                WaitFor.aTime(7000),
                WaitForResponse.withText(ATENCION_AL_CLIENTE)
        );
        EvidenciaUtils.registrarCaptura("Finalizar Llamada");
    }

    public static Performable marcar(String numero) {
        return instrumented(Op6LargaDistanciaInternacional.class, numero);
    }
}
