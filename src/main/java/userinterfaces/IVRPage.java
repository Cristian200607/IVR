package userinterfaces;

import org.openqa.selenium.By;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;

public class IVRPage extends PageObject {

    public static final Target BOTON_TEST = Target.the("boton de prueba").locatedBy("dummy");

    public static final Target TECLADO_TELEFONO = Target.the("Teclado del telefono")
            .located(By.id("com.google.android.dialer:id/tab_dialpad"));

    public static final Target TECLADO_TELEFONO_EN_GRABACION = Target.the("Teclado del telefono en grabacion")
                    .located(By.xpath("//android.view.View[@clickable='true']" + "[.//android.view.View[@content-desc='Teclado']]"));

    public static final Target TECLADO_6 = Target.the("Quinto botón del incall")
            .located(By.id("com.google.android.dialer:id/six"));

    public static final Target TECLADO_1 = Target.the("Quinto botón del incall")
            .located(By.id("com.google.android.dialer:id/one"));

    public static final Target BOTON_SILENCIAR_EN_LLAMADA = Target.the("Quinto botón del incall")
            .located(By.xpath("(//android.widget.ImageView[@resource-id='com.samsung.android.incallui:id/incall_button_icon'])[5]"));

    public static final Target TECLADO_EN_LLAMADA = Target.the("teclado")
            .located(By.xpath("//android.widget.LinearLayout[@content-desc='Teclado']"));

    public static final Target BOTON_2_EN_LLAMADA = Target.the("vista con content-desc 2")
            .located(By.id("com.google.android.dialer:id/two"));

    public static final Target BTN_LLAMAR = Target.the("Boton de marcar")
            .located(By.id("com.google.android.dialer:id/dialpad_voice_call_button"));

    public static final Target BTN_COLGAR = Target.the("Boton colgar")
            .located(By.xpath("//android.view.View[@content-desc=\"Finalizar llamada\"]"));

    public static final Target FIN_LLAMADA_BUTTON = Target.the("Botón Fin de llamada")
            .located(By.xpath("//android.widget.ImageButton[@content-desc='Fin. llamada']"));

    public static final Target BOTON_ASTERISCO_EN_GRABACION = Target.the("botón *")
            .located(By.id("com.google.android.dialer:id/star"));

    public static final Target BOTON_NUMERAL_EN_GRABACION = Target.the("botón #")
            .located(By.id("com.google.android.dialer:id/pound"));

    public static final Target BOTON_0_EN_GRABACION = Target.the("botón cero")
            .located(By.id("com.google.android.dialer:id/zero"));

    public static final Target BOTON_1_EN_GRABACION = Target.the("botón uno")
            .located(By.id("com.google.android.dialer:id/one"));

    public static final Target BOTON_2_EN_GRABACION = Target.the("botón dos")
            .located(By.id("com.google.android.dialer:id/two"));

    public static final Target BOTON_3_EN_GRABACION = Target.the("botón tres")
            .located(By.id("com.google.android.dialer:id/three"));

    public static final Target BOTON_4_EN_GRABACION = Target.the("botón tres")
            .located(By.id("com.google.android.dialer:id/four"));

    public static final Target BOTON_5_EN_GRABACION = Target.the("botón cinco")
            .located(By.id("com.google.android.dialer:id/five"));

    public static final Target BOTON_6_EN_GRABACION = Target.the("botón seis")
            .located(By.id("com.google.android.dialer:id/six"));

    public static final Target BOTON_7_EN_GRABACION = Target.the("botón siete")
            .located(By.id("com.google.android.dialer:id/seven"));

    public static final Target BOTON_8_EN_GRABACION = Target.the("botón ocho")
            .located(By.id("com.google.android.dialer:id/eight"));

    public static final Target BOTON_9_EN_GRABACION = Target.the("botón nueve")
            .located(By.id("com.google.android.dialer:id/nine"));

    public static final Target FIN_LLAMADA_EN_GRABACION = Target.the("botón fin llamada")
            .located(By.xpath("//android.widget.TextView[@content-desc='Fin de llamada']"));

    public static final Target BTN_GRABAR =
            Target.the("caja de texto ivr")
                    .located(By.id("com.google.android.dialer:id/default_request_text"));

    public static final Target TXT_APP_PREDETERMINADA =
            Target.the("Texto Establecer Teléfono como app predeterminada")
                    .located(By.id("com.google.android.dialer:id/default_request_text"));

    public static final Target BTN_HACER_PREDETERMINADA =
            Target.the("Boton Hacer predeterminada")
                    .located(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]"));

    public static final Target IMG_ESPERA =
            Target.the("Ejecutando código IVR…")
                    .located(By.id("android:id/progress"));

    public static final Target BOTON_RETROCESO_TECLADO = Target.the("botón retroceso")
            .located(By.xpath("//android.widget.ImageButton[@content-desc='Retroceso']"));

    public static final Target COLAPSAR_EN_LLAMADA = Target.the("botón colapsar")
            .located(By.xpath("//android.widget.ImageButton[@content-desc='Colapsar']"));

    // 1. Botón de la primera opción
    public static final Target BTN_TRES_PUNTOS = Target.the("Botón tres puntos")
            .located(By.xpath("//androidx.compose.ui.platform.ComposeView[@resource-id=\"com.google.android.dialer:id/incall_main_buttons_container\"]/android.view.View/android.view.View/android.view.View[1]/android.view.View[7]/android.widget.Button"));

    // 2. Botón Grabar
    public static final Target BTN_CONFIRMAR_GRABAR_LLAMADA = Target.the("Botón Grabar")
            .located(By.xpath("//android.view.View[@content-desc=\"Grabar\"]"));

    // 3. Botón del contenedor inferior
    public static final Target BTN_SALIR_MENU_TRES_PUNTOS = Target.the("Botón grabar confirmar")
            .located(By.xpath("//android.widget.FrameLayout[@resource-id=\"com.google.android.dialer:id/bottom_sheet_fragment_container\"]/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.widget.Button"));


    public static final Target BTN_TRES_PUNTOS_CLICK = Target.the("Botón tres puntos")
            .located(By.xpath(
                    "//android.widget.Button[" +
                            "ancestor::androidx.compose.ui.platform.ComposeView[@resource-id='com.google.android.dialer:id/incall_main_buttons_container']" +
                            "]/ancestor::*[@clickable='true'][1]"
            ));

    public static final Target VOLVER_PAGINA_PRINCIPAL =
            Target.the("Volver a página principal")
                    .located(By.xpath("(//android.widget.ImageView[@resource-id=\"com.google.android.dialer:id/navigation_bar_item_icon_view\"])[1]"));

    public static final Target ULTIMA_LLAMADA =
            Target.the("Click en la última llamada")
                    .located(By.xpath("(//android.view.ViewGroup[@resource-id=\"com.google.android.dialer:id/call_log_entry_root_constraint_layout\"])[1]"));

    public static final Target BTN_COMPARTIR_AUDIO =
            Target.the("Botón Compartir archivo de audio")
                    .located(By.xpath("//android.widget.ImageButton[@content-desc=\"Compartir archivo de audio\"]"));

    public static final Target UBICACION_GRABACION =
            Target.the("Ubicación donde se guardará la grabación")
                    .located(By.xpath("(//android.widget.ImageView[@resource-id=\"android:id/icon\"])[7]"));
}