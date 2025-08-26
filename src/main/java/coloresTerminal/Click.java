package coloresTerminal;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.targets.Target;

public class Click implements Interaction {

    private final Target target;

    public Click(Target target) {
        this.target = target;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        System.out.println("\u001B[32m👉 [" + actor.getName() + "] hace click en: " + target.getName() + "\u001B[0m");
        // Llama al Click original de Serenity
        net.serenitybdd.screenplay.actions.Click.on(target).performAs(actor);
    }

    public static Click on(Target target) {
        return new Click(target);
    }
}

