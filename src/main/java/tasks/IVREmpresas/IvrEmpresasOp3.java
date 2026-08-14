package tasks.IVREmpresas;

import interactions.comunes.WaitFor;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import utils.EvidenciaUtils;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static userinterfaces.IVRPage.*;
import static userinterfaces.IVRPage.BTN_COLGAR;

public class IvrEmpresasOp3 implements Task {
    @Override
    public <T extends Actor> void performAs(T actor) {
        EvidenciaUtils.registrarCaptura("Pulsar Grabar Llamada");
        actor.attemptsTo(
                WaitFor.aTime(22000),
                Click.on(TECLADO_TELEFONO_EN_GRABACION),
                Click.on(BOTON_2_EN_GRABACION),
                WaitFor.aTime(11000),
                Click.on(BOTON_2_EN_GRABACION),
                WaitFor.aTime(10000),
                Click.on(BTN_COLGAR)
        );
        EvidenciaUtils.registrarCaptura("Finalizar Llamada");
        System.out.println("Llamada realizada y finalizada correctamente.");
    }

    public static Performable validar () {
        return instrumented(IvrEmpresasOp3.class);
    }
}
