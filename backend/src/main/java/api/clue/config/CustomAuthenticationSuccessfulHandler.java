package api.clue.config;

import api.clue.domain.User;
import api.clue.repository.UserRepository;
import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class CustomAuthenticationSuccessfulHandler extends SimpleUrlAuthenticationSuccessHandler {

  private final UserRepository userRepository;

  private final String homeUrl = "http://localhost:3000/";

  public CustomAuthenticationSuccessfulHandler(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {
    DefaultOidcUser oidcUser = (DefaultOidcUser) authentication.getPrincipal();
    Map attributes = oidcUser.getAttributes();
    String email = (String) attributes.get("email");
    User user = userRepository.findByEmail(email);
    System.out.println(user);
    String token = JwtTokenUtil.generateToken(user);
    String redirectionUrl = UriComponentsBuilder.fromUriString(homeUrl)
        .queryParam("auth_token", token)
        .build().toUriString();
    getRedirectStrategy().sendRedirect(request, response, redirectionUrl);
  }
}
