package tasks;

import interactions.Click.ClickSostenido;
import interactions.comunes.WaitFor;
import io.appium.java_client.AppiumBy;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import org.openqa.selenium.By;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static userinterfaces.IVRPage.*;

public class MarcarOpcionDos implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitFor.aTime(15000),
                Click.on(TECLADO_EN_LLAMADA),
                Click.on(BOTON_2_EN_LLAMADA),
                Click.on(COLAPSAR_EN_LLAMADA),
                WaitFor.aTime(4000),
                ClickSostenido.sobre(BTN_GRABAR, 1),
                WaitFor.aTime(19000),
                Click.on(BTN_COLGAR)
        );
    }

    public static Performable marcar() {
        return instrumented(MarcarOpcionDos.class);
    }
}
