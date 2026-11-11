package tasks.IVREmpresas.OpcionesNumeral;

import MarcarLinea.DialPadHelper;
import interactions.comunes.ColgarSiSigueActiva;
import interactions.comunes.WaitFor;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import userinterfaces.WaitTimes;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static userinterfaces.IVRPage.*;

public class IvrEmpresasNumeralOp1Op1 implements Task {
    private final String numero;

    public IvrEmpresasNumeralOp1Op1(String numero) {
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
                WaitFor.aTime(11000),
                Click.on(BOTON_2_EN_GRABACION),
                WaitFor.aTime(8000)
        );
        DialPadHelper.marcarNumeroMaximo(actor, numero);
        actor.attemptsTo(
                Click.on(BOTON_NUMERAL_EN_GRABACION),
                WaitFor.aTime(14000),
                Click.on(BOTON_1_EN_GRABACION),
                WaitFor.aTime(35000),
                Click.on(BOTON_NUMERAL_EN_GRABACION),
                WaitFor.aTime(16000),
                Click.on(BOTON_1_EN_GRABACION),
                WaitFor.aTime(59000),
                Click.on(BOTON_1_EN_GRABACION),
                WaitFor.aTime(71000),
                ColgarSiSigueActiva.laLlamada()
        );
    }

    public static Performable validar(String numero) {
        return instrumented(
                IvrEmpresasNumeralOp1Op1.class,
                numero
        );
    }
}
