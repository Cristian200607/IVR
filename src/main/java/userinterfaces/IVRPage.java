package userinterfaces;

import io.appium.java_client.AppiumBy;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;

public class IVRPage extends PageObject {

  public static final Target TECLADO_TELEFONO = Target.the("Teclado del telefono")
          .located(AppiumBy.id("com.hb.dialer.free:id/digits"));

  public static final Target TECLADO_EN_LLAMADA = Target.the("teclado")
          .located(AppiumBy.xpath("//android.widget.LinearLayout[@content-desc='Teclado']"));

  public static final Target BOTON_2_EN_LLAMADA = Target.the("vista con content-desc 2")
          .located(By.xpath("//android.view.View[@content-desc='2']"));

  public static final Target BTN_LLAMAR = Target.the("Boton de marcar")
          .located(AppiumBy.id("com.hb.dialer.free:id/call"));

  public static final Target BTN_COLGAR = Target.the("Boton colgar")
          .located(AppiumBy.xpath("//android.widget.ImageButton[@content-desc=\"Fin. llamada\"]"));

  public static final Target TECLADO_TELEFONO_EN_GRABACION = Target.the("teclado")
          .located(AppiumBy.xpath("(//android.widget.ImageView[@resource-id='com.samsung.android.incallui:id/incall_button_icon'])[6]"));

  public static final Target BOTON_1_EN_GRABACION = Target.the("botón uno")
          .located(AppiumBy.xpath("//android.widget.TextView[@resource-id='com.samsung.android.incallui:id/dialpad_key_number' and @text='1']"));

  public static final Target BOTON_2_EN_GRABACION = Target.the("botón dos")
          .located(AppiumBy.xpath("//android.widget.TextView[@resource-id='com.samsung.android.incallui:id/dialpad_key_number' and @text='2']"));

  public static final Target FIN_LLAMADA_EN_GRABACION = Target.the("botón fin llamada")
          .located(AppiumBy.xpath("//android.widget.TextView[@content-desc='Fin de llamada']"));

  public static final Target SUBTITULADO =
          Target.the("caja de texto ivr")
                  .located(AppiumBy.id("com.google.android.dialer:id/default_request_text"));


  public static final Target TXT_APP_PREDETERMINADA =
          Target.the("Texto Establecer Teléfono como app predeterminada")
                  .located(AppiumBy.id("com.google.android.dialer:id/default_request_text"));

  public static final Target BTN_HACER_PREDETERMINADA =
          Target.the("Boton Hacer predeterminada")
                  .located(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]"));


  public static final Target BTN_GRABAR = Target.the("Botón colgar")
          .located(By.xpath("//android.widget.LinearLayout[@content-desc=\"Iniciar grabación\"]"));


  public static final Target IMG_ESPERA =
          Target.the("Ejecutando código IVR…")
                  .located(AppiumBy.id("android:id/progress"));

  public static final Target BOTON_RETROCESO_TECLADO = Target.the("botón retroceso")
          .located(AppiumBy.xpath("//android.widget.ImageButton[@content-desc='Retroceso']"));

  public static final Target COLAPSAR_EN_LLAMADA = Target.the("botón colapsar")
          .located(AppiumBy.xpath("//android.widget.ImageButton[@content-desc='Colapsar']"));
}