package tasks;

import interactions.comunes.WaitFor;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import utils.EvidenciaUtils;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static userinterfaces.IVRPage.*;

public class CompartirAudio implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {

        EvidenciaUtils.registrarCaptura("Finalizar llamada");
        actor.attemptsTo(
            Click.on(VOLVER_PAGINA_PRINCIPAL),
            Click.on(ULTIMA_LLAMADA),
            Click.on(BTN_COMPARTIR_AUDIO),
            Click.on(UBICACION_GRABACION)
        );
    }

    public static Performable validar() {
        return instrumented(CompartirAudio.class);
    }
}