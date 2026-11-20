package utils;

import io.appium.java_client.android.AndroidDriver;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import net.thucydides.core.webdriver.DriverSource;
import org.openqa.selenium.remote.DesiredCapabilities;

public class MyDriver implements DriverSource {

    private static AndroidDriver driver;

    public static AndroidDriver getDriver() {
        return driver;
    }

    @Override
    public AndroidDriver newDriver() {
        try {

            // El "freezer" de apps en segundo plano (Android 12+) puede matar al servidor
            // UiAutomator2 justo al iniciar la sesion ("sent binder code 1 with flags 2 to
            // frozen apps and got error -32"), dejando el celular en el home en vez de abrir
            // la app. Se desactiva antes de crear el driver para evitar esa caida.
            deshabilitarFreezerDeApps();

            DesiredCapabilities capabilities = new DesiredCapabilities();

            // -------------------------
            // ANDROID
            // -------------------------
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("automationName", "UiAutomator2");
            capabilities.setCapability("udid", Constantes.UDID);

            // -------------------------
            // APLICACIÓN TELÉFONO
            // -------------------------
            capabilities.setCapability(
                    "appPackage",
                    "com.google.android.dialer"
            );

            capabilities.setCapability(
                    "appActivity",
                    "com.google.android.dialer.extensions.GoogleDialtactsActivity"
            );

            // -------------------------
            // CONFIGURACIÓN APP
            // -------------------------
            capabilities.setCapability("noReset", true);
            capabilities.setCapability("autoGrantPermissions", true);
            capabilities.setCapability("autoDismissAlerts", true);
            capabilities.setCapability("newCommandTimeout", 3000);

            // No captura logcat en cada sesión (arranque más rápido).
            // skipDeviceInitialization se quitó: con él el servidor UiAutomator2 se caía a mitad de
            // la llamada ("instrumentation process is not running") en el vivo.
            capabilities.setCapability("skipLogcatCapture", true);

            // -------------------------
            // CREAR DRIVER
            // -------------------------
            driver = new AndroidDriver(
                    new URL("http://127.0.0.1:4723/wd/hub"),
                    capabilities
            );

            // -------------------------
            // TIMEOUT
            // -------------------------
            driver.manage()
                    .timeouts()
                    .implicitlyWait(20, TimeUnit.SECONDS);

            return driver;

        } catch (IOException e) {
            throw new Error(e);
        }
    }

    @Override
    public boolean takesScreenshots() {
        return true;
    }

    private void deshabilitarFreezerDeApps() {
        try {
            new ProcessBuilder(
                    "adb", "-s", Constantes.UDID,
                    "shell", "device_config", "put",
                    "activity_manager", "cached_apps_freezer", "disabled")
                    .redirectErrorStream(true)
                    .start()
                    .waitFor(10, TimeUnit.SECONDS);
        } catch (Exception e) {
            System.err.println("No se pudo deshabilitar el freezer de apps: " + e.getMessage());
        }
    }
}