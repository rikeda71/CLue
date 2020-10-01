package api.clue.config;

import api.clue.security.CustomAuthenticationHandler;
import api.clue.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.client.web.HttpSessionOAuth2AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final OidcUserService oidcUserService;

  private final UserDetailsService userDetailsService;

  private final CustomAuthenticationHandler authenticationHandler;

  public SecurityConfig(OidcUserService oidcUserService, UserDetailsService userDetailsService,
      CustomAuthenticationHandler authenticationHandler) {
    this.oidcUserService = oidcUserService;
    this.userDetailsService = userDetailsService;
    this.authenticationHandler = authenticationHandler;
  }

  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // TODO: this is temporary settings. need to fix
    http.cors().configurationSource(this.corsConfigurationSource()) // cors
        .and().authorizeRequests()
        .antMatchers(HttpMethod.GET).permitAll()
        .anyRequest().authenticated()
        .and().oauth2Login()
        .userInfoEndpoint().oidcUserService(oidcUserService)
        .and().authorizationEndpoint().baseUri("/oauth2/authorize")
        .authorizationRequestRepository(customAuthorizationRequestRepository())
        .and().successHandler(this.authenticationHandler).failureHandler(this.authenticationHandler)
        .and().csrf().disable()
        .addFilterBefore(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  }

  @Bean
  public AuthorizationRequestRepository<OAuth2AuthorizationRequest> customAuthorizationRequestRepository() {
    return new HttpSessionOAuth2AuthorizationRequestRepository();
  }

  @Bean
  public JwtAuthenticationFilter authenticationTokenFilter() {
    return new JwtAuthenticationFilter(this.userDetailsService);
  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
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
