package userinterfaces;

import io.appium.java_client.AppiumBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;

public class IVRPage extends PageObject {

    public static final Target TECLADO_TELEFONO =
            Target.the("Teclado del telefono")
                    .located(AppiumBy.id("dialpad_fab"));

    public static final Target BTN_LLAMAR =
            Target.the("Boton de marcar")
                    .located(AppiumBy.id("dialpad_voice_call_button"));

    public static final Target BTN_COLGAR =
            Target.the("Boton colgar")
                    .located(AppiumBy.id("com.google.android.dialer:id/incall_end_call"));

    public static final Target SUBTITULADO =
            Target.the("caja de texto ivr")
                    .located(AppiumBy.id("com.google.android.dialer:id/default_request_text"));


    public static final Target TXT_APP_PREDETERMINADA =
            Target.the("Texto Establecer Teléfono como app predeterminada")
                    .located(AppiumBy.id("com.google.android.dialer:id/default_request_text"));

    public static final Target BTN_HACER_PREDETERMINADA =
            Target.the("Boton Hacer predeterminada")
                    .located(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]"));

    public static final Target IMG_ESPERA =
            Target.the("Ejecutando código USSD…")
                    .located(AppiumBy.id("android:id/progress"));
}
