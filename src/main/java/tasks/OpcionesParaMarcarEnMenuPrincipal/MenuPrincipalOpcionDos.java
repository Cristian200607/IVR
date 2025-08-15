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
import static utils.Constantes.FIN_LLAMADA_GRABACION;

public class MenuPrincipalOpcionDos implements Task {

    private static final String paso = "Pulsar Grbrar llamada";
    private static final String paso1 = "Marcar Opcion 2";
    private static final String paso2 = "Esperar a que se cuelgue la llamda";

    @Override
    public <T extends Actor> void performAs(T actor) {
        EvidenciaUtils.registrarCaptura(paso);
        actor.attemptsTo(
                WaitFor.aTime(400),
                ClickSostenido.sobre(BTN_GRABAR, 1),
                WaitFor.aTime(14000),
                Click.on(TECLADO_TELEFONO_EN_GRABACION),
                Click.on(BOTON_2_EN_GRABACION)
        );
        EvidenciaUtils.registrarCaptura(paso1);
        actor.attemptsTo(
                WaitFor.aTime(27000),
                Click.on(BTN_COLGAR),
                WaitForResponse.withText(FIN_LLAMADA_GRABACION)
        );
        EvidenciaUtils.registrarCaptura(paso2);
    }

    public static Performable marcar() {
        return instrumented(MenuPrincipalOpcionDos.class);
    }
}
