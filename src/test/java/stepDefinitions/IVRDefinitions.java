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
import tasks.MenuPrincipalOpciones.Asterico.Op2ComprarPaquetes.AsteriscoComprarPaquetes;
import tasks.MenuPrincipalOpciones.Asterico.Op2ComprarPaquetes.AsteriscoComprarPaquetesOp2Op3;
import tasks.MenuPrincipalOpciones.Asterico.Op2ComprarPaquetes.AsteriscoComprarPaquetesOp2Op4;
import tasks.MenuPrincipalOpciones.Asterico.Op1ConsultarSaldo.AsteriscoConsultarSaldo;
import tasks.MenuPrincipalOpciones.Asterico.Op3RecargarLineaPrepago.RecargarLineaPrepago;
import tasks.MenuPrincipalOpciones.Asterico.Op4ProductosFinanciados.ProductosFinanciados;
import tasks.MenuPrincipalOpciones.Asterico.Op5PaquetesActivos.PaquetesActivos;
import tasks.MenuPrincipalOpciones.Asterico.Op5PaquetesActivos.PaquetesActivosOp2;
import tasks.MenuPrincipalOpciones.MenuPrincipalDespuesDeDigitarLinea;
import tasks.MenuInicio;
import tasks.MenuPrincipalOpciones.Numeral.Op1SoporteDeVoz;
import tasks.MenuPrincipalOpciones.Numeral.Op2PasarseAClaro;
import tasks.MenuPrincipalOpciones.Numeral.Op3RoboDeCelular;
import tasks.MenuPrincipalOpciones.Numeral.Op4ReconectarLinea;
import tasks.MenuPrincipalOpciones.Opcion2.Op1PerdidaCelular;
import tasks.MenuPrincipalOpciones.Opcion2.Op2Reconeccion;
import tasks.MenuPrincipalOpciones.Opcion2.Op3RegistrarEquipo;
import tasks.MenuPrincipalOpciones.Opcion2.Op4ReciclajeDeEquipos;
import tasks.MenuPrincipalOpciones.Opcion3.*;
import tasks.MenuPrincipalOpciones.Opcion1.ActivarRoamingInternacional.Op1ActivarRoamingInternacional;
import tasks.MenuPrincipalOpciones.Opcion1.ActivarRoamingInternacional.Op2ActivarRoamingInternacional;
import tasks.MenuPrincipalOpciones.Opcion1.AdquirirEquipos.AdquirirEquipos;
import tasks.MenuPrincipalOpciones.Opcion1.AdquirirServiciosClaro.*;
import tasks.MenuPrincipalOpciones.Opcion1.EligidosMovilFamilia.ElegidosMovilFamilia;
import tasks.MenuPrincipalOpciones.Opcion1.InformacionGeneral.InformacionGeneral;
import tasks.MenuPrincipalOpciones.Opcion4.ClaroPay.ClaroPay;
import tasks.MenuPrincipalOpciones.Opcion4.ClaroPayMasterCard.ClaroPayMasterCard;
import tasks.MenuPrincipalOpciones.Opcion4.ClaroPayMasterCard.ClaroPayMasterCardOp2;
import tasks.OpcionesParaMarcarEnMenuInicio.ComprarServicios.ComprarServiciosOpcion1;
import tasks.OpcionesParaMarcarEnMenuInicio.ComprarServicios.ComprarServiciosOpcion2;
import tasks.OpcionesParaMarcarEnMenuInicio.ComprarServicios.ComprarServiciosOpcion3;
import tasks.OpcionesParaMarcarEnMenuInicio.ComprarServicios.ComprarServiciosOpcion4;
import tasks.OpcionesParaMarcarEnMenuInicio.ValidaMenuDeLineas;
import tasks.OpcionesParaMarcarEnMenuInicio.IngresoWhatsApp;
import tasks.RealizarLlamada;
import utils.EstadoPrueba;
import utils.WordAppium;

public class  IVRDefinitions {

  User addCredentials;
  private static final Logger LOGGER = Logger.getLogger(WordAppium.class.getName());

  // 📌 Variable general para el número de celular
  public static final String NUMERO_CELULAR = "3204108085";


  @Before
  public void initScenario(Scenario scenario) {
    OnStage.setTheStage(new OnlineCast());
    LOGGER.info("Limpiando carpeta de capturas...");
    WordAppium.inicializarPlantillaReporte();

    EstadoPrueba.reset();  // esto ya guarda horaInicio automáticamente
    // 👉 Guardar en EstadoPrueba el número de celular y la hora
    EstadoPrueba.linea = NUMERO_CELULAR;


    // Mantener la pantalla encendida (infinito)
    String udid = "R9WW70LL61V"; // tu UDID
    try {
      String keepScreenOn = String.format(
              "adb -s %s shell settings put system_off_timeout 2147483647",
              udid
      );
      Process procesoKeepAlive = Runtime.getRuntime().exec(keepScreenOn);
      int resultadoKeepAlive = procesoKeepAlive.waitFor();
      if (resultadoKeepAlive == 0) {
        System.out.println("Pantalla configurada para nunca apagarse.");
      } else {
        System.err.println("Error configurando el timeout de pantalla. Código: " + resultadoKeepAlive);
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("Error ejecutando adb para mantener pantalla encendida", e);
    }

    // Limpiar capturas locales
    File folder = new File("Capturas");
    if (folder.exists() && folder.isDirectory()) {
      for (File file : folder.listFiles()) {
        if (file.isFile() && file.getName().endsWith(".jpg")) {
          file.delete();
        }
      }
    }

    // Limpiar audios en el celular
    String rutaCelular = "/sdcard/Recordings/Call/";
    try {
      String crearCarpeta = String.format("adb -s %s shell mkdir -p %s", udid, rutaCelular);
      Runtime.getRuntime().exec(crearCarpeta).waitFor();

      String borrarArchivos = String.format("adb -s %s shell rm -rf %s*", udid, rutaCelular);
      System.out.println("Limpiando audios del celular con: " + borrarArchivos);
      Process proceso = Runtime.getRuntime().exec(borrarArchivos);
      int resultado = proceso.waitFor();
      if (resultado == 0) {
        System.out.println("Carpeta de audios del celular limpia correctamente: " + rutaCelular);
      } else {
        System.err.println("Error limpiando carpeta del celular. Código: " + resultado);
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("Error ejecutando adb para limpiar carpeta del celular", e);
    }
  }



  @Given("^Se realiza la llamada al numero (.*)$")
  public void realizaUnaLlamadaAlNumero(String numero) {
    theActorCalled("").attemptsTo(RealizarLlamada.Llamar(numero));
  }

  @When("^Se Valida Menu Inicio$")
  public void MenuPrincipal() {
    theActorInTheSpotlight().attemptsTo(
            MenuInicio.validar()
    );
  }

  //Menu Inicio
  @And("^Op1, Ingreso a WhatsApp$")
  public void MarcarOpcionUno() {
    theActorInTheSpotlight().attemptsTo(
            IngresoWhatsApp.marcar()
    );
  }

  //Marcar Opciones En Escenario Comprar servicios******************************************

  @And("^Comprar Servicios Claro, Servicios Hogar$")
  public void ComprarServiciosOpcion1() {
    theActorInTheSpotlight().attemptsTo(
            ComprarServiciosOpcion1.marcar()
    );
  }

  @And("^Comprar Servicios Claro, Plan Postpago Movil$")
  public void ComprarServiciosOpcion2() {
    theActorInTheSpotlight().attemptsTo(
            ComprarServiciosOpcion2.marcar()
    );
  }

  @And("^Comprar Servicios Claro, Compra De Equipos$")
  public void ComprarServiciosOpcion3() {
    theActorInTheSpotlight().attemptsTo(
            ComprarServiciosOpcion3.marcar()
    );
  }

  @And("^Comprar Servicios Claro, Adquirir Servicios Y Pasarte a Claro$")
  public void ComprarServiciosOpcion4() {
    theActorInTheSpotlight().attemptsTo(
            ComprarServiciosOpcion4.marcar()
    );
  }
  //

  //Menu para consultar Lineas***************************************************
  @And("^Valida Menu Para Consultar Lineas$")
  public void ConsultarLineasOpcionTres() {
    theActorInTheSpotlight().attemptsTo(
            ValidaMenuDeLineas.marcar()
    );
  }

  //Opciones Menu Principal***********************************************************************
  // * *********************************************************************************
  @And("^Se valida el menu principal despues de digitar la linea a consultar$")
  public void ValidarOpcionesMenuPrincipalDespuesDeDigitarLinea() {
    theActorInTheSpotlight().attemptsTo(
            MenuPrincipalDespuesDeDigitarLinea.marcar(NUMERO_CELULAR)
    );
  }

  @And("^Menu Principal, Asterisco, Consultar Saldo$")
  public void ConsultarLineasOpcionCuatro() {
    theActorInTheSpotlight().attemptsTo(
            AsteriscoConsultarSaldo.marcar(NUMERO_CELULAR)
    );
  }

  @And("^Menu Principal, Asterisco, Comprar Paquetes, Validar Menu Comprar Paquetes$")
  public void MenuPrincipalAsteriscoOp2ValidaMetodosDePago() {
    theActorInTheSpotlight().attemptsTo(
            AsteriscoComprarPaquetes.marcar(NUMERO_CELULAR)
    );
  }

  @And("^Menu Principal, Asterisco, Comprar Paquetes, Mas opciones de paquetes$")
  public void MenuPrincipalAsteriscoOp2ValidaMasMetodosDePago() {
    theActorInTheSpotlight().attemptsTo(
            AsteriscoComprarPaquetesOp2Op3.marcar(NUMERO_CELULAR)
    );
  }

  @And("^Menu Principal, Asterisco, Comprar Paquetes, Mas Opciones De Recarga$")
  public void MenuPrincipalAsteriscoOp2ValidaMasMetodosDePagoo() {
    theActorInTheSpotlight().attemptsTo(
            AsteriscoComprarPaquetesOp2Op4.marcar(NUMERO_CELULAR)
    );
  }

  @And("^Menu Principal, Asterisco, Recarga Linea Prepago$")
  public void MenuPrincipalAsteriscoRecargaLineaPrepago() {
    theActorInTheSpotlight().attemptsTo(
            RecargarLineaPrepago.marcar(NUMERO_CELULAR)
    );
  }

  @And("^Menu Principal, Asterisco, Productos Financiados$")
  public void MenuPrincipalAsteriscoProductosFinanciados() {
    theActorInTheSpotlight().attemptsTo(
            ProductosFinanciados.marcar(NUMERO_CELULAR)
    );
  }

  @And("^Menu Principal, Asterisco, Paquetes Activos, Validar Menu Paquetes Activos$")
  public void MenuPrincipalAsteriscoProductosFinanciadosPaquetesActivosValidarMenuPaquetesActivos() {
    theActorInTheSpotlight().attemptsTo(
            PaquetesActivos.marcar(NUMERO_CELULAR)
    );
  }

  @And("^Menu Principal, Asterisco, Paquetes Activos, Retorno Menu Principal$")
  public void MenuPrincipalAsteriscoProductosFinanciadosPaquetesActivosRetornoMenuPrincipal() {
    theActorInTheSpotlight().attemptsTo(
            PaquetesActivosOp2.marcar(NUMERO_CELULAR)
    );
  }
  //

  // # **********************************************************************************************
  @And("^Menu Principal, Numeral, Soporte De Voz$")
  public void MarcaNumeralOp1() {
    theActorInTheSpotlight().attemptsTo(
            Op1SoporteDeVoz.marcar(NUMERO_CELULAR)
    );
  }

  @And("^Menu Principal, Numeral, Te Pasaste a Claro$")
  public void MarcaNumeralOp2() {
    theActorInTheSpotlight().attemptsTo(
            Op2PasarseAClaro.marcar(NUMERO_CELULAR)
    );
  }

  @And("^Menu Principal, Numeral, Robo De Celular$")
  public void MarcaNumeralOp3() {
    theActorInTheSpotlight().attemptsTo(
            Op3RoboDeCelular.marcar(NUMERO_CELULAR)
    );
  }

  @And("^Menu Principal, Numeral, Reconectar Linea$")
  public void MarcaNumeralOp4() {
    theActorInTheSpotlight().attemptsTo(
            Op4ReconectarLinea.marcar(NUMERO_CELULAR)
    );
  }
  //

  //Menu Principal Op1 ******************************************************************************************

  @And("^Menu Principal, Op1, Info General$")
  public void MenuPrincipalOp1InfoGeneral() {
    theActorInTheSpotlight().attemptsTo(
            InformacionGeneral.marcar(NUMERO_CELULAR)
    );
  }

  @And("^Menu Principal, Op1, Activar Roaming Internacional, Op1$")
  public void MenuPrincipalOp1ActivarRoamingInternacionalOp1() {
    theActorInTheSpotlight().attemptsTo(
            Op1ActivarRoamingInternacional.marcar(NUMERO_CELULAR)
    );
  }

  @And("^Menu Principal, Op1, Activar Roaming Internacional, Op2$")
  public void MenuPrincipalOp1ActivarRoamingInternacionalOp2() {
    theActorInTheSpotlight().attemptsTo(
            Op2ActivarRoamingInternacional.marcar(NUMERO_CELULAR)
    );
  }

  @And("^Menu Principal, Op1, Adquirir Equipos$")
  public void MenuPrincipalOp1AdquirirEquipos() {
    theActorInTheSpotlight().attemptsTo(
            AdquirirEquipos.marcar(NUMERO_CELULAR)
    );
  }

  @And("^Menu Principal, Op1, Pasarte a Claro y Adquirir Servicios Claro, Servicios Moviles y Servicios Hogar$")
  public void MenuPrincipalOp1PasarteaClaroyAdquirirServiciosClaroServiciosMovilesyServiciosHogar() {
    theActorInTheSpotlight().attemptsTo(
            Op1ServiciosMovilesServiciosHogar.marcar(NUMERO_CELULAR)
    );
  }

  @And("^Menu Principal, Op1, Adquirir Servicios Claro, Pasarte Claro Con Tu Mismo Numero$")
  public void MenuPrincipalOp1AdquirirServiciosClaroPasarteClaroConTuMismoNumero() {
    theActorInTheSpotlight().attemptsTo(
            Op2PasarteClaroConTuMismoNumero.marcar(NUMERO_CELULAR)
    );
  }

  @And("^Menu Principal, Op1, Adquirir Servicios Claro, Claro Video$")
  public void MenuPrincipalOp1AdquirirServiciosClaroClaroVideo() {
    theActorInTheSpotlight().attemptsTo(
            Op3ClaroVideo.marcar(NUMERO_CELULAR)
    );
  }

  @And("^Menu Principal, Op1, Adquirir Servicios Claro, Claro Musica$")
  public void MenuPrincipalOp1AdquirirServiciosClaroClaroMusica() {
    theActorInTheSpotlight().attemptsTo(
            Op4ClaroMusica.marcar(NUMERO_CELULAR)
    );
  }

  @And("^Menu Principal, Op1, Adquirir Servicios Claro, Adquiri Servicio Asistencia Claro, Activar Servicio$")
  public void MenuPrincipalOp1AdquirirServiciosClaroAdquirirServicioAsistenciaClaroActivarServicio() {
    theActorInTheSpotlight().attemptsTo(
            Op5AdquiriServicioAsistenciaClaroOp1.marcar(NUMERO_CELULAR)
    );
  }

  @And("^Menu Principal, Op1, Adquirir Servicios Claro, Adquiri Servicio Asistencia Claro, Mayor Informacion$")
  public void MenuPrincipalOp1AdquirirServiciosClaroAdquirirServicioAsistenciaClaroMayorInformacion() {
    theActorInTheSpotlight().attemptsTo(
            Op5AdquiriServicioAsistenciaClaroOp2.marcar(NUMERO_CELULAR)
    );
  }

  @And("^Menu Principal, Op1, Adquirir Servicios Claro, Adquiri Servicio Asistencia Claro, Solicitud de Asistencia$")
  public void MenuPrincipalOp1AdquirirServiciosClaroAdquirirtServicioAsistenciaClaroSolicitudDeAsistencia() {
    theActorInTheSpotlight().attemptsTo(
            Op5AdquiriServicioAsistenciaClaroOp3.marcar(NUMERO_CELULAR)
    );
  }

  @And("^Menu Principal, Op1, Adquirir Servicios Claro, Larga Distancia Internacional$")
  public void MenuPrincipalOp1AdquirirServiciosClaroOp6() {
    theActorInTheSpotlight().attemptsTo(
            Op6LargaDistanciaInternacional.marcar(NUMERO_CELULAR)
    );
  }

  @And("^Menu Principal, Op1, Elegidos Movil Familia$")
  public void MenuPrincipalOp1ElegidosMovilFamilia() {
    theActorInTheSpotlight().attemptsTo(
            ElegidosMovilFamilia.marcar(NUMERO_CELULAR)
    );
  }
  //

  //Menu Principal Op2 ****************************************************************************************************
  @And("^Menu Principal, Op2, Perdida De Celular$")
  public void MenuPrincipalOp2PerdidaDeCelular() {
    theActorInTheSpotlight().attemptsTo(
            Op1PerdidaCelular.marcar(NUMERO_CELULAR)
    );
  }

  @And("^Menu Principal, Op2, Reconeccion$")
  public void MenuPrincipalOp2Reconeccion() {
    theActorInTheSpotlight().attemptsTo(
            Op2Reconeccion.marcar(NUMERO_CELULAR)
    );
  }

  @And("^Menu Principal, Op2, Registrar Equipo$")
  public void MenuPrincipalOp2RegistrarEquipo3() {
    theActorInTheSpotlight().attemptsTo(
            Op3RegistrarEquipo.marcar(NUMERO_CELULAR)
    );
  }

  @And("^Menu Principal, Op2, Reciclaje De Equipos$")
  public void MenuPrincipalOp2OReciclajeDeEquipos() {
    theActorInTheSpotlight().attemptsTo(
            Op4ReciclajeDeEquipos.marcar(NUMERO_CELULAR)
    );
  }

  @And("^Menu Principal, Op3, Desistir, Cancelar Servicios Hogar o Movil$")
  public void MenuPrincipalOp3Op1() {
    theActorInTheSpotlight().attemptsTo(
            Op1CancelarServiciosHogarOMovil.marcar(NUMERO_CELULAR)
    );
  }

  @And("^Menu Principal, Op3, Desistir, Cancelar Servicios Adicionales$")
  public void MenuPrincipalOp3Op1Op2() {
    theActorInTheSpotlight().attemptsTo(
            Op1CancelarServiciosAdicionales.marcar(NUMERO_CELULAR)
    );
  }

  @And("^Menu Principal, Op3, Radicar Peticiones, Consultar Peticiones Quejas o Recursos Desde Web$")
  public void MenuPrincipalOp3RadicarPeticionesConsultarPeticionesQuejasORecursosDesdeWeb() {
    theActorInTheSpotlight().attemptsTo(
            Op2ConsultarPeticionesQuejasRecursosDesdeWeb.marcar(NUMERO_CELULAR)
    );
  }

  @And("^Menu Principal, Op3, Radicar Peticiones, Op2, Si es de la linea que llamas Op1$")
  public void MenuPrincipalOp3Op2Op2() {
    theActorInTheSpotlight().attemptsTo(
            PresentarReclamosOp2Op2Op1.marcar(NUMERO_CELULAR)
    );
  }

  @And("^Menu Principal, Op3, Radicar Peticiones, Op2, Si es de un servicio diferente Op2$")
  public void MenuPrincipalOp3Op2Op2Op2() {
    theActorInTheSpotlight().attemptsTo(
            PresentarReclamosOp2Op2Op2.marcar(NUMERO_CELULAR)
    );
  }

  @And("^Menu Principal, Op3, Radicar Peticiones, Radicar Peticiones Quejas o Recursos$")
  public void MenuPrincipalOp3Op2Op3() {
    theActorInTheSpotlight().attemptsTo(
            Op3RadicarPeticionesQuejasORecursos.marcar(NUMERO_CELULAR)
    );
  }

  @And("^Menu Principal, Op4, Acerca De Billetra Claro Pay$")
  public void MenuPrincipalOp4ClaroPayMasterCard() {
    theActorInTheSpotlight().attemptsTo(
            ClaroPayMasterCard.marcar(NUMERO_CELULAR)
    );
  }
  //



}