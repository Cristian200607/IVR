package tasks;

import interactions.Click.ClickSostenido;
import interactions.comunes.WaitFor;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import utils.EvidenciaUtils;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static userinterfaces.IVRPage.*;


public class MenuInicio implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        EvidenciaUtils.registrarCaptura("Pulsar Grabar Llamada");
        actor.attemptsTo(
                WaitFor.aTime(200),
                ClickSostenido.sobre(BTN_GRABAR, 1),
                WaitFor.aTime(19000)
        );
        actor.attemptsTo(Click.on(BTN_COLGAR));
        EvidenciaUtils.registrarCaptura("Finalizar Llamada");
        System.out.println("📞 Llamada realizada y finalizada correctamente.");
    }

    public static Performable validar () {
        return instrumented(MenuInicio.class);
    }
}