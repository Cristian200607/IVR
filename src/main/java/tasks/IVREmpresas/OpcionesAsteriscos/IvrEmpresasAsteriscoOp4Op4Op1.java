package tasks.IVREmpresas.OpcionesAsteriscos;

import MarcarLinea.DialPadHelper;
import interactions.comunes.WaitFor;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import userinterfaces.WaitTimes;
import utils.EvidenciaUtils;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static userinterfaces.IVRPage.*;

public class IvrEmpresasAsteriscoOp4Op4Op1 implements Task {

    private final String numero;

    public IvrEmpresasAsteriscoOp4Op4Op1(String numero) {
        this.numero = numero;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                WaitFor.aTime(WaitTimes.INICIO_LLAMADA),
                Click.on(TECLADO_TELEFONO_EN_GRABACION),
                Click.on(BOTON_2_EN_GRABACION)
        );
        EvidenciaUtils.registrarCaptura("Se marca opcion 2");
        actor.attemptsTo(
                WaitFor.aTime(11000),
                Click.on(BOTON_2_EN_GRABACION),
                WaitFor.aTime(9000)
        );
        EvidenciaUtils.registrarCaptura("Se marca opcion 2");
        DialPadHelper.marcarNumeroMaximo(actor, numero);
        actor.attemptsTo(
                Click.on(BOTON_NUMERAL_EN_GRABACION),
                WaitFor.aTime(15000),
                Click.on(BOTON_1_EN_GRABACION),
                WaitFor.aTime(35000),
                Click.on(BOTON_ASTERISCO_EN_GRABACION),
                WaitFor.aTime(24000),
                Click.on(BOTON_4_EN_GRABACION),
                WaitFor.aTime(25000),
                Click.on(BOTON_4_EN_GRABACION),
                WaitFor.aTime(24000),
                Click.on(BOTON_1_EN_GRABACION),
                WaitFor.aTime(26000),
                Click.on(BTN_COLGAR)
        );
        EvidenciaUtils.registrarCaptura("Finalizar Llamada");
    }

    public static Performable validar(String numero) {
        return instrumented(
                IvrEmpresasAsteriscoOp4Op4Op1.class,
                numero
        );
    }
}