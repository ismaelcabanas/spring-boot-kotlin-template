package cabanas.garcia.ismael.meetup.infrastructure.testcontainer

import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.testcontainers.containers.KafkaContainer
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.lifecycle.Startables
import org.testcontainers.utility.DockerImageName
import java.time.ZoneOffset
import java.util.*
import java.util.stream.Stream

class TestContainerInitializer : ApplicationContextInitializer<ConfigurableApplicationContext> {
  private val kafkaContainer = KafkaTestContainerService.instance
  private val postgreSqlContainer = PostgreSqlTestContainerService.instance

  override fun initialize(applicationContext: ConfigurableApplicationContext?) {
    TimeZone.setDefault(TimeZone.getTimeZone(ZoneOffset.UTC))
    Startables.deepStart(Stream.of(postgreSqlContainer)).join()
    TestPropertyValues.of(
      "database.url=${postgreSqlContainer.jdbcUrl}",
      "database.user=${postgreSqlContainer.username}",
      "database.password=${postgreSqlContainer.password}",
      "spring.cloud.stream.kafka.binder.brokers=${kafkaContainer.bootstrapServers}"
    ).applyTo(applicationContext)
  }

  companion object {
    private const val POSTGRES_DOCKER_IMAGE = "postgres:12.4"
  }
}

object KafkaTestContainerService {
  private const val KAFKA_DOCKER_IMAGE = "confluentinc/cp-kafka:5.4.1"

  val instance by lazy { startContainer() }

  val bootstrapServers: String get() = "${instance.host}:${instance.getMappedPort(KafkaContainer.KAFKA_PORT)}"

  private fun startContainer() =
    KafkaContainer(DockerImageName.parse(KAFKA_DOCKER_IMAGE))
      .apply {
        start()
      }
}

object PostgreSqlTestContainerService {
  private const val POSTGRES_DOCKER_IMAGE = "postgres:12.4"

  val instance by lazy { startContainer() }

  private fun startContainer() =
    PostgreSQLContainer<Nothing>(POSTGRES_DOCKER_IMAGE).apply {
      withDatabaseName("meetup")
      withUrlParam("currentSchema", "meetup")
      withReuse(true)
    }
}
