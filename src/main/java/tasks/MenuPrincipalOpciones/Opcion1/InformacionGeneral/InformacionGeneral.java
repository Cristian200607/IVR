package tasks.MenuPrincipalOpciones.Opcion1.InformacionGeneral;

import MarcarLinea.DialPadHelper;
import interactions.Click.ClickSostenido;
import interactions.comunes.WaitFor;
import interactions.comunes.WaitForResponse;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
//import net.serenitybdd.screenplay.actions.Click;
import utils.EvidenciaUtils;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static userinterfaces.IVRPage.*;
import static userinterfaces.IVRPage.BOTON_1_EN_GRABACION;
import static userinterfaces.IVRPage.BOTON_3_EN_GRABACION;
import static userinterfaces.IVRPage.BOTON_4_EN_GRABACION;
import static userinterfaces.IVRPage.BOTON_9_EN_GRABACION;
import static utils.Constantes.ATENCION_AL_CLIENTE;
import coloresTerminal.Click;


public class InformacionGeneral implements Task {

    private static final String paso = "Pulsar Grbrar llamada";
    private static final String paso1 = "Marcar Opcion 3";
    private static final String paso2 = "Marcar Opcion 1";
    private static final String paso3 = "Esperar a que se cuelgue la llamada";
    private final String numero;

    public InformacionGeneral(String numero) {
        this.numero = numero;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        EvidenciaUtils.registrarCaptura(paso);
        actor.attemptsTo(
                WaitFor.aTime(500),
                ClickSostenido.sobre(BTN_GRABAR, 1),
                Click.on(BOTON_SILENCIAR_EN_LLAMADA),
                WaitFor.aTime(13600),
                Click.on(TECLADO_TELEFONO_EN_GRABACION),
                Click.on(BOTON_3_EN_GRABACION),
                WaitFor.aTime(31000)
        );
        EvidenciaUtils.registrarCaptura(paso1);
        actor.attemptsTo(
                Click.on(BOTON_4_EN_GRABACION),
                WaitFor.aTime(2000)
        );

        // Marcar los números del parámetro lo más rápido posible
        EvidenciaUtils.registrarCaptura("Marcando número rápido");
        DialPadHelper.marcarNumeroMaximo(actor, numero);

        actor.attemptsTo(
                WaitFor.aTime(16000),
                Click.on(BOTON_1_EN_GRABACION)
        );
        actor.attemptsTo(
                WaitFor.aTime(14000),
                Click.on(BOTON_9_EN_GRABACION)
        );
        actor.attemptsTo(
                WaitFor.aTime(41000),
                Click.on(BOTON_1_EN_GRABACION)
        );
        actor.attemptsTo(
                WaitFor.aTime(39000),
                Click.on(BOTON_1_EN_GRABACION)
        );
        actor.attemptsTo(
                WaitForResponse.withText(ATENCION_AL_CLIENTE)
        );
        EvidenciaUtils.registrarCaptura(paso3);
    }

    public static Performable marcar(String numero) {
        return instrumented(InformacionGeneral.class, numero);
    }
}
