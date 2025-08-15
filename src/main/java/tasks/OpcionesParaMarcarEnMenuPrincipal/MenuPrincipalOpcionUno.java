package tasks.OpcionesParaMarcarEnMenuPrincipal;

import interactions.Click.ClickSostenido;
import interactions.comunes.WaitFor;
import interactions.comunes.WaitForResponse;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import utils.EvidenciaUtils;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static userinterfaces.IVRPage.*;
import static userinterfaces.IVRPage.BTN_GRABAR;
import static utils.Constantes.*;

public class MenuPrincipalOpcionUno implements Task {
    private static final String paso = "Pulsar Grbrar llamada";
    private static final String paso1 = "Marcar Opcion 1";
    private static final String paso2 = "Esperar a que se cuelgue la llamda";

    @Override
    public <T extends Actor> void performAs(T actor) {
        EvidenciaUtils.registrarCaptura(paso);
        actor.attemptsTo(
                WaitFor.aTime(400),
                ClickSostenido.sobre(BTN_GRABAR, 1),
                WaitFor.aTime(15000),
                Click.on(TECLADO_TELEFONO_EN_GRABACION),
                Click.on(BOTON_1_EN_GRABACION)
        );
        EvidenciaUtils.registrarCaptura(paso1);
        actor.attemptsTo(
                WaitForResponse.withText(FIN_LLAMADA_GRABACION)
        );
        EvidenciaUtils.registrarCaptura(paso2);

    }

    public static Performable marcar() {
        return instrumented(MenuPrincipalOpcionUno.class);
    }
}
