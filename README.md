# Kotlin Domain Driven Design (DDD) example project

<!-- Badger start badges -->
[![Build status](https://badger.engprod-pro.mpi-internal.com/badge/travis/scmspain/meeting-monolith-app)](https://badger.engprod-pro.mpi-internal.com/redirect/travis/scmspain/meeting-monolith-app) [![Test coverage](https://badger.engprod-pro.mpi-internal.com/badge/coverage/scmspain/meeting-monolith-app)](https://badger.engprod-pro.mpi-internal.com/redirect/coverage/scmspain/meeting-monolith-app) [![Static Code Analysis issues](https://badger.engprod-pro.mpi-internal.com/badge/issues/scmspain/meeting-monolith-app)](https://badger.engprod-pro.mpi-internal.com/redirect/issues/scmspain/meeting-monolith-app) [![Flaky tests](https://badger.engprod-pro.mpi-internal.com/badge/flaky_tests/scmspain/meeting-monolith-app)](https://badger.engprod-pro.mpi-internal.com/redirect/flaky_tests/scmspain/meeting-monolith-app) [![Quality Index](https://badger.engprod-pro.mpi-internal.com/badge/quality_index/scmspain/meeting-monolith-app)](https://badger.engprod-pro.mpi-internal.com/redirect/quality_index/scmspain/meeting-monolith-app) [![Latest delivery](https://badger.engprod-pro.mpi-internal.com/badge/delivery/scmspain/meeting-monolith-app)](https://badger.engprod-pro.mpi-internal.com/redirect/delivery/scmspain/meeting-monolith-app) [![Kubernetes](https://badger.engprod-pro.mpi-internal.com/badge/kubernetes/scmspain/meeting-monolith-app)](https://badger.engprod-pro.mpi-internal.com/redirect/kubernetes/scmspain/meeting-monolith-app) [![Badger](https://badger.engprod-pro.mpi-internal.com/badge/engprod/scmspain/meeting-monolith-app)](https://badger.engprod-pro.mpi-internal.com/redirect/engprod/scmspain/meeting-monolith-app)
<!-- Badger end badges -->

## The goal of this project

MEETUP APPLICATION

El objetivo es implementar una aplicación de MeetUp similar a http://meetup.com utilizando patrones tácticos de DDD.

## Domain

El dominio de Meetup fue elegido para este propósito ya que es un áera de negocio con una 
complejidad limitada y a la que creo que estamos familiarizados en su uso, conceptualmente 
no es dificil de entender y tiene ciertas reglas de negocio que se deben implementar en el sistema.

### Domain description

Esto es un resumen de la información que obtenemos dee los expertos del dominio sobre cómo deberían de funcionar 
una aplicación de Meetup.

#### Grupos de Meetup



## Run

```
docker-compose up -d
./gradlew run
```

## Test

```
./gradlew test
```

## Integration Test

Default [ApplicationIntegrationTest](src/integration-test/java/cabanas/garcia/ismael/meetup/infrastructure/ApplicationIntegrationTest.kt) uses [testcontainers](https://www.testcontainers.org/) and [docker-compose.yml](docker-compose.yml)

You can add additional @Nested testcases sharing the same docker-compose execution or use any other approach to your liking

```
./gradlew integrationTest
```

## Useful links

- [Travis](https://travis.mpi-internal.com/scmspain/meeting-monolith-app)