package utils;

import java.io.IOException;

public class CapturaOCR {

    public static String tomarScreenshot() {
        try {
            String output = "C:/grabacionesIVR/screen.png";
            Process p = Runtime.getRuntime().exec("adb exec-out screencap -p > " + output);
            p.waitFor();
            return output;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
