# Recordatorios Citas Service
## Descripción
Repositorio del post-contenido de la Unidad 9 de Patrones de Diseño
de Software — Sexto Semestre. Un único proyecto Spring Boot que
envía recordatorios de citas por el canal preferido del paciente,
construido desde el inicio con Strategy (ReminderChannel) y Factory
Method (ReminderChannelFactory) inyectados por constructor.

## Parte 1 — Pruebas Unitarias con JUnit 5 y Mockito
**Mock vs. Stub vs. Fake (Parte 1).** Para cada colaborador del constructor
de CitaReminderServiceImpl se tomó una decisión distinta:

- **CitaRepository → Mock.** No basta con stubbear
  findByFechaHoraCitaBetweenAndRecordatorioEnviadoFalse para que devuelva
  una lista: también hay que verificar que save() se llama exactamente una
  vez y con la cita ya marcada como enviada. Cuando una prueba necesita
  controlar la respuesta y además verificar la interacción, el doble es un
  Mock y no un simple Stub.

- **ReminderChannelFactory y ReminderChannel → Mock.** El comportamiento
  bajo prueba es la selección de la estrategia: que el servicio pida el
  canal EMAIL (o SMS) según la preferencia del paciente y que ese canal
  reciba el mensaje. Eso solo se comprueba verificando la interacción
  (verify(factory).obtenerCanal(...) y verify(canal).enviar(...)), no con
  un valor de retorno.

- **Clock → Fake (Clock.fixed).** El JDK ya provee una implementación real
  y completa de Clock que se comporta igual que en producción, solo que con
  el instante congelado. Mockearlo exigiría stubbear instant() y getZone()
  por separado y correría el riesgo de que LocalDateTime.now(clock) no se
  comporte como con un Clock real. Con el Fake, la ventana de 24 horas es
  exacta y reproducible en cualquier máquina y a cualquier hora; con
  Clock.systemDefaultZone() la aserción cambiaría en cada ejecución.


## Evidencia de pruebas

### Parte 1 — Pruebas unitarias
![mvn test](img.png)
