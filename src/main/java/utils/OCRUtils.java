package utils;


import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;

import java.io.File;

public class OCRUtils {

    public static String leerTexto(String imagePath) {
        try {
            ITesseract tesseract = new Tesseract();
            tesseract.setDatapath("C:/tessdata/"); // Ruta donde está spa.traineddata
            tesseract.setLanguage("spa"); // Usa "eng" si es en inglés

            return tesseract.doOCR(new File(imagePath));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
