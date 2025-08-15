package interactions.Click;

import io.appium.java_client.android.AndroidDriver;
import net.thucydides.core.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.webdriver.ThucydidesWebDriverSupport;
import net.thucydides.core.webdriver.WebDriverFacade;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ClickSostenido implements Interaction {

    private final Target target;
    private final int segundos;

    public ClickSostenido(Target target, int segundos) {
        this.target = target;
        this.segundos = segundos;
    }

    @Override
    @Step("Mantiene presionado el elemento '{0}' por {1} segundos.")
    public <T extends Actor> void performAs(T actor) {
        WebElement elemento = target.resolveFor(actor);

        // Obtener AndroidDriver real desde Serenity
        AndroidDriver driver = (AndroidDriver) ((WebDriverFacade) ThucydidesWebDriverSupport.getDriver())
                .getProxiedDriver();

        new Actions(driver)
                .clickAndHold(elemento)
                .pause(segundos * 1000L)
                .release()
                .perform();
    }

    public static Interaction sobre(Target target, int segundos) {
        return instrumented(ClickSostenido.class, target, segundos);
    }
}
