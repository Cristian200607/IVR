package interactions.Click;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import net.thucydides.core.webdriver.ThucydidesWebDriverSupport;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ClickSostenido implements Interaction {

    private final Target target;
    private final int segundos;

    public ClickSostenido(Target target, int segundos) {
        this.target = target;
        this.segundos = segundos;
    }

    @Override
    @Step("Mantiene presionado el elemento '#target' por {0} segundos.")
    public <T extends Actor> void performAs(T actor) {
        WebElement elemento = target.resolveFor(actor);

        WebDriver driver = ThucydidesWebDriverSupport.getDriver();
        Actions actions = new Actions(driver);

        actions.clickAndHold(elemento)
                .pause(segundos * 1000L)
                .release()
                .perform();
    }

    public static Interaction sobre(Target target, int segundos) {
        return instrumented(ClickSostenido.class, target, segundos);
    }
}
