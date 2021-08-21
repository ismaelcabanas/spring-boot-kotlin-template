package cabanas.garcia.ismael.meetup.infrastructure

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication(
  scanBasePackages = [
    "cabanas.garcia.ismael.meetup.infrastructure.controller",
    "cabanas.garcia.ismael.meetup.infrastructure.configuration"
  ]
)

@EnableFeignClients
@EnableCircuitBreaker
class Application

@Suppress("SpreadOperator")
fun main(args: Array<String>) {
  runApplication<Application>(*args)
}
