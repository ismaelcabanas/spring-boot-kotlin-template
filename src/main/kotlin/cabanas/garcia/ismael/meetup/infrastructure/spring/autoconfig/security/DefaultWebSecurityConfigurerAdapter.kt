package cabanas.garcia.ismael.meetup.infrastructure.spring.autoconfig.security

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest
import org.springframework.boot.actuate.health.HealthEndpoint
import org.springframework.boot.actuate.info.InfoEndpoint
import org.springframework.boot.actuate.metrics.MetricsEndpoint
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

open class DefaultWebSecurityConfigurerAdapter
  : WebSecurityConfigurerAdapter(false) {
  override fun configure(http: HttpSecurity?) {
    /**
     * Requests to actuator /health not authenticated.
     * Any other requests to actuator authenticated with basic authentication.
     * Any other requests not authenticated.
     */
    http!!
      .authorizeRequests()
      .requestMatchers(EndpointRequest.to(MetricsEndpoint::class.java)).permitAll()
      .requestMatchers(EndpointRequest.to(HealthEndpoint::class.java)).permitAll()
      .requestMatchers(EndpointRequest.to(InfoEndpoint::class.java)).permitAll()
      .requestMatchers(EndpointRequest.toAnyEndpoint()).authenticated()
      .anyRequest().permitAll()
      .and().httpBasic()
  }
}
