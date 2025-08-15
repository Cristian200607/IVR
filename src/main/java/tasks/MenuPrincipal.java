package tasks;

import interactions.Click.ClickSostenido;
import interactions.comunes.WaitFor;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import utils.EvidenciaUtils;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static userinterfaces.IVRPage.BTN_COLGAR;
import static userinterfaces.IVRPage.BTN_GRABAR;

public class MenuPrincipal implements Task {
    private static final String paso = "Presionar boton grabar";
    private static final String paso2 = "Validacion texto llamada finalaizada";

    @Override
    public <T extends Actor> void performAs(T actor) {
        EvidenciaUtils.registrarCaptura(paso);
        actor.attemptsTo(
                ClickSostenido.sobre(BTN_GRABAR, 1),
                WaitFor.aTime(17000)
        );
        actor.attemptsTo(Click.on(BTN_COLGAR));
        EvidenciaUtils.registrarCaptura(paso2);
        System.out.println("📞 Llamada realizada y finalizada correctamente.");

    }

    public static Performable validar () {
        return instrumented(MenuPrincipal.class);
    }
}