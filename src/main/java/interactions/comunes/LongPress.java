package interactions.comunes;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class LongPress implements Interaction {

    private final Target target;

    public LongPress(Target target) {
        this.target = target;
    }

    @Override
    @Step("{0} realiza long press sobre el elemento")
    public <T extends Actor> void performAs(T actor) {
        AndroidDriver driver = (AndroidDriver) BrowseTheWeb.as(actor).getDriver();
        WebElement element = target.resolveFor(actor).getElement();

        new TouchAction<>(driver)
                .longPress(
                        LongPressOptions.longPressOptions()
                                .withElement(ElementOption.element(element))
                                .withDuration(Duration.ofSeconds(2))
                )
                .release()
                .perform();
    }

    public static LongPress on(Target target) {
        return new LongPress(target);
    }
}
