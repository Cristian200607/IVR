package MarcarLinea;

import io.appium.java_client.MobileElement;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.targets.Target;

import java.util.HashMap;
import java.util.Map;

import static userinterfaces.IVRPage.*;

public class DialPadHelper {

    private static final Map<Character, Target> DIGITOS = new HashMap<>();

    static {
        DIGITOS.put('0', BOTON_0_EN_GRABACION);
        DIGITOS.put('1', BOTON_1_EN_GRABACION);
        DIGITOS.put('2', BOTON_2_EN_GRABACION);
        DIGITOS.put('3', BOTON_3_EN_GRABACION);
        DIGITOS.put('4', BOTON_4_EN_GRABACION);
        DIGITOS.put('5', BOTON_5_EN_GRABACION);
        DIGITOS.put('6', BOTON_6_EN_GRABACION);
        DIGITOS.put('7', BOTON_7_EN_GRABACION);
        DIGITOS.put('8', BOTON_8_EN_GRABACION);
        DIGITOS.put('9', BOTON_9_EN_GRABACION);
    }

    /**
     * Marca un número completo lo más rápido posible, usando click directo en MobileElement.
     */
    public static void marcarNumeroMaximo(Actor actor, String numero) {
        for (char digito : numero.toCharArray()) {
            Target botonTarget = DIGITOS.get(digito);
            if (botonTarget != null) {
                // click directo en el MobileElement, evitando Serenity Click
                MobileElement boton = (MobileElement) botonTarget.resolveFor(actor).getWrappedElement();
                boton.click();
            }
        }
    }
}
