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

public class IvrEmpresasOp1 implements Task {
    private final String numero;

    public IvrEmpresasOp1(String numero) {
        this.numero = numero;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                IrAlMenuPrincipalEmpresas.con(numero),
                Click.on(BOTON_1_EN_GRABACION),
                WaitFor.aTime(17500),
                ColgarSiSigueActiva.laLlamada()
        );
    }

    public static Performable validar(String numero) {
        return instrumented(
                IvrEmpresasOp1.class,
                numero
        );
    }
}
