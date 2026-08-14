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

            DesiredCapabilities capabilities = new DesiredCapabilities();

            // -------------------------
            // ANDROID
            // -------------------------
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("automationName", "UiAutomator2");
            capabilities.setCapability("udid", "10AECM1AP5000XT");

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
}