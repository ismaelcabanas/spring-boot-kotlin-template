package cabanas.garcia.ismael.meetup.infrastructure

import cabanas.garcia.ismael.meetup.infrastructure.testcontainer.KafkaTestContainerService
import cabanas.garcia.ismael.meetup.infrastructure.testcontainer.TestContainerInitializer
import org.apache.kafka.clients.consumer.Consumer
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import java.time.Duration

@SpringBootTest(
  classes = [Application::class],
  properties = ["spring.profiles.active=integration-test"]
)
@ContextConfiguration(initializers = [TestContainerInitializer::class])
@AutoConfigureMockMvc
abstract class IntegrationTest {

  @Autowired
  protected lateinit var mvc: MockMvc

  fun createConsumerFor(topic: String, consumerGroup: String): Consumer<String, String> {
    val props = mutableMapOf<String, Any>(
      ConsumerConfig.GROUP_ID_CONFIG to consumerGroup,
      ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java,
      ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java,
      ConsumerConfig.AUTO_OFFSET_RESET_CONFIG to "earliest",
      ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to KafkaTestContainerService.bootstrapServers
    )

    return DefaultKafkaConsumerFactory<String, String>(props, StringDeserializer(), StringDeserializer())
      .createConsumer()
      .apply {
        subscribe(listOf(topic))
        poll(Duration.ofSeconds(10))
      }
  }
}
