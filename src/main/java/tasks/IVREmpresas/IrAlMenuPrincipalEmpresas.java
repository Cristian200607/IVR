package tasks.IVREmpresas;


import utils.EvidenciaUtils;
import interactions.comunes.MarcarOpcion;
import MarcarLinea.DialPadHelper;
import interactions.comunes.WaitFor;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import userinterfaces.WaitTimes;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static userinterfaces.IVRPage.*;

/** Tramo común de IVR Empresas: desde que contesta hasta que termina de leer el menú principal. */
public class IrAlMenuPrincipalEmpresas implements Task {

    private final String numero;

    public IrAlMenuPrincipalEmpresas(String numero) {
        this.numero = numero;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                WaitFor.aTime(WaitTimes.INICIO_LLAMADA),
                Click.on(TECLADO_TELEFONO_EN_GRABACION),
                MarcarOpcion.conBoton(BOTON_2_EN_GRABACION)
        );
        actor.attemptsTo(
                WaitFor.aTime(11000),
                MarcarOpcion.conBoton(BOTON_2_EN_GRABACION),
                WaitFor.aTime(8000)
        );
        DialPadHelper.marcarNumeroMaximo(actor, numero);
        EvidenciaUtils.registrarCapturaSinAlterarTiempos("Se digita el documento " + numero);
        actor.attemptsTo(
                MarcarOpcion.conBoton(BOTON_NUMERAL_EN_GRABACION),
                WaitFor.aTime(14000),
                MarcarOpcion.conBoton(BOTON_1_EN_GRABACION),
                WaitFor.aTime(35000)
        );
    }

    public static Performable con(String numero) {
        return instrumented(IrAlMenuPrincipalEmpresas.class, numero);
    }
}
