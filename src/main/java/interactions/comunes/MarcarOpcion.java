package interactions.comunes;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static userinterfaces.IVRPage.*;

import java.util.HashMap;
import java.util.Map;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.Step;
import utils.EvidenciaUtils;

/**
 * Marca una tecla del teclado en llamada y deja captura como evidencia de que sí se marcó. El
 * tiempo de la captura se descuenta de la siguiente espera para no correr los tiempos del IVR.
 */
public class MarcarOpcion implements Interaction {

  private static final Map<Target, String> TECLAS = new HashMap<>();

  static {
    TECLAS.put(BOTON_0_EN_GRABACION, "0");
    TECLAS.put(BOTON_1_EN_GRABACION, "1");
    TECLAS.put(BOTON_2_EN_GRABACION, "2");
    TECLAS.put(BOTON_3_EN_GRABACION, "3");
    TECLAS.put(BOTON_4_EN_GRABACION, "4");
    TECLAS.put(BOTON_5_EN_GRABACION, "5");
    TECLAS.put(BOTON_6_EN_GRABACION, "6");
    TECLAS.put(BOTON_7_EN_GRABACION, "7");
    TECLAS.put(BOTON_8_EN_GRABACION, "8");
    TECLAS.put(BOTON_9_EN_GRABACION, "9");
    TECLAS.put(BOTON_ASTERISCO_EN_GRABACION, "*");
    TECLAS.put(BOTON_NUMERAL_EN_GRABACION, "#");
  }

  private final Target boton;

  public MarcarOpcion(Target boton) {
    this.boton = boton;
  }

  public static MarcarOpcion conBoton(Target boton) {
    return instrumented(MarcarOpcion.class, boton);
  }

  @Override
  @Step("Marcar la opción #boton")
  public <T extends Actor> void performAs(T actor) {
    actor.attemptsTo(Click.on(boton));
    String tecla = TECLAS.getOrDefault(boton, boton.getName());
    EvidenciaUtils.registrarCapturaSinAlterarTiempos("Se marca la opción " + tecla);
  }
}
