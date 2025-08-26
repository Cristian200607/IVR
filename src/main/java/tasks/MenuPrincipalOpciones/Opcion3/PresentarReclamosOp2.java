package tasks.MenuPrincipalOpciones.Opcion3;

import MarcarLinea.DialPadHelper;
import interactions.Click.ClickSostenido;
import interactions.comunes.WaitFor;
import interactions.comunes.WaitForResponse;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import utils.EvidenciaUtils;

import static userinterfaces.IVRPage.*;
import static userinterfaces.IVRPage.BOTON_1_EN_GRABACION;
import static userinterfaces.IVRPage.BOTON_3_EN_GRABACION;
import static userinterfaces.IVRPage.BOTON_4_EN_GRABACION;
import static userinterfaces.IVRPage.BOTON_9_EN_GRABACION;
import static utils.Constantes.ATENCION_AL_CLIENTE;

public class PresentarReclamosOp2 implements Task {
    private final String numero;

    public PresentarReclamosOp2(String numero) {
        this.numero = numero;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        EvidenciaUtils.registrarCaptura("Pulsar Grabar Llamada");
        actor.attemptsTo(
                WaitFor.aTime(300),
                ClickSostenido.sobre(BTN_GRABAR, 1),
                Click.on(BOTON_SILENCIAR_EN_LLAMADA),
                WaitFor.aTime(9000),
                Click.on(TECLADO_TELEFONO_EN_GRABACION),
                Click.on(BOTON_3_EN_GRABACION),
                WaitFor.aTime(30000)
        );
        EvidenciaUtils.registrarCaptura("Se Empieza A Grabar Y Marca Opcion 3");
        actor.attemptsTo(
                Click.on(BOTON_4_EN_GRABACION),
                WaitFor.aTime(2000)
        );
        EvidenciaUtils.registrarCaptura("Marca Opcion 4");

        // Marcar los números del parámetro lo más rápido posible
        DialPadHelper.marcarNumeroMaximo(actor, numero);
        EvidenciaUtils.registrarCaptura("Se Digita La Linea A Consultar");
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
                WaitFor.aTime(40000),
                Click.on(BOTON_3_EN_GRABACION)
        );
        EvidenciaUtils.registrarCaptura("Marca Opcion 3");
        actor.attemptsTo(
                WaitFor.aTime(21000),
                Click.on(BOTON_2_EN_GRABACION)
        );
        EvidenciaUtils.registrarCaptura("Marca Opcion 2");
        actor.attemptsTo(
                WaitFor.aTime(20000),
                Click.on(BOTON_1_EN_GRABACION)
        );
        EvidenciaUtils.registrarCaptura("Marca Opcion 1");
        actor.attemptsTo(
                WaitFor.aTime(20000),
                WaitForResponse.withText(ATENCION_AL_CLIENTE)
        );
        EvidenciaUtils.registrarCaptura("Finalizar Llamada");
    }

    public static Performable marcar(String numero) {
        return Tasks.instrumented(PresentarReclamosOp2.class, numero);
    }
}
