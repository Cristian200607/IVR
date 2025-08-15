package runners;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.runner.RunWith;
import utils.BeforeSuite;
import utils.DataToFeature;
import net.serenitybdd.cucumber.CucumberWithSerenity; // <-- agregado

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepDefinitions", "utils", "hooks"},
        snippets = SnippetType.CAMELCASE,
        tags = "@IVR_03")
@RunWith(CucumberWithSerenity.class) // <-- cambiado
public class GeneralRunner {
  @BeforeSuite
  public static void test() throws InvalidFormatException, IOException {
    DataToFeature.overrideFeatureFiles("src/test/resources/features");
  }
}