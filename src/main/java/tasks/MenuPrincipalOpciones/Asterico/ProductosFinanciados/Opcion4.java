package tasks.MenuPrincipalOpciones.Asterico.ProductosFinanciados;

import MarcarLinea.DialPadHelper;
import interactions.Click.ClickSostenido;
import interactions.comunes.WaitFor;
import interactions.comunes.WaitForResponse;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import tasks.MenuPrincipalOpciones.Asterico.RecargarLineaPrepago.Opcion3;
import utils.EvidenciaUtils;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static userinterfaces.IVRPage.*;
import static userinterfaces.IVRPage.BOTON_1_EN_GRABACION;
import static userinterfaces.IVRPage.BOTON_3_EN_GRABACION;
import static userinterfaces.IVRPage.BOTON_4_EN_GRABACION;
import static userinterfaces.IVRPage.BOTON_9_EN_GRABACION;
import static userinterfaces.IVRPage.BOTON_ASTERISCO_EN_GRABACION;
import static utils.Constantes.ATENCION_AL_CLIENTE;

public class Opcion4 implements Task {
    private final String numero;

    public Opcion4(String numero) {
        this.numero = numero;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        EvidenciaUtils.registrarCaptura("Pulsar Grabar Llamada");
        actor.attemptsTo(
                WaitFor.aTime(500),
                ClickSostenido.sobre(BTN_GRABAR, 1),
                net.serenitybdd.screenplay.actions.Click.on(BOTON_SILENCIAR_EN_LLAMADA),
                WaitFor.aTime(13600),
                net.serenitybdd.screenplay.actions.Click.on(TECLADO_TELEFONO_EN_GRABACION),
                net.serenitybdd.screenplay.actions.Click.on(BOTON_3_EN_GRABACION),
                WaitFor.aTime(31000)
        );
        EvidenciaUtils.registrarCaptura("Se Empieza A Grabar Y Marca Opcion 3");
        actor.attemptsTo(
                net.serenitybdd.screenplay.actions.Click.on(BOTON_4_EN_GRABACION),
                WaitFor.aTime(2000)
        );
        EvidenciaUtils.registrarCaptura("Marca Opcion 4");

        // Marcar los números del parámetro lo más rápido posible
        EvidenciaUtils.registrarCaptura("Marcando número rápido");
        DialPadHelper.marcarNumeroMaximo(actor, numero);

        actor.attemptsTo(
                WaitFor.aTime(16000),
                net.serenitybdd.screenplay.actions.Click.on(BOTON_1_EN_GRABACION)
        );
        EvidenciaUtils.registrarCaptura("Marca Opcion 1");
        actor.attemptsTo(
                WaitFor.aTime(13000),
                net.serenitybdd.screenplay.actions.Click.on(BOTON_9_EN_GRABACION)
        );
        EvidenciaUtils.registrarCaptura("Marca Opcion 9");
        actor.attemptsTo(
                WaitFor.aTime(41000),
                net.serenitybdd.screenplay.actions.Click.on(BOTON_ASTERISCO_EN_GRABACION)
        );
        EvidenciaUtils.registrarCaptura("Marca Opcion Asterisco");
        actor.attemptsTo(
                WaitFor.aTime(27000),
                net.serenitybdd.screenplay.actions.Click.on(BOTON_4_EN_GRABACION)
        );
        EvidenciaUtils.registrarCaptura("Marca Opcion 4");
        actor.attemptsTo(
                WaitFor.aTime(4000),
                WaitForResponse.withText(ATENCION_AL_CLIENTE)
        );
        EvidenciaUtils.registrarCaptura("Finalizar Llamada");
    }

    public static Performable marcar(String numero) {
        return instrumented(Opcion4.class, numero);
    }
}
