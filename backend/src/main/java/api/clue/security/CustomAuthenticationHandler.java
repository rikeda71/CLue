package api.clue.security;

import api.clue.domain.User;
import api.clue.repository.UserRepository;
import api.clue.util.JwtTokenUtil;
import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.web.util.UriComponentsBuilder;

@Configuration
public class CustomAuthenticationHandler extends SimpleUrlAuthenticationSuccessHandler implements AuthenticationFailureHandler {

  private final UserRepository userRepository;

  private final MappingJackson2HttpMessageConverter httpMessageConverter;

  private final String frontendUrl = "http://localhost:3000/";

  public CustomAuthenticationHandler(UserRepository userRepository, MappingJackson2HttpMessageConverter httpMessageConverter) {
    this.userRepository = userRepository;
    this.httpMessageConverter = httpMessageConverter;
  }

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {
    DefaultOidcUser oidcUser = (DefaultOidcUser) authentication.getPrincipal();
    Map attributes = oidcUser.getAttributes();
    String email = (String) attributes.get("email");
    User user = userRepository.findByEmail(email);
    String token = JwtTokenUtil.generateToken(user);
    response.addCookie(new Cookie("auto_token", token));
    getRedirectStrategy().sendRedirect(request, response, frontendUrl);
    // String redirectionUrl = UriComponentsBuilder.fromUriString(frontendUrl)
    //     .queryParam("auth_token", token)
    //     .build().toUriString();
    // getRedirectStrategy().sendRedirect(request, response, redirectionUrl);
  }

  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException exception) throws IOException, ServletException {
    String token = "authentication failure";
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    httpMessageConverter.write(new Token(token), MediaType.APPLICATION_JSON, outputMessage);
    response.setStatus(HttpStatus.UNAUTHORIZED.value());
  }

  @Data
  @AllArgsConstructor
  public static class Token {
    private String token;
  }
}
