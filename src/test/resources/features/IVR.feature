Feature: Validar los flujos de IVR

  #@IVR_01
  Scenario Outline: Marcacion IVR
    Given Se realiza la llamada al numero *611#
    When Se comienza a grabar la llamada
    Examples:
      | saludo |  numero | valor |
  ##@externalData@./src/test/resources/data/Datos.xlsx@correo@
      |Hola   |3558   |$ 50.000|

  @Audio_01
  Scenario: Convertir y reconocer un archivo de audio
    Given El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    When Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    Then Visualizacion de la transcripcion

  @IVR_00 @Menu_Inicio
  Scenario: Marcación IVR y Validar Menu Inicio
    Given Se realiza la llamada al numero *611
    When  Se Valida Menu Inicio
    And   El archivo de audio se trae automáticamente desde el celular
    And   Un archivo de audio ubicado en Llamadas
    And   Se convierte el archivo a WAV mono 16kHz si es necesario
    And   Se realiza el reconocimiento de voz sobre el archivo convertido
    And   Visualizacion de la transcripcion
    Then  Validar la transcripción con el texto esperado del menu principal


  @IVR_01 @Inegreso_WhatsApp
  Scenario: Marcación IVR y Menu Inicio, Ingreso a WhatsApp
    Given Se realiza la llamada al numero *611#
    And   Op1, Ingreso a WhatsApp
    And   El archivo de audio se trae automáticamente desde el celular
    And   Un archivo de audio ubicado en Llamadas
    And   Se convierte el archivo a WAV mono 16kHz si es necesario
    And   Se realiza el reconocimiento de voz sobre el archivo convertido
    And   Visualizacion de la transcripcion
    Then  Validar la transcripción Menu Inicio, Ingreso a WhatsApp

  @IVR_02 @Menu_Inicio_Comprar_Servicios_Claro
  Scenario: Marcación IVR y Menu Inicio, Comprar Servicios Claro, Servicios Hogar
    Given Se realiza la llamada al numero *611#
    And Comprar Servicios Claro, Servicios Hogar
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    Then Validar la transcripción Comprar Servicios Claro, Servicios Hogar

  @IVR_03 @Menu_Inicio_Comprar_Servicios_Claro
  Scenario: Marcación IVR y Menu Inicio, Comprar Servicios Claro, Plan Postpago Movil
    Given Se realiza la llamada al numero *611#
    And Comprar Servicios Claro, Plan Postpago Movil
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    Then Validar la transcripción Comprar Servicios Claro, Plan Postpago Movil

  @IVR_04 @Menu_Inicio_Comprar_Servicios_Claro
  Scenario: Marcación IVR y Menu Inicio, Comprar Servicios Claro, Compra De Equipos
    Given Se realiza la llamada al numero *611#
    And Comprar Servicios Claro, Compra De Equipos
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    Then Validar la transcripción Menu Inicio, Comprar Servicios Claro, Compra De Equipos

  @IVR_05 @Menu_Inicio_Comprar_Servicios_Claro
  Scenario: Marcación IVR y Menu Inicio, Comprar Servicios Claro, Adquirir Servicios Y Pasarte a Claro
    Given Se realiza la llamada al numero *611#
    And Comprar Servicios Claro, Adquirir Servicios Y Pasarte a Claro
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    Then Validar la transcripción Menu Inicio, Comprar Servicios Claro, Adquirir Servicios Y Pasarte a Claro

  @IVR_06
  Scenario: Marcación IVR y Valida Menu Para Consultar Lineas
    Given Se realiza la llamada al numero *611#
    And Valida Menu Para Consultar Lineas
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    Then Validar la transcripción Valida Menu Para Consultar Lineas

  @IVR_07
  Scenario: Marcación IVR y Validar Menu Principal Despues de consultar linea
    Given Se realiza la llamada al numero *611#
    And Se valida el menu principal despues de digitar la linea a consultar
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    #Then Validar la transcripción con el texto esperado de Validar Menu principal despues de digitar la linea

  @IVR_08 @Menu_Principal_Asterisco_Consultar_Saldo
  Scenario: Marcación IVR y Menu Principal, Asterisco, Consultar Saldo
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Asterisco, Consultar Saldo
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    Then Validar la transcripción Menu Principal, Asterisco, Consultar Saldo

  @IVR_09 @Menu_Principal_Asterisco_Comprar_Paquetes
  Scenario: Marcación IVR y Menu Principal, Asterisco, Comprar Paquetes
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Asterisco, Comprar Paquetes, Validar Menu Comprar Paquetes
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    Then Validar la transcripción Menu Principal, Asterisco, Comprar Paquetes

  @IVR_10
  Scenario: Marcación IVR y Menu Principal, Asterisco, Comprar Paquetes, Mas opciones de paquetes
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Asterisco, Comprar Paquetes, Mas opciones de paquetes
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    Then Validar la transcripción Menu Principal, Asterisco, Comprar Paquetes, Mas opciones de paquetes

  @IVR_11
  Scenario: Marcación IVR y Menu Principal, Asterisco, Comprar Paquetes, Mas Opciones De Recarga
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Asterisco, Comprar Paquetes, Mas Opciones De Recarga
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    Then Validar la transcripción Menu Principal, Asterisco, Comprar Paquetes, Mas Opciones De Recarga

  @IVR_12
  Scenario: Marcación IVR y Menu Principal, Asterisco, Recargar Linea Prepago
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Asterisco, Recarga Linea Prepago
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    Then Validar la transcripción Menu Principal, Asterisco, Recargar Linea Prepago

  @IVR_13 #Hallazgo
  Scenario: Marcación IVR y Menu Principal, Asterisco, Productos Financiados
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Asterisco, Productos Financiados
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    #Then Validar la transcripción con el texto esperado de Opcion 3 despues 4

  @IVR_14
  Scenario: Marcación IVR y Menu Principal, Asterisco, Paquetes Activos
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Asterisco, Paquetes Activos, Validar Menu Paquetes Activos
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    #Then Validar la transcripción Menu Principal, Asterisco, Paquetes Activos

  @IVR_15
  Scenario: Marcación IVR y Menu Principal, Asterisco, Paquetes Activos
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Asterisco, Paquetes Activos, Retorno Menu Principal
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    #Then Validar la transcripción Menu Principal, Asterisco, Paquetes Activos, Retorno Menu Principal

  @IVR_16
  Scenario: Marcación IVR y Menu Principal, Numeral, Soporte De Voz
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Numeral, Soporte De Voz
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    Then Validar la transcripción Menu Principal, Numeral, Soporte De Voz

  @IVR_17
  Scenario: Marcación IVR y Menu Principal, Numeral, Te Pasaste a Claro
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Numeral, Te Pasaste a Claro
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    Then Validar la transcripción Menu Principal, Numeral, Te Pasaste a Claro

  @IVR_18
  Scenario: Marcación IVR y Menu Principal, Numeral, Robo De Celular
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Numeral, Robo De Celular
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    Then Validar la transcripción Menu Principal, Numeral, Robo De Celular

  @IVR_19
  Scenario: Marcación IVR y Menu Principal, Numeral, Reconectar Linea
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Numeral, Reconectar Linea
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    Then Validar la transcripción Menu Principal, Numeral, Reconectar Linea

  @IVR_20
  Scenario: Marcación IVR y Menu Principal, Op1, Info General
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op1, Info General
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    Then Validar la transcripción Menu Principal, Op1, Info General

  @IVR_21
  Scenario: Marcación IVR y Menu Principal, Op1, Activar Roaming Internacional
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op1, Activar Roaming Internacional, Op1
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    Then Validar la transcripción Menu Principal, Op1, Activar Roaming Internacional

  @IVR_22
  Scenario: Marcación IVR y Menu Principal, Op1, Activar Roaming Internacional, Op2
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op1, Activar Roaming Internacional, Op2
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    Then Validar la transcripción Menu Principal, Op1, Activar Roaming Internacional, Op2

  @IVR_23
  Scenario: Marcación IVR y Menu Principal, Op1, Adquirir Equipos
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op1, Adquirir Equipos
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    Then Validar la transcripción Menu Principal, Op1, Adquirir Equipos

  @IVR_24
  Scenario: Marcación IVR y Menu Principal Op1, Pasarte a Claro y Adquirir Servicios Claro
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op1, Pasarte a Claro y Adquirir Servicios Claro, Servicios Moviles y Servicios Hogar
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    Then Validar la transcripción Menu Principal, Op1, Pasarte a Claro y Adquirir Servicios Claro, Servicios Moviles y Servicios Hogar

  @IVR_25
  Scenario: Marcación IVR y Menu Principal Op1, Pasarte a Claro y Adquirir Servicios Claro
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op1, Adquirir Servicios Claro, Pasarte Claro Con Tu Mismo Numero
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    Then Validar la transcripción Menu Principal, Op1, Adquirir Servicios Claro, Pasarte Claro Con Tu Mismo Numero

  @IVR_26
  Scenario: Marcación IVR y Menu Principal Op1, Pasarte a Claro y Adquirir Servicios Claro
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op1, Adquirir Servicios Claro, Claro Video
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    Then Validar la transcripción Menu Principal, Op1, Adquirir Servicios Claro, Claro Video

  @IVR_27
  Scenario: Marcación IVR y Menu Principal Op1, Pasarte a Claro y Adquirir Servicios Claro
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op1, Adquirir Servicios Claro, Claro Musica
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    Then Validar la transcripción Menu Principal, Op1, Adquirir Servicios Claro, Claro Musica

  @IVR_28
  Scenario: Marcación IVR y Menu Principal Op1, Pasarte a Claro y Adquirir Servicios Claro
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op1, Adquirir Servicios Claro, Adquiri Servicio Asistencia Claro, Activar Servicio
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    Then Validar la transcripción Menu Principal, Op1, Adquirir Servicios Claro, Adquirir Servicio Asistencia Claro, Activar Servicio

  @IVR_29
  Scenario: Marcación IVR y Menu Principal Op1, Pasarte a Claro y Adquirir Servicios Claro
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op1, Adquirir Servicios Claro, Adquiri Servicio Asistencia Claro, Mayor Informacion
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    Then Validar la transcripción Menu Principal, Op1, Adquirir Servicios Claro, Adquiri Servicio Asistencia Claro, Mayor Informacion

  @IVR_30
  Scenario: Marcación IVR y Menu Principal Op1, Pasarte a Claro y Adquirir Servicios Claro
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op1, Adquirir Servicios Claro, Adquiri Servicio Asistencia Claro, Solicitud de Asistencia
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    Then Validar la transcripción Menu Principal, Op1, Adquirir Servicios Claro, Adquirir Servicio Asistencia Claro, Solicitud de Asistencia

  @IVR_31
  Scenario: Marcación IVR y Menu Principal Op1, Pasarte a Claro y Adquirir Servicios Claro
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op1, Adquirir Servicios Claro, Larga Distancia Internacional
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    Then Validar la transcripción Menu Principal, Op1, Adquirir Servicios Claro, Larga Distancia Internacional

  @IVR_32
  Scenario: Marcación IVR y Menu Principal Op1, Pasarte a Claro y Elegidos Movil Familia
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op1, Elegidos Movil Familia
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    Then Validar la transcripción Menu Principal, Op1, Elegidos Movil Familia

  @IVR_33
  Scenario: Marcación IVR y Menu Principal, Op2, Perdida De Celular
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op2, Perdida De Celular
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    Then Validar la transcripción Menu Principal, Op2, Perdida De Celular

  @IVR_34
  Scenario: Marcación IVR y Menu Principal, Op2, Reconeccion
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op2, Reconeccion
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    Then Validar la transcripción Menu Principal, Op2, Reconeccion

  @IVR_35
  Scenario: Marcación IVR y Menu Principal, Op2, Registrar Equipo
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op2, Registrar Equipo
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    Then Validar la transcripción Menu Principal, Op2, Registrar Equipo

  @IVR_36
  Scenario: Marcación IVR y Menu Principal, Op2, Reciclaje De Equipos
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op2, Reciclaje De Equipos
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    Then Validar la transcripción Menu Principal, Op2, Reciclaje De Equipos

  @IVR_37
  Scenario: Marcación IVR y Menu Principal, Op3, Desistir
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op3, Desistir, Cancelar Servicios Hogar o Movil
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    Then Validar la transcripción Menu Principal, Op3, Desistir, Cancelar Servicios Hogar o Movil

  @IVR_38
  Scenario: Marcación IVR y Menu Principal, Op3, Desistir
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op3, Desistir, Cancelar Servicios Adicionales
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    Then Validar la transcripción Menu Principal, Op3, Desistir, Cancelar Servicios Adicionales

  @IVR_39
  Scenario: Marcación IVR y Menu Principal, Op3, Radicar Peticiones
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op3, Radicar Peticiones, Consultar Peticiones Quejas o Recursos Desde Web
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    Then Validar la transcripción Menu Principal, Op3, Radicar Peticiones, Consultar Peticiones Quejas o Recursos Desde Web

  @IVR_40
  Scenario: Marcación IVR y Menu Principal, Op3, Radicar Peticiones
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op3, Radicar Peticiones, Op2, Si es de la linea que llamas Op1
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    Then Validar la transcripción Menu Principal, Op3, Radicar Peticiones, Op2, Si es de la linea que llamas Op1

  @IVR_41
  Scenario: Marcación IVR y Menu Principal, Op3, Radicar Peticiones
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op3, Radicar Peticiones, Op2, Si es de un servicio diferente Op2
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    Then Validar la transcripción Menu Principal, Op3, Radicar Peticiones, Op2, Si es de un servicio diferente Op2

  @IVR_42
  Scenario: Marcación IVR y Menu Principal, Op3, Radicar Peticiones Quejas o Recursos
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op3, Radicar Peticiones, Radicar Peticiones Quejas o Recursos
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    Then Validar la transcripción Menu Principal, Op3, Radicar Peticiones, Radicar Peticiones Quejas o Recursos

  @IVR_43
  Scenario: Marcación IVR y Menu Principal, Op4, Claro Pay
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op4, Claro Pay
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    Then Validar la transcripción Menu Principal, Op4, Claro Pay

  @IVR_44
  Scenario: Marcación IVR y Menu Principal, Op4, Claro Pay Master Card
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op4, Claro Pay Master Card
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    Then Validar la transcripción Menu Principal, Op4, Claro Pay Master Card

  @IVR_45
  Scenario: Marcación IVR y Menu Principal, Op4, Claro Pay Master Card, Op2
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op4, Claro Pay Master Card, Op2
    And El archivo de audio se trae automáticamente desde el celular
    And Un archivo de audio ubicado en Llamadas
    And Se convierte el archivo a WAV mono 16kHz si es necesario
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    Then Validar la transcripción Menu Principal, Op4, Claro Pay Master Card, Op2