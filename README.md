# Kotlin Domain Driven Design (DDD) example project

<!-- Badger start badges -->

<!-- Badger end badges -->

## The goal of this project

[NAME] APPLICATION



## Domain


### Domain description



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

