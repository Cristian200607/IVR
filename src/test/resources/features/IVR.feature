Feature: Validar los flujos de IVR

  @Audio_01 @IVR
  Scenario: Convertir y reconocer un archivo de audio
    Given El archivo de audio se trae automáticamente desde el celular
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    Then Visualizacion de la transcripcion

  @IVR_00 @Menu_Inicio @IVR_43_POS @IVR
  Scenario: Marcación IVR y Validar Menu Inicio
    Given Se realiza la llamada al numero *611
    When  Se Valida Menu Inicio
    And   Compartir audio a la carpeta
    And   El archivo de audio se trae automáticamente desde el celular
    And   Se realiza el reconocimiento de voz sobre el archivo convertido
    And   Visualizacion de la transcripcion
    Then  Validar la transcripción con el texto esperado del menu principal

  # ================================================================
  # IVR EMPRESAS: @IVR_01_EMPRESAS a @IVR_37_EMPRESAS en el orden de los menús.
  # ================================================================

  # ---------- MENÚ DE INICIO ----------

  @IVR_01_EMPRESAS
  Scenario: IVR Empresas | Menú inicio > 1 Ventas: adquirir servicios o cambiarte a Claro
    Given Se realiza la llamada al numero *611
    When  Menu Principal Op1 IVR EMPRESAS
    And   Compartir audio a la carpeta
    And   El archivo de audio se trae automáticamente desde el celular
    And   Se realiza el reconocimiento de voz sobre el archivo convertido
    And   Visualizacion de la transcripcion
    Then  Validar la transcripción con el texto esperado del menu principal op1 ivr empresas

  @IVR_02_EMPRESAS
  Scenario: IVR Empresas | Menú inicio > 2 Servicio al cliente: oferta de WhatsApp
    Given Se realiza la llamada al numero *611
    When  Menu Principal Op2 IVR EMPRESAS
    And   Compartir audio a la carpeta
    And   El archivo de audio se trae automáticamente desde el celular
    And   Se realiza el reconocimiento de voz sobre el archivo convertido
    And   Visualizacion de la transcripcion
    Then  Validar la transcripción con el texto esperado del menu principal op2 ivr empresas


  # ---------- MENÚ PRINCIPAL > * FACTURA, PAGOS Y PRODUCTOS FINANCIADOS ----------

  @IVR_03_EMPRESAS
  Scenario: IVR Empresas | * > 1 Factura o enlace de pago
    Given Se realiza la llamada al numero *611
    When  Op Asterisco Op1 IVR EMPRESAS
    And   Compartir audio a la carpeta
    And   El archivo de audio se trae automáticamente desde el celular
    And   Se realiza el reconocimiento de voz sobre el archivo convertido
    And   Visualizacion de la transcripcion
    Then  Validar la transcripción con el texto esperado del menu principal op asterisco op1 ivr empresas

  @IVR_04_EMPRESAS
  Scenario: IVR Empresas | * > 2 Factura por correo electrónico
    Given Se realiza la llamada al numero *611
    When  Op Asterisco Op2 IVR EMPRESAS
    And   Compartir audio a la carpeta
    And   El archivo de audio se trae automáticamente desde el celular
    And   Se realiza el reconocimiento de voz sobre el archivo convertido
    And   Visualizacion de la transcripcion
    Then  Validar la transcripción con el texto esperado del menu principal op asterisco op2 ivr empresas

  @IVR_05_EMPRESAS
  Scenario: IVR Empresas | * > 3 Productos financiados
    Given Se realiza la llamada al numero *611
    When  Op Asterisco Op3 IVR EMPRESAS
    And   Compartir audio a la carpeta
    And   El archivo de audio se trae automáticamente desde el celular
    And   Se realiza el reconocimiento de voz sobre el archivo convertido
    And   Visualizacion de la transcripcion
    Then  Validar la transcripción con el texto esperado del menu principal op asterisco op3 ivr empresas

  @IVR_06_EMPRESAS
  Scenario: IVR Empresas | * > 4 Medios de pago > 1 Claro Pay
    Given Se realiza la llamada al numero *611
    When  Op Asterisco Op4 Op1 IVR EMPRESAS
    And   Compartir audio a la carpeta
    And   El archivo de audio se trae automáticamente desde el celular
    And   Se realiza el reconocimiento de voz sobre el archivo convertido
    And   Visualizacion de la transcripcion
    Then  Validar la transcripción con el texto esperado del menu principal op asterisco op4 op1 ivr empresas

  @IVR_07_EMPRESAS
  Scenario: IVR Empresas | * > 4 Medios de pago > 2 App Mi Claro
    Given Se realiza la llamada al numero *611
    When  Op Asterisco Op4 Op2 IVR EMPRESAS
    And   Compartir audio a la carpeta
    And   El archivo de audio se trae automáticamente desde el celular
    And   Se realiza el reconocimiento de voz sobre el archivo convertido
    And   Visualizacion de la transcripcion
    Then  Validar la transcripción con el texto esperado del menu principal op asterisco op4 op2 ivr empresas

  @IVR_08_EMPRESAS
  Scenario: IVR Empresas | * > 4 Medios de pago > 3 Portal de pagos y recargas
    Given Se realiza la llamada al numero *611
    When  Op Asterisco Op4 Op3 IVR EMPRESAS
    And   Compartir audio a la carpeta
    And   El archivo de audio se trae automáticamente desde el celular
    And   Se realiza el reconocimiento de voz sobre el archivo convertido
    And   Visualizacion de la transcripcion
    Then  Validar la transcripción con el texto esperado del menu principal op asterisco op4 op3 ivr empresas

  @IVR_09_EMPRESAS
  Scenario: IVR Empresas | * > 4 Medios de pago > 4 Bancos y plataformas > 1 Billeteras y bancos por internet
    Given Se realiza la llamada al numero *611
    When  Op Asterisco Op4 Op4 Op1 IVR EMPRESAS
    And   Compartir audio a la carpeta
    And   El archivo de audio se trae automáticamente desde el celular
    And   Se realiza el reconocimiento de voz sobre el archivo convertido
    And   Visualizacion de la transcripcion
    Then  Validar la transcripción con el texto esperado del menu principal op asterisco op4 op4 op1 ivr empresas

  @IVR_10_EMPRESAS
  Scenario: IVR Empresas | * > 4 Medios de pago > 4 Bancos y plataformas > 2 Bancos donde pagar
    Given Se realiza la llamada al numero *611
    When  Op Asterisco Op4 Op4 Op2 IVR EMPRESAS
    And   Compartir audio a la carpeta
    And   El archivo de audio se trae automáticamente desde el celular
    And   Se realiza el reconocimiento de voz sobre el archivo convertido
    And   Visualizacion de la transcripcion
    Then  Validar la transcripción con el texto esperado del menu principal op asterisco op4 op4 op2 ivr empresas

  @IVR_11_EMPRESAS
  Scenario: IVR Empresas | * > 4 Medios de pago > 4 Bancos y plataformas > 3 Cajeros, almacenes y recaudo
    Given Se realiza la llamada al numero *611
    When  Op Asterisco Op4 Op4 Op3 IVR EMPRESAS
    And   Compartir audio a la carpeta
    And   El archivo de audio se trae automáticamente desde el celular
    And   Se realiza el reconocimiento de voz sobre el archivo convertido
    And   Visualizacion de la transcripcion
    Then  Validar la transcripción con el texto esperado del menu principal op asterisco op4 op4 op3 ivr empresas


  # ---------- MENÚ PRINCIPAL > # PLAN Y CONSUMOS ----------

  @IVR_12_EMPRESAS
  Scenario: IVR Empresas | # > 1 Consumos de voz y datos
    Given Se realiza la llamada al numero *611
    When  Op Numeral Op1 IVR EMPRESAS
    And   Compartir audio a la carpeta
    And   El archivo de audio se trae automáticamente desde el celular
    And   Se realiza el reconocimiento de voz sobre el archivo convertido
    And   Visualizacion de la transcripcion
    Then  Validar la transcripción con el texto esperado del menu principal op numeral op1 ivr empresas

  @IVR_13_EMPRESAS
  Scenario: IVR Empresas | # > 1 Consumos > 1 Detalles del plan pospago
    Given Se realiza la llamada al numero *611
    When  Op Numeral Op1 Op1 IVR EMPRESAS
    And   Compartir audio a la carpeta
    And   El archivo de audio se trae automáticamente desde el celular
    And   Se realiza el reconocimiento de voz sobre el archivo convertido
    And   Visualizacion de la transcripcion
    Then  Validar la transcripción con el texto esperado del menu principal op numeral op1 op1 ivr empresas

  @IVR_14_EMPRESAS
  Scenario: IVR Empresas | # > 2 Plan contratado
    Given Se realiza la llamada al numero *611
    When  Op Numeral Op2 IVR EMPRESAS
    And   Compartir audio a la carpeta
    And   El archivo de audio se trae automáticamente desde el celular
    And   Se realiza el reconocimiento de voz sobre el archivo convertido
    And   Visualizacion de la transcripcion
    Then  Validar la transcripción con el texto esperado del menu principal op numeral op1 op2 ivr empresas

  @IVR_15_EMPRESAS
  Scenario: IVR Empresas | # > 2 Plan contratado > 2 Consumos de datos
    Given Se realiza la llamada al numero *611
    When  Op Numeral Op2 Op2 IVR EMPRESAS
    And   Compartir audio a la carpeta
    And   El archivo de audio se trae automáticamente desde el celular
    And   Se realiza el reconocimiento de voz sobre el archivo convertido
    And   Visualizacion de la transcripcion
    Then  Validar la transcripción con el texto esperado del menu principal op numeral plan op2 ivr empresas


  # ---------- MENÚ PRINCIPAL > 1 CAMBIOS EN SERVICIOS ----------

  @IVR_16_EMPRESAS
  Scenario: IVR Empresas | 1 Cambios en servicios
    Given Se realiza la llamada al numero *611
    When  Op1 IVR EMPRESAS
    And   Compartir audio a la carpeta
    And   El archivo de audio se trae automáticamente desde el celular
    And   Se realiza el reconocimiento de voz sobre el archivo convertido
    And   Visualizacion de la transcripcion
    Then  Validar la transcripción con el texto esperado del menu principal opcion 1 ivr empresas

  @IVR_17_EMPRESAS
  Scenario: IVR Empresas | 1 > 1 Activar roaming internacional
    Given Se realiza la llamada al numero *611
    When  Op1 Op1 IVR EMPRESAS
    And   Compartir audio a la carpeta
    And   El archivo de audio se trae automáticamente desde el celular
    And   Se realiza el reconocimiento de voz sobre el archivo convertido
    And   Visualizacion de la transcripcion
    Then  Validar la transcripción con el texto esperado del menu principal opcion 1 op1 ivr empresas

  @IVR_18_EMPRESAS
  Scenario: IVR Empresas | 1 > 2 Larga distancia internacional
    Given Se realiza la llamada al numero *611
    When  Op1 Op2 IVR EMPRESAS
    And   Compartir audio a la carpeta
    And   El archivo de audio se trae automáticamente desde el celular
    And   Se realiza el reconocimiento de voz sobre el archivo convertido
    And   Visualizacion de la transcripcion
    Then  Validar la transcripción con el texto esperado del menu principal opcion 1 op2 ivr empresas

  @IVR_19_EMPRESAS
  Scenario: IVR Empresas | 1 > 2 Larga distancia > 1 Paquetes de larga distancia
    Given Se realiza la llamada al numero *611
    When  Op1 Op2 Op1 IVR EMPRESAS
    And   Compartir audio a la carpeta
    And   El archivo de audio se trae automáticamente desde el celular
    And   Se realiza el reconocimiento de voz sobre el archivo convertido
    And   Visualizacion de la transcripcion
    Then  Validar la transcripción con el texto esperado del menu principal opcion 1 op2 op1 ivr empresas

  @IVR_20_EMPRESAS
  Scenario: IVR Empresas | 1 > 2 Larga distancia > 2 Marcar larga distancia internacional
    Given Se realiza la llamada al numero *611
    When  Op1 Op2 Op2 IVR EMPRESAS
    And   Compartir audio a la carpeta
    And   El archivo de audio se trae automáticamente desde el celular
    And   Se realiza el reconocimiento de voz sobre el archivo convertido
    And   Visualizacion de la transcripcion
    Then  Validar la transcripción con el texto esperado del menu principal opcion 1 op2 op2 ivr empresas

  @IVR_21_EMPRESAS
  Scenario: IVR Empresas | 1 > 2 Larga distancia > 3 Marcar larga distancia nacional
    Given Se realiza la llamada al numero *611
    When  Op1 Op2 Op3 IVR EMPRESAS
    And   Compartir audio a la carpeta
    And   El archivo de audio se trae automáticamente desde el celular
    And   Se realiza el reconocimiento de voz sobre el archivo convertido
    And   Visualizacion de la transcripcion
    Then  Validar la transcripción con el texto esperado del menu principal opcion 1 op2 op3 ivr empresas

  @IVR_22_EMPRESAS
  Scenario: IVR Empresas | 1 > 3 Cambio de plan
    Given Se realiza la llamada al numero *611
    When  Op1 Op3 IVR EMPRESAS
    And   Compartir audio a la carpeta
    And   El archivo de audio se trae automáticamente desde el celular
    And   Se realiza el reconocimiento de voz sobre el archivo convertido
    And   Visualizacion de la transcripcion
    Then  Validar la transcripción con el texto esperado del menu principal opcion 1 op3 ivr empresas


  # ---------- MENÚ PRINCIPAL > 2 AYUDA CON SERVICIO MÓVIL ----------

  @IVR_23_EMPRESAS
  Scenario: IVR Empresas | 2 Ayuda con servicio móvil
    Given Se realiza la llamada al numero *611
    When  Op2 IVR EMPRESAS
    And   Compartir audio a la carpeta
    And   El archivo de audio se trae automáticamente desde el celular
    And   Se realiza el reconocimiento de voz sobre el archivo convertido
    And   Visualizacion de la transcripcion
    Then  Validar la transcripción con el texto esperado del menu principal opcion 2 ivr empresas


  # ---------- MENÚ PRINCIPAL > 3 PQR ----------

  @IVR_24_EMPRESAS
  Scenario: IVR Empresas | 3 PQR
    Given Se realiza la llamada al numero *611
    When  Op3 IVR EMPRESAS
    And   Compartir audio a la carpeta
    And   El archivo de audio se trae automáticamente desde el celular
    And   Se realiza el reconocimiento de voz sobre el archivo convertido
    And   Visualizacion de la transcripcion
    Then  Validar la transcripción con el texto esperado del menu principal opcion 3 ivr empresas

  @IVR_25_EMPRESAS
  Scenario: IVR Empresas | 3 > 1 Consultar estado de PQR
    Given Se realiza la llamada al numero *611
    When  Op3 Op1 IVR EMPRESAS
    And   Compartir audio a la carpeta
    And   El archivo de audio se trae automáticamente desde el celular
    And   Se realiza el reconocimiento de voz sobre el archivo convertido
    And   Visualizacion de la transcripcion
    Then  Validar la transcripción con el texto esperado del menu principal opcion 3 op1 ivr empresas

  @IVR_26_EMPRESAS
  Scenario: IVR Empresas | 3 > 2 PQR por soporte o fallas técnicas
    Given Se realiza la llamada al numero *611
    When  Op3 Op2 IVR EMPRESAS
    And   Compartir audio a la carpeta
    And   El archivo de audio se trae automáticamente desde el celular
    And   Se realiza el reconocimiento de voz sobre el archivo convertido
    And   Visualizacion de la transcripcion
    Then  Validar la transcripción con el texto esperado del menu principal opcion 3 op2 ivr empresas

  @IVR_27_EMPRESAS
  Scenario: IVR Empresas | 3 > 3 PQR por cobros en la facturación
    Given Se realiza la llamada al numero *611
    When  Op3 Op3 IVR EMPRESAS
    And   Compartir audio a la carpeta
    And   El archivo de audio se trae automáticamente desde el celular
    And   Se realiza el reconocimiento de voz sobre el archivo convertido
    And   Visualizacion de la transcripcion
    Then  Validar la transcripción con el texto esperado del menu principal opcion 3 op3 ivr empresas

  @IVR_28_EMPRESAS
  Scenario: IVR Empresas | 3 > 4 PQR por otros temas
    Given Se realiza la llamada al numero *611
    When  Op3 Op4 IVR EMPRESAS
    And   Compartir audio a la carpeta
    And   El archivo de audio se trae automáticamente desde el celular
    And   Se realiza el reconocimiento de voz sobre el archivo convertido
    And   Visualizacion de la transcripcion
    Then  Validar la transcripción con el texto esperado del menu principal opcion 3 op4 ivr empresas


  # ---------- MENÚ PRINCIPAL > 4 PREPAGO Y CANCELACIÓN ----------

  @IVR_29_EMPRESAS
  Scenario: IVR Empresas | 4 Prepago y cancelación
    Given Se realiza la llamada al numero *611
    When  Op4 IVR EMPRESAS
    And   Compartir audio a la carpeta
    And   El archivo de audio se trae automáticamente desde el celular
    And   Se realiza el reconocimiento de voz sobre el archivo convertido
    And   Visualizacion de la transcripcion
    Then  Validar la transcripción con el texto esperado del menu principal opcion 4 ivr empresas

  @IVR_30_EMPRESAS
  Scenario: IVR Empresas | 4 > 1 Conocer beneficios
    Given Se realiza la llamada al numero *611
    When  Op4 Op1 IVR EMPRESAS
    And   Compartir audio a la carpeta
    And   El archivo de audio se trae automáticamente desde el celular
    And   Se realiza el reconocimiento de voz sobre el archivo convertido
    And   Visualizacion de la transcripcion
    Then  Validar la transcripción con el texto esperado del menu principal opcion 4 op1 ivr empresas

  @IVR_31_EMPRESAS
  Scenario: IVR Empresas | 4 > 2 Desistir o confirmar la cancelación
    Given Se realiza la llamada al numero *611
    When  Op4 Op2 IVR EMPRESAS
    And   Compartir audio a la carpeta
    And   El archivo de audio se trae automáticamente desde el celular
    And   Se realiza el reconocimiento de voz sobre el archivo convertido
    And   Visualizacion de la transcripcion
    Then  Validar la transcripción con el texto esperado del menu principal opcion 4 op2 ivr empresas

  @IVR_32_EMPRESAS
  Scenario: IVR Empresas | 4 > 3 Solicitar la cancelación
    Given Se realiza la llamada al numero *611
    When  Op4 Op3 IVR EMPRESAS
    And   Compartir audio a la carpeta
    And   El archivo de audio se trae automáticamente desde el celular
    And   Se realiza el reconocimiento de voz sobre el archivo convertido
    And   Visualizacion de la transcripcion
    Then  Validar la transcripción con el texto esperado del menu principal opcion 4 op3 ivr empresas


  # ---------- MENÚ PRINCIPAL > 5 ROBO O PÉRDIDA ----------

  @IVR_33_EMPRESAS
  Scenario: IVR Empresas | 5 Robo o pérdida
    Given Se realiza la llamada al numero *611
    When  Op5 IVR EMPRESAS
    And   Compartir audio a la carpeta
    And   El archivo de audio se trae automáticamente desde el celular
    And   Se realiza el reconocimiento de voz sobre el archivo convertido
    And   Visualizacion de la transcripcion
    Then  Validar la transcripción con el texto esperado del menu principal opcion 5 ivr empresas

  @IVR_34_EMPRESAS
  Scenario: IVR Empresas | 5 > 1 Reportar equipo por robo o pérdida
    Given Se realiza la llamada al numero *611
    When  Op5 Op1 IVR EMPRESAS
    And   Compartir audio a la carpeta
    And   El archivo de audio se trae automáticamente desde el celular
    And   Se realiza el reconocimiento de voz sobre el archivo convertido
    And   Visualizacion de la transcripcion
    Then  Validar la transcripción con el texto esperado del menu principal opcion 5 op1 ivr empresas

  @IVR_35_EMPRESAS
  Scenario: IVR Empresas | 5 > 2 Reconectar línea
    Given Se realiza la llamada al numero *611
    When  Op5 Op2 IVR EMPRESAS
    And   Compartir audio a la carpeta
    And   El archivo de audio se trae automáticamente desde el celular
    And   Se realiza el reconocimiento de voz sobre el archivo convertido
    And   Visualizacion de la transcripcion
    Then  Validar la transcripción con el texto esperado del menu principal opcion 5 op2 ivr empresas

  @IVR_36_EMPRESAS
  Scenario: IVR Empresas | 5 > 3 Reciclaje de equipos
    Given Se realiza la llamada al numero *611
    When  Op5 Op3 IVR EMPRESAS
    And   Compartir audio a la carpeta
    And   El archivo de audio se trae automáticamente desde el celular
    And   Se realiza el reconocimiento de voz sobre el archivo convertido
    And   Visualizacion de la transcripcion
    Then  Validar la transcripción con el texto esperado del menu principal opcion 5 op3 ivr empresas

  @IVR_37_EMPRESAS
  Scenario: IVR Empresas | 5 > 4 Registrar IMEI (puede registrar un equipo real: no correr sin autorización)
    Given Se realiza la llamada al numero *611
    When  Op5 Op4 IVR EMPRESAS
    And   Compartir audio a la carpeta
    And   El archivo de audio se trae automáticamente desde el celular
    And   Se realiza el reconocimiento de voz sobre el archivo convertido
    And   Visualizacion de la transcripcion
    Then  Validar la transcripción con el texto esperado del menu principal opcion 5 op4 ivr empresas


  #Funciona 09/06/26
  Scenario: Marcación IVR y Menu Inicio, Ingreso a WhatsApp
    Given Se realiza la llamada al numero *611#
    And   Op1, Ingreso a WhatsApp
    And   El archivo de audio se trae automáticamente desde el celular
    And   Se realiza el reconocimiento de voz sobre el archivo convertido
    And   Visualizacion de la transcripcion
    Then  Validar la transcripción Menu Inicio, Ingreso a WhatsApp

  #Funciona 09/06/26
  Scenario: Marcación IVR y Menu Inicio, Comprar Servicios Claro, Servicios Hogar
    Given Se realiza la llamada al numero *611#
    And Comprar Servicios Claro, Servicios Hogar
    And El archivo de audio se trae automáticamente desde el celular
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    Then Validar la transcripción Comprar Servicios Claro, Servicios Hogar

  #Funciona 09/06/26
  Scenario: Marcación IVR y Menu Inicio, Comprar Servicios Claro, Plan Postpago Movil
    Given Se realiza la llamada al numero *611#
    And Comprar Servicios Claro, Plan Postpago Movil
    And El archivo de audio se trae automáticamente desde el celular
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    Then Validar la transcripción Comprar Servicios Claro, Plan Postpago Movil

  #Funciona 09/06/26
  Scenario: Marcación IVR y Menu Inicio, Comprar Servicios Claro, Compra De Equipos
    Given Se realiza la llamada al numero *611#
    And Comprar Servicios Claro, Compra De Equipos
    And El archivo de audio se trae automáticamente desde el celular
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    Then Validar la transcripción Menu Inicio, Comprar Servicios Claro, Compra De Equipos

  #Funciona 09/06/26
  Scenario: Marcación IVR y Menu Inicio, Comprar Servicios Claro, Adquirir Servicios Y Pasarte a Claro
    Given Se realiza la llamada al numero *611#
    And Comprar Servicios Claro, Adquirir Servicios Y Pasarte a Claro
    And El archivo de audio se trae automáticamente desde el celular
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    Then Validar la transcripción Menu Inicio, Comprar Servicios Claro, Adquirir Servicios Y Pasarte a Claro

  #Funciona 09/06/26
  Scenario: Marcación IVR y Menu Principal, Asterisco, Consultar Saldo
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Asterisco, Consultar Saldo
    And El archivo de audio se trae automáticamente desde el celular
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
    Then Validar la transcripción Menu Principal, Asterisco, Consultar Saldo

  Scenario: Marcación IVR y Menu Principal, Asterisco, Comprar Paquetes
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Asterisco, Comprar Paquetes, Validar Menu Comprar Paquetes
    And El archivo de audio se trae automáticamente desde el celular
    And Se realiza el reconocimiento de voz sobre el archivo convertido
    And Visualizacion de la transcripcion
  #  Then Validar la transcripción Menu Principal, Asterisco, Comprar Paquetes

  Scenario: Marcación IVR y Menu Principal, Asterisco, Comprar Paquetes, Mas opciones de paquetes
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Asterisco, Comprar Paquetes, Mas opciones de paquetes
    And El archivo de audio se trae automáticamente desde el celular
    And Se eliminan y respaldan los audios del celular
  #  And Se realiza el reconocimiento de voz sobre el archivo convertido
  #  And Visualizacion de la transcripcion
  #  Then Validar la transcripción Menu Principal, Asterisco, Comprar Paquetes, Mas opciones de paquetes

  Scenario: Marcación IVR y Menu Principal, Asterisco, Comprar Paquetes, Mas Opciones De Recarga
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Asterisco, Comprar Paquetes, Mas Opciones De Recarga
    And El archivo de audio se trae automáticamente desde el celular
    And Se eliminan y respaldan los audios del celular
  #  And Se realiza el reconocimiento de voz sobre el archivo convertido
  #  And Visualizacion de la transcripcion
  #  Then Validar la transcripción Menu Principal, Asterisco, Comprar Paquetes, Mas Opciones De Recarga

  Scenario: Marcación IVR y Menu Principal, Asterisco, Recargar Linea Prepago
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Asterisco, Recarga Linea Prepago
    And El archivo de audio se trae automáticamente desde el celular
    And Se eliminan y respaldan los audios del celular
  #  And Se realiza el reconocimiento de voz sobre el archivo convertido
  #  And Visualizacion de la transcripcion
  #  Then Validar la transcripción Menu Principal, Asterisco, Recargar Linea Prepago

  Scenario: Marcación IVR y Menu Principal, Asterisco, Productos Financiados
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Asterisco, Productos Financiados
    And El archivo de audio se trae automáticamente desde el celular
    And Se eliminan y respaldan los audios del celular
  #  And Se realiza el reconocimiento de voz sobre el archivo convertido
  #  And Visualizacion de la transcripcion
  #  Then Validar la transcripción con el texto esperado de Opcion 3 despues 4

  #revisar
  Scenario: Marcación IVR y Menu Principal, Asterisco, Paquetes Activos
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Asterisco, Paquetes Activos, Validar Menu Paquetes Activos
    And El archivo de audio se trae automáticamente desde el celular
    And Se eliminan y respaldan los audios del celular
  #  And Se realiza el reconocimiento de voz sobre el archivo convertido
  #  And Visualizacion de la transcripcion
  #  Then Validar la transcripción Menu Principal, Asterisco, Paquetes Activos

  Scenario: Marcación IVR y Menu Principal, Numeral, Soporte De Voz
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Numeral, Soporte De Voz
    And El archivo de audio se trae automáticamente desde el celular
    And Se eliminan y respaldan los audios del celular
  #  And Se realiza el reconocimiento de voz sobre el archivo convertido
  #  And Visualizacion de la transcripcion
  #  Then Validar la transcripción Menu Principal, Numeral, Soporte De Voz

  #hallazgo
  Scenario: Marcación IVR y Menu Principal, Numeral, Te Pasaste a Claro
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Numeral, Te Pasaste a Claro
    And El archivo de audio se trae automáticamente desde el celular
    And Se eliminan y respaldan los audios del celular
  #  And Se realiza el reconocimiento de voz sobre el archivo convertido
  #  And Visualizacion de la transcripcion
  #  Then Validar la transcripción Menu Principal, Numeral, Te Pasaste a Claro

  Scenario: Marcación IVR y Menu Principal, Numeral, Robo De Celular
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Numeral, Robo De Celular
    And El archivo de audio se trae automáticamente desde el celular
    And Se eliminan y respaldan los audios del celular
  #  And Se realiza el reconocimiento de voz sobre el archivo convertido
  #  And Visualizacion de la transcripcion
  #  Then Validar la transcripción Menu Principal, Numeral, Robo De Celular

  #YA NO ESTA LA OPCION
  Scenario: Marcación IVR y Menu Principal, Numeral, Reconectar Linea
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Numeral, Reconectar Linea
    And El archivo de audio se trae automáticamente desde el celular
    And Se eliminan y respaldan los audios del celular
  #  And Se realiza el reconocimiento de voz sobre el archivo convertido
  #  And Visualizacion de la transcripcion
  #  Then Validar la transcripción Menu Principal, Numeral, Reconectar Linea

  Scenario: Marcación IVR y Menu Principal, Op1, Info General
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op1, Info General
    And El archivo de audio se trae automáticamente desde el celular
    And Se eliminan y respaldan los audios del celular
  #  And Se realiza el reconocimiento de voz sobre el archivo convertido
  #  And Visualizacion de la transcripcion
  #  Then Validar la transcripción Menu Principal, Op1, Info General

  Scenario: Marcación IVR y Menu Principal, Op1, Activar Roaming Internacional
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op1, Activar Roaming Internacional, Op1
    And El archivo de audio se trae automáticamente desde el celular
    And Se eliminan y respaldan los audios del celular
  #  And Se realiza el reconocimiento de voz sobre el archivo convertido
  #  And Visualizacion de la transcripcion
  #  Then Validar la transcripción Menu Principal, Op1, Activar Roaming Internacional

  #HORARIO
  Scenario: Marcación IVR y Menu Principal, Op1, Activar Roaming Internacional, Op2
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op1, Activar Roaming Internacional, Op2
    And El archivo de audio se trae automáticamente desde el celular
    And Se eliminan y respaldan los audios del celular
  #  And Se realiza el reconocimiento de voz sobre el archivo convertido
  #  And Visualizacion de la transcripcion
  #  Then Validar la transcripción Menu Principal, Op1, Activar Roaming Internacional, Op2

  #@IVR_72_POS
  Scenario: Marcación IVR y Menu Principal, Op1, Adquirir Equipos
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op1, Adquirir Equipos
    And El archivo de audio se trae automáticamente desde el celular
    And Se eliminan y respaldan los audios del celular
  #  And Se realiza el reconocimiento de voz sobre el archivo convertido
  #  And Visualizacion de la transcripcion
  #  Then Validar la transcripción Menu Principal, Op1, Adquirir Equipos

  #@IVR_73_POS
  Scenario: Marcación IVR y Menu Principal Op1, Pasarte a Claro y Adquirir Servicios Claro
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op1, Pasarte a Claro y Adquirir Servicios Claro, Servicios Moviles y Servicios Hogar
    And El archivo de audio se trae automáticamente desde el celular
    And Se eliminan y respaldan los audios del celular
  #  And Se realiza el reconocimiento de voz sobre el archivo convertido
  #  And Visualizacion de la transcripcion
  #  Then Validar la transcripción Menu Principal, Op1, Pasarte a Claro y Adquirir Servicios Claro, Servicios Moviles y Servicios Hogar

  #@IVR_74_POS
  Scenario: Marcación IVR y Menu Principal Op1, Pasarte a Claro y Adquirir Servicios Claro
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op1, Adquirir Servicios Claro, Pasarte Claro Con Tu Mismo Numero
    And El archivo de audio se trae automáticamente desde el celular
    And Se eliminan y respaldan los audios del celular
  #  And Se realiza el reconocimiento de voz sobre el archivo convertido
  #  And Visualizacion de la transcripcion
  #  Then Validar la transcripción Menu Principal, Op1, Adquirir Servicios Claro, Pasarte Claro Con Tu Mismo Numero

  #@IVR_75_POS
  Scenario: Marcación IVR y Menu Principal Op1, Pasarte a Claro y Adquirir Servicios Claro
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op1, Adquirir Servicios Claro, Claro Video
    And El archivo de audio se trae automáticamente desde el celular
    And Se eliminan y respaldan los audios del celular
  #  And Se realiza el reconocimiento de voz sobre el archivo convertido
  #  And Visualizacion de la transcripcion
  #  Then Validar la transcripción Menu Principal, Op1, Adquirir Servicios Claro, Claro Video

  #@IVR_76_POS
  Scenario: Marcación IVR y Menu Principal Op1, Pasarte a Claro y Adquirir Servicios Claro
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op1, Adquirir Servicios Claro, Claro Musica
    And El archivo de audio se trae automáticamente desde el celular
    And Se eliminan y respaldan los audios del celular
  #  And Se realiza el reconocimiento de voz sobre el archivo convertido
  #  And Visualizacion de la transcripcion
  #  Then Validar la transcripción Menu Principal, Op1, Adquirir Servicios Claro, Claro Musica

  #@IVR_77_POS
  Scenario: Marcación IVR y Menu Principal Op1, Pasarte a Claro y Adquirir Servicios Claro
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op1, Adquirir Servicios Claro, Adquiri Servicio Asistencia Claro, Activar Servicio
    And El archivo de audio se trae automáticamente desde el celular
    And Se eliminan y respaldan los audios del celular
  #  And Se realiza el reconocimiento de voz sobre el archivo convertido
  #  And Visualizacion de la transcripcion
  #  Then Validar la transcripción Menu Principal, Op1, Adquirir Servicios Claro, Adquirir Servicio Asistencia Claro, Activar Servicio

  #@IVR_78_POS
  Scenario: Marcación IVR y Menu Principal Op1, Pasarte a Claro y Adquirir Servicios Claro
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op1, Adquirir Servicios Claro, Adquiri Servicio Asistencia Claro, Mayor Informacion
    And El archivo de audio se trae automáticamente desde el celular
    And Se eliminan y respaldan los audios del celular
  #  And Se realiza el reconocimiento de voz sobre el archivo convertido
  #  And Visualizacion de la transcripcion
  #  Then Validar la transcripción Menu Principal, Op1, Adquirir Servicios Claro, Adquiri Servicio Asistencia Claro, Mayor Informacion

  Scenario: Marcación IVR y Menu Principal Op1, Pasarte a Claro y Adquirir Servicios Claro
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op1, Adquirir Servicios Claro, Adquiri Servicio Asistencia Claro, Solicitud de Asistencia
    And El archivo de audio se trae automáticamente desde el celular
    And Se eliminan y respaldan los audios del celular
  #  And Se realiza el reconocimiento de voz sobre el archivo convertido
  #  And Visualizacion de la transcripcion
  #  Then Validar la transcripción Menu Principal, Op1, Adquirir Servicios Claro, Adquirir Servicio Asistencia Claro, Solicitud de Asistencia

  Scenario: Marcación IVR y Menu Principal Op1, Pasarte a Claro y Adquirir Servicios Claro
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op1, Adquirir Servicios Claro, Larga Distancia Internacional
    And El archivo de audio se trae automáticamente desde el celular
    And Se eliminan y respaldan los audios del celular
  #  And Se realiza el reconocimiento de voz sobre el archivo convertido
  #  And Visualizacion de la transcripcion
  #  Then Validar la transcripción Menu Principal, Op1, Adquirir Servicios Claro, Larga Distancia Internacional

  Scenario: Marcación IVR y Menu Principal Op1, Pasarte a Claro y Elegidos Movil Familia
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op1, Elegidos Movil Familia
    And El archivo de audio se trae automáticamente desde el celular
    And Se eliminan y respaldan los audios del celular
  #  And Se realiza el reconocimiento de voz sobre el archivo convertido
  #  And Visualizacion de la transcripcion
  #  Then Validar la transcripción Menu Principal, Op1, Elegidos Movil Familia

  Scenario: Marcación IVR y Menu Principal, Op2, Perdida De Celular
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op2, Perdida De Celular
    And El archivo de audio se trae automáticamente desde el celular
    And Se eliminan y respaldan los audios del celular
  #  And Se realiza el reconocimiento de voz sobre el archivo convertido
  #  And Visualizacion de la transcripcion
  #  Then Validar la transcripción Menu Principal, Op2, Perdida De Celular

  Scenario: Marcación IVR y Menu Principal, Op2, Reconeccion
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op2, Reconeccion
    And El archivo de audio se trae automáticamente desde el celular
    And Se eliminan y respaldan los audios del celular
  #  And Se realiza el reconocimiento de voz sobre el archivo convertido
  #  And Visualizacion de la transcripcion
  #  Then Validar la transcripción Menu Principal, Op2, Reconeccion

  Scenario: Marcación IVR y Menu Principal, Op2, Registrar Equipo
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op2, Registrar Equipo
    And El archivo de audio se trae automáticamente desde el celular
    And Se eliminan y respaldan los audios del celular
  #  And Se realiza el reconocimiento de voz sobre el archivo convertido
  #  And Visualizacion de la transcripcion
  #  Then Validar la transcripción Menu Principal, Op2, Registrar Equipo

  Scenario: Marcación IVR y Menu Principal, Op2, Reciclaje De Equipos
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op2, Reciclaje De Equipos
    And El archivo de audio se trae automáticamente desde el celular
    And Se eliminan y respaldan los audios del celular
  #  And Se realiza el reconocimiento de voz sobre el archivo convertido
  #  And Visualizacion de la transcripcion
  #  Then Validar la transcripción Menu Principal, Op2, Reciclaje De Equipos

  Scenario: Marcación IVR y Menu Principal, Op3, Desistir
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op3, Desistir, Cancelar Servicios Hogar o Movil
    And El archivo de audio se trae automáticamente desde el celular
    And Se eliminan y respaldan los audios del celular
  #  And Se realiza el reconocimiento de voz sobre el archivo convertido
  #  And Visualizacion de la transcripcion
  #  Then Validar la transcripción Menu Principal, Op3, Desistir, Cancelar Servicios Hogar o Movil

  Scenario: Marcación IVR y Menu Principal, Op3, Desistir
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op3, Desistir, Cancelar Servicios Adicionales
    And El archivo de audio se trae automáticamente desde el celular
    And Se eliminan y respaldan los audios del celular
  #  And Se realiza el reconocimiento de voz sobre el archivo convertido
  #  And Visualizacion de la transcripcion
  #  Then Validar la transcripción Menu Principal, Op3, Desistir, Cancelar Servicios Adicionales

  Scenario: Marcación IVR y Menu Principal, Op3, Radicar Peticiones
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op3, Radicar Peticiones, Consultar Peticiones Quejas o Recursos Desde Web
    And El archivo de audio se trae automáticamente desde el celular
    And Se eliminan y respaldan los audios del celular
  #  And Se realiza el reconocimiento de voz sobre el archivo convertido
  #  And Visualizacion de la transcripcion
  #  Then Validar la transcripción Menu Principal, Op3, Radicar Peticiones, Consultar Peticiones Quejas o Recursos Desde Web

  Scenario: Marcación IVR y Menu Principal, Op3, Radicar Peticiones
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op3, Radicar Peticiones, Op2, Si es de la linea que llamas Op1
    And El archivo de audio se trae automáticamente desde el celular
    And Se eliminan y respaldan los audios del celular
  #  And Se realiza el reconocimiento de voz sobre el archivo convertido
  #  And Visualizacion de la transcripcion
  #  Then Validar la transcripción Menu Principal, Op3, Radicar Peticiones, Op2, Si es de la linea que llamas Op1

  Scenario: Marcación IVR y Menu Principal, Op3, Radicar Peticiones
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op3, Radicar Peticiones, Op2, Si es de un servicio diferente Op2
    And El archivo de audio se trae automáticamente desde el celular
    And Se eliminan y respaldan los audios del celular
  #  And Se realiza el reconocimiento de voz sobre el archivo convertido
  #  And Visualizacion de la transcripcion
  #  Then Validar la transcripción Menu Principal, Op3, Radicar Peticiones, Op2, Si es de un servicio diferente Op2

  Scenario: Marcación IVR y Menu Principal, Op3, Radicar Peticiones Quejas o Recursos
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op3, Radicar Peticiones, Radicar Peticiones Quejas o Recursos
    And El archivo de audio se trae automáticamente desde el celular
    And Se eliminan y respaldan los audios del celular
  #  And Se realiza el reconocimiento de voz sobre el archivo convertido
  #  And Visualizacion de la transcripcion
  #  Then Validar la transcripción Menu Principal, Op3, Radicar Peticiones, Radicar Peticiones Quejas o Recursos

  Scenario: Marcación IVR y Menu Principal, Op4, Claro Pay Master Card
    Given Se realiza la llamada al numero *611#
    And Menu Principal, Op4, Acerca De Billetra Claro Pay
    And El archivo de audio se trae automáticamente desde el celular
    And Se eliminan y respaldan los audios del celular
  #  And Se realiza el reconocimiento de voz sobre el archivo convertido
  #  And Visualizacion de la transcripcion
  #  Then Validar la transcripción Menu Principal, Op4, Claro Pay Master Card

