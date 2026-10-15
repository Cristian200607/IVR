package interactions.Click;

import net.serenitybdd.screenplay.Interaction;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import net.serenitybdd.screenplay.targets.Target;
public class FastClick {

    public static Interaction fastOn(Target target) {
        return instrumented(FastClickOnTarget.class, target);
    }
}
