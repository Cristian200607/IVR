package tasks.IVREmpresas.OpcionesNumeral;

import MarcarLinea.DialPadHelper;
import interactions.comunes.WaitFor;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import tasks.IVREmpresas.OpcionesAsteriscos.IvrEmpresasAsteriscoOp4Op4Op3;
import userinterfaces.WaitTimes;
import utils.EvidenciaUtils;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static userinterfaces.IVRPage.*;
import static userinterfaces.IVRPage.BOTON_1_EN_GRABACION;
import static userinterfaces.IVRPage.BOTON_3_EN_GRABACION;
import static userinterfaces.IVRPage.BOTON_4_EN_GRABACION;
import static userinterfaces.IVRPage.BOTON_ASTERISCO;
import static userinterfaces.IVRPage.BTN_COLGAR;

public class IvrEmpresasNumeralOp1 implements Task {
    private final String numero;

    public IvrEmpresasNumeralOp1(String numero) {
        this.numero = numero;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                WaitFor.aTime(WaitTimes.INICIO_LLAMADA),
                Click.on(TECLADO_TELEFONO_EN_GRABACION),
                Click.on(BOTON_2_EN_GRABACION)
        );
        actor.attemptsTo(
                WaitFor.aTime(10000),
                Click.on(BOTON_2_EN_GRABACION),
                WaitFor.aTime(9000)
        );
        DialPadHelper.marcarNumeroMaximo(actor, numero);
        actor.attemptsTo(
                Click.on(BOTON_NUMERAL_EN_GRABACION),
                WaitFor.aTime(14000),
                Click.on(BOTON_1_EN_GRABACION),
                WaitFor.aTime(35000),
                Click.on(BOTON_NUMERAL_EN_GRABACION),
                WaitFor.aTime(18000),
                Click.on(BOTON_1_EN_GRABACION),
                WaitFor.aTime(45000),
                Click.on(BTN_COLGAR)
        );
    }

    public static Performable validar(String numero) {
        return instrumented(
                IvrEmpresasNumeralOp1.class,
                numero
        );
    }
}
