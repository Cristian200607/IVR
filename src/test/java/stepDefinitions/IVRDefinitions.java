package stepDefinitions;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

import cucumber.api.Scenario;
import cucumber.api.java.en.*;
import freemarker.log.Logger;
import cucumber.api.java.Before;
import java.io.File;
import models.User;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import tasks.MenuPrincipal;
import tasks.OpcionesParaMarcarEnMenuPrincipal.MenuPrincipalOpcionDos;
import tasks.OpcionesParaMarcarEnMenuPrincipal.MenuPrincipalOpcionTres;
import tasks.OpcionesParaMarcarEnMenuPrincipal.MenuPrincipalOpcionUno;
import tasks.ComprarServicios.*;
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

  @When("^Se valida el flujo menu principal$")
  public void MenuPrincipal() {
    theActorInTheSpotlight().attemptsTo(
            MenuPrincipal.validar()
    );
  }

  //Marcar Opciones
  @And("^Se marca la opcion uno y empieza a grabar$")
  public void MarcarOpcionUno() {
    theActorInTheSpotlight().attemptsTo(
            MenuPrincipalOpcionUno.marcar()
    );
  }
  //Marcar Opciones En Escenario Comprar servicios******************************************
  @And("^Se marca la opcion dos y empieza a grabar$")
  public void MarcarOpcionDos() {
    theActorInTheSpotlight().attemptsTo(
            MenuPrincipalOpcionDos.marcar()
    );
  }

  @And("^Se marca la opcion 1 en comprar servicios y empieza a grabar$")
  public void ComprarServiciosOpcion1() {
    theActorInTheSpotlight().attemptsTo(
            ComprarServiciosOpcion1.marcar()
    );
  }
  //***************************************************************************************

  @And("^Se marca la opcion tres y empieza a grabar$")
  public void MarcarOpcionTres() {
    theActorInTheSpotlight().attemptsTo(
            MenuPrincipalOpcionTres.marcar()
    );
  }
  //
}
