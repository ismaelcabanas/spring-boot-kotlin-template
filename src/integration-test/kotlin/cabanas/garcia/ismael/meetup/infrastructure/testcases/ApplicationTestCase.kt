package cabanas.garcia.ismael.meetup.infrastructure.testcases

import cabanas.garcia.ismael.meetup.infrastructure.IntegrationTest
import io.restassured.module.mockmvc.RestAssuredMockMvc.given
import io.restassured.module.mockmvc.RestAssuredMockMvc.mockMvc
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.availability.AvailabilityChangeEvent.publish
import org.springframework.boot.availability.LivenessState
import org.springframework.boot.availability.ReadinessState
import org.springframework.context.ApplicationEventPublisher
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

abstract class ApplicationTestCase : IntegrationTest(){

  @Autowired
  private lateinit var eventPublisher: ApplicationEventPublisher

  @BeforeEach
  fun setUp() {
    mockMvc(mvc)
  }

  @AfterEach
  fun tearDown() {
    publish(eventPublisher, this, LivenessState.CORRECT)
    publish(eventPublisher, this, ReadinessState.ACCEPTING_TRAFFIC)
  }

  @Test
  fun should_be_healthy() {
    given()
      .`when`()
      .get("/actuator/health")
      .then()
      .assertThat(status().isOk)
      .body("status", equalTo("UP"))
  }

  @Test
  fun should_say_hello() {
    given()
      .`when`()
      .get("/hello")
      .then()
      .assertThat(status().isOk)
      .content(equalTo("Hello world"))
  }

  @Test
  fun should_expose_info() {
    given()
      .`when`()
      .get("/actuator/info")
      .then()
      .assertThat(status().isOk)
  }

  @Test
  fun should_expose_swagger() {
    given()
      .`when`()
      .get("/schema")
      .then()
      .assertThat(status().isOk)
      .body("paths.'/hello'.get.summary", equalTo("sayHello"))
  }

  @Test
  fun should_not_expose_shutdown_endpoint() {
    given()
      .`when`()
      .get("/actuator/shutdown")
      .then()
      .assertThat(status().is4xxClientError)
  }

  @Test
  fun should_be_alive() {
    given()
      .`when`()
      .get("/actuator/health/liveness")
      .then()
      .assertThat(status().isOk)
      .body("status", equalTo("UP"))
  }

  @Test
  fun should_not_be_alive() {
    publish(eventPublisher, this, LivenessState.BROKEN)
    given()
      .`when`()
      .get("/actuator/health/liveness")
      .then()
      .body("status", equalTo("DOWN"))
      .assertThat(status().isServiceUnavailable)
  }

  @Test
  fun should_be_ready() {
    given()
      .`when`()
      .get("/actuator/health/readiness")
      .then()
      .assertThat(status().isOk)
      .body("status", equalTo("UP"))
  }

  @Test
  fun should_not_be_ready() {
    publish(eventPublisher, this, ReadinessState.REFUSING_TRAFFIC)
    given()
      .`when`()
      .get("/actuator/health/readiness")
      .then()
      .body("status", equalTo("OUT_OF_SERVICE"))
      .assertThat(status().isServiceUnavailable)
  }
}
