package stepDefinitions;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

import freemarker.log.Logger;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import java.io.File;
import models.User;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import tasks.GrabarLlamada;
import tasks.MarcarOpcionDos;
import tasks.RealizarLlamada;
import utils.WordAppium;

public class IVRDefinitions {

  User addCredentials;
  private static final Logger LOGGER = Logger.getLogger(WordAppium.class.getName());

  @Before
  public void initScenario(Scenario scenario) {
    OnStage.setTheStage(new OnlineCast());
    LOGGER.info("Limpiando carpeta de capturas...");
    WordAppium.inicializarPlantillaReporte();
    File folder = new File("Capturas");
    if (folder.exists() && folder.isDirectory()) {
      for (File file : folder.listFiles()) {
        if (file.isFile() && file.getName().endsWith(".jpg")) {
          file.delete();
        }
      }
    }
  }

  @Given("^Se realiza la llamada al numero (.*)$")
  public void realizaUnaLlamadaAlNumero(String numero) {
    theActorCalled("").attemptsTo(RealizarLlamada.Llamar(numero));
  }

  @When("^Se comienza a grabar la llamda$")
  public void MenuPrincipal() {
    theActorInTheSpotlight().attemptsTo(
            GrabarLlamada.grabar()
    );
  }

  //Marcar Opciones
  @And("^Se marca la opcion dos y empieza a grabar$")
  public void MarcarOpcionDos() {
    theActorInTheSpotlight().attemptsTo(
            MarcarOpcionDos.marcar()
    );
  }
  //
}
