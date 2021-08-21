package cabanas.garcia.ismael.meetup.infrastructure.configuration

import cabanas.garcia.ismael.meetup.infrastructure.spring.autoconfig.security.DefaultWebSecurityConfigurerAdapter
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity

@Configuration
@EnableWebSecurity
@Suppress("EmptyClassBlock")
class WebSecurityConfiguration : DefaultWebSecurityConfigurerAdapter() {
  /**
   * spring-autoconfig applies DefaultWebSecurityConfigurerAdapter security settings
   * if no other WebSecurityConfigurerAdapter is found in the spring context.
   *
   * This class is only a reminder that security settings can be enhanced or overriden.
   * You could:
   * - Leave this class as it is and spring-autoconfig settings will be applied via inheritance.
   * - Remove this class and spring-autoconfig settings will be applied via autostarter.
   * - Combine super.configure(HttpSecurity) with extra calls to HttpSecurity parameter.
   * - Make this class extend from WebSecurityConfigurerAdapter and configure security from scratch.
   */
}
