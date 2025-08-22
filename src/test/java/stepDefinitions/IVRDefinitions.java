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
import tasks.MenuPrincipalOpciones.ConsultarSaldoOpcionAsterisco;
import tasks.MenuPrincipalOpciones.MenuPrincipalDespuesDeDigitarLinea;
import tasks.MenuPrincipalOpciones.Numeral.Opcion1;
import tasks.MenuPrincipal;
import tasks.MenuPrincipalOpciones.Numeral.Opcion2;
import tasks.MenuPrincipalOpciones.Numeral.Opcion3;
import tasks.MenuPrincipalOpciones.Numeral.Opcion4;
import tasks.OpcionesParaMarcarEnMenuInicio.MenuPrincipalOpcionDos;
import tasks.OpcionesParaMarcarEnMenuInicio.MenuPrincipalOpcionTres;
import tasks.OpcionesParaMarcarEnMenuInicio.MenuPrincipalOpcionUno;
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

  @And("^Se marca la opcion 4 en comprar servicios y empieza a grabar$")
  public void ComprarServiciosOpcion4() {
    theActorInTheSpotlight().attemptsTo(
            ComprarServiciosOpcion4.marcar()
    );
  }

  //Marcar Opciones En Escenario Cosultar Lineas***************************************************
  @And("^Se marca la opcion tres y empieza a grabar$")
  public void ConsultarLineasOpcionTres() {
    theActorInTheSpotlight().attemptsTo(
            MenuPrincipalOpcionTres.marcar()
    );
  }

  @And("^Se marca la opcion cuatro en Consultar Lineas y empieza a grabar$")
  public void ConsultarLineasOpcionCuatro() {
    theActorInTheSpotlight().attemptsTo(
            ConsultarSaldoOpcionAsterisco.marcar("3125457947")
    );
  }

  @And("^Se marca numeral opcion 1$")
  public void MarcaNumeralOp1() {
    theActorInTheSpotlight().attemptsTo(
            Opcion1.marcar("3125457947")
    );
  }

  @And("^Se marca numeral opcion 2$")
  public void MarcaNumeralOp2() {
    theActorInTheSpotlight().attemptsTo(
            Opcion2.marcar("3125457947")
    );
  }

  @And("^Se marca numeral opcion 3$")
  public void MarcaNumeralOp3() {
    theActorInTheSpotlight().attemptsTo(
            Opcion3.marcar("3125457947")
    );
  }

  @And("^Se marca numeral opcion 4$")
  public void MarcaNumeralOp4() {
    theActorInTheSpotlight().attemptsTo(
            Opcion4.marcar("3125457947")
    );
  }

  @And("^Se valida el menu principal despues de digitar la linea a consultar$")
  public void ValidarOpcionesMenuPrincipalDespuesDeDigitarLinea() {
    theActorInTheSpotlight().attemptsTo(
            MenuPrincipalDespuesDeDigitarLinea.marcar("3213268056")
    );
  }
  //
}
