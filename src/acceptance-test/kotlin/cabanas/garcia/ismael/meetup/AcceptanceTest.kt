package cabanas.garcia.ismael.meetup

import cabanas.garcia.ismael.meetup.infrastructure.Application
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(
  classes = [Application::class]
)
abstract class AcceptanceTest {
}
