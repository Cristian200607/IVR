package tasks.IVREmpresas.OpcionesMenuPrincipal;

import interactions.comunes.ColgarSiSigueActiva;
import interactions.comunes.WaitFor;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import tasks.IVREmpresas.IrAlMenuPrincipalEmpresas;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static userinterfaces.IVRPage.*;

public class IvrEmpresasOp4Op1 implements Task {
    private final String numero;

    public IvrEmpresasOp4Op1(String numero) {
        this.numero = numero;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                IrAlMenuPrincipalEmpresas.con(numero),
                Click.on(BOTON_4_EN_GRABACION),
                WaitFor.aTime(19500),
                Click.on(BOTON_1_EN_GRABACION),
                WaitFor.aTime(68000),
                Click.on(BTN_COLGAR)
        );
    }

    public static Performable validar(String numero) {
        return instrumented(
                IvrEmpresasOp4Op1.class,
                numero
        );
    }
}
