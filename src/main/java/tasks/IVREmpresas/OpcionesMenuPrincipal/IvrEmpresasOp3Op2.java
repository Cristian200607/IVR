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

public class IvrEmpresasOp3Op2 implements Task {
    private final String numero;

    public IvrEmpresasOp3Op2(String numero) {
        this.numero = numero;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                IrAlMenuPrincipalEmpresas.con(numero),
                Click.on(BOTON_3_EN_GRABACION),
                WaitFor.aTime(23500),
                Click.on(BOTON_2_EN_GRABACION),
                WaitFor.aTime(38000),
                ColgarSiSigueActiva.laLlamada()
        );
    }

    public static Performable validar(String numero) {
        return instrumented(
                IvrEmpresasOp3Op2.class,
                numero
        );
    }
}
