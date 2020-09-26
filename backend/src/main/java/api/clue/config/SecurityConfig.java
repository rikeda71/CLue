package api.clue.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.client.web.HttpSessionOAuth2AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final OidcUserService oidcUserService;

  private final CustomAuthenticationSuccessfulHandler authenticationSuccessfulHandler;

  public SecurityConfig(OidcUserService oidcUserService, CustomAuthenticationSuccessfulHandler authenticationSuccessfulHandler) {
    this.oidcUserService = oidcUserService;
    this.authenticationSuccessfulHandler = authenticationSuccessfulHandler;
  }

  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // TODO: this is temporary settings. need to fix
    http.cors().configurationSource(this.corsConfigurationSource()) // cors
        .and().antMatcher("/**").authorizeRequests()
        .and().authorizeRequests().antMatchers(HttpMethod.GET).permitAll()
        .and().oauth2Login().redirectionEndpoint().baseUri("/oauth2/callback/*")
        .and().userInfoEndpoint().oidcUserService(oidcUserService)
        .and().authorizationEndpoint().baseUri("/oauth2/authorize").authorizationRequestRepository(customAuthorizationRequestRepository())
        .and().successHandler(authenticationSuccessfulHandler);
        // .and().csrf().disable()
        // .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  }

  @Bean
  public AuthorizationRequestRepository<OAuth2AuthorizationRequest> customAuthorizationRequestRepository() {
    return new HttpSessionOAuth2AuthorizationRequestRepository();
  }

  private CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.addAllowedHeader(CorsConfiguration.ALL);
    configuration.addAllowedMethod(CorsConfiguration.ALL);
    configuration.addAllowedHeader("Authorization ");
    configuration.addAllowedOrigin("http://localhost:3000");
    configuration.addAllowedOrigin("http://0.0.0.0:3000");
    configuration.setAllowCredentials(true);

    UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
    corsSource.registerCorsConfiguration("/**", configuration);
    return corsSource;
  }

}
