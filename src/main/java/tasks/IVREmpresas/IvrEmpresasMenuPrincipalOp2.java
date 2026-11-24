package tasks.IVREmpresas;


import interactions.comunes.MarcarOpcion;
import interactions.comunes.Colgar;
import interactions.comunes.WaitFor;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import userinterfaces.WaitTimes;
import utils.EvidenciaUtils;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static userinterfaces.IVRPage.*;
import static userinterfaces.WaitTimes.INICIO_LLAMADA;

public class IvrEmpresasMenuPrincipalOp2 implements Task {
    @Override
    public <T extends Actor> void performAs(T actor) {
        EvidenciaUtils.registrarCaptura("Pulsar Grabar Llamada");
        actor.attemptsTo(
                WaitFor.aTime(WaitTimes.INICIO_LLAMADA),
                Click.on(TECLADO_TELEFONO_EN_GRABACION),
                MarcarOpcion.conBoton(BOTON_2_EN_GRABACION),
                WaitFor.aTime(12000),
                MarcarOpcion.conBoton(BOTON_1_EN_GRABACION),
                WaitFor.aTime(12000),
                Colgar.laLlamada()
        );
        System.out.println("Llamada realizada y finalizada correctamente.");
    }

    public static Performable validar () {
        return instrumented(IvrEmpresasMenuPrincipalOp2.class);
    }
}
