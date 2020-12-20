package api.clue.config;

import static api.clue.config.Constants.FRONTEND_URL;

import api.clue.security.CookieOAuth2AuthorizationRequestRepository;
import api.clue.security.Oauth2AuthenticationFailureHandler;
import api.clue.security.Oauth2AuthenticationSuccessHandler;
import api.clue.security.JwtAuthenticationFilter;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final OidcUserService oidcUserService;

  private final UserDetailsService userDetailsService;

  private final Oauth2AuthenticationSuccessHandler successHandler;

  private final Oauth2AuthenticationFailureHandler failureHandler;

  private final CookieOAuth2AuthorizationRequestRepository oauth2RequestRepository;

  public SecurityConfig(OidcUserService oidcUserService, UserDetailsService userDetailsService,
      Oauth2AuthenticationSuccessHandler successHandler,
      Oauth2AuthenticationFailureHandler failureHandler,
      CookieOAuth2AuthorizationRequestRepository oauth2RequestRepository
  ) {
    this.oidcUserService = oidcUserService;
    this.userDetailsService = userDetailsService;
    this.successHandler = successHandler;
    this.failureHandler = failureHandler;
    this.oauth2RequestRepository = oauth2RequestRepository;
  }

  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.cors().configurationSource(this.corsConfigurationSource()) // cors
        .and().authorizeRequests()
        .antMatchers(HttpMethod.GET).permitAll()
        .anyRequest().authenticated()
        .and().oauth2Login()
        .userInfoEndpoint().oidcUserService(oidcUserService)
        .and().authorizationEndpoint().baseUri("/oauth2/authorize")
        .authorizationRequestRepository(this.oauth2RequestRepository)
        .and().successHandler(this.successHandler).failureHandler(this.failureHandler)
        .and().csrf().disable()
        .addFilterBefore(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
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
    configuration.addAllowedOrigin(FRONTEND_URL);
    configuration.addAllowedOrigin(FRONTEND_URL.replaceFirst("localhost", "0.0.0.0"));
    configuration.setAllowCredentials(true);

    UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
    corsSource.registerCorsConfiguration("/**", configuration);
    return corsSource;
  }

}
