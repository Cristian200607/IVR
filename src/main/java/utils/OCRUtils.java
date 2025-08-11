package utils;

import java.io.File;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;

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
