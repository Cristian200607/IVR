package interactions.Click;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.Step;

public class FastClickOnTarget implements Interaction {

    private final Target target;

    public FastClickOnTarget(Target target) {
        this.target = target;
    }

    @Step("{0} fast clicks on #target")
    @Override
    public <T extends Actor> void performAs(T actor) {
        target.resolveFor(actor)
                .getWrappedElement()
                .click();
    }
}
