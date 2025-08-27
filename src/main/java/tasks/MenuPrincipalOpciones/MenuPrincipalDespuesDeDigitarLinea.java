package tasks.MenuPrincipalOpciones;

import MarcarLinea.DialPadHelper;
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
import static userinterfaces.IVRPage.BOTON_1_EN_GRABACION;
import static userinterfaces.IVRPage.BOTON_4_EN_GRABACION;
import static userinterfaces.IVRPage.BOTON_9_EN_GRABACION;
import static utils.Constantes.FIN_LLAMADA_GRABACION;

public class MenuPrincipalDespuesDeDigitarLinea implements Task {
    private final String numero;

    public MenuPrincipalDespuesDeDigitarLinea(String numero) {
        this.numero = numero;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        EvidenciaUtils.registrarCaptura("Pulsar Grabar Llamada");
        actor.attemptsTo(
                WaitFor.aTime(400),
                ClickSostenido.sobre(BTN_GRABAR, 1),
                WaitFor.aTime(13000),
                Click.on(TECLADO_TELEFONO_EN_GRABACION),
                Click.on(BOTON_3_EN_GRABACION),
                WaitFor.aTime(30000)
        );
        EvidenciaUtils.registrarCaptura("Se Empieza A Grabar Y Marca Opcion 3");
        actor.attemptsTo(
                Click.on(BOTON_4_EN_GRABACION),
                WaitFor.aTime(1700)
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
                WaitFor.aTime(26000),
                Click.on(BOTON_9_EN_GRABACION)
        );
        EvidenciaUtils.registrarCaptura("Marca Opcion 9");
        actor.attemptsTo(
                WaitFor.aTime(40000),
                Click.on(BTN_COLGAR),
                WaitForResponse.withText(FIN_LLAMADA_GRABACION)
        );
        EvidenciaUtils.registrarCaptura("Finalizar Llamada");
    }

    public static Performable marcar(String numero) {
        return instrumented(MenuPrincipalDespuesDeDigitarLinea.class, numero);
    }
}
