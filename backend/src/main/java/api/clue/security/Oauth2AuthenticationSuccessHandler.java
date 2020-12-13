package api.clue.security;

import static api.clue.config.Constants.REDIRECT_URI_PARAM_COOKIE_NAME;

import api.clue.domain.User;
import api.clue.repository.UserRepository;
import api.clue.util.CookieUtil;
import api.clue.util.JwtTokenUtil;
import java.io.IOException;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.web.util.UriComponentsBuilder;

@Configuration
public class Oauth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

  private final UserRepository userRepository;

  private final CookieOAuth2AuthorizationRequestRepository oauth2RequestRepository;

  public Oauth2AuthenticationSuccessHandler(
      UserRepository userRepository,
      CookieOAuth2AuthorizationRequestRepository oauth2RequestRepository
  ) {
    this.userRepository = userRepository;
    this.oauth2RequestRepository = oauth2RequestRepository;
  }

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {
    String targetUrl = determineTargetUrl(request, response, authentication);

    if (response.isCommitted()) {
      logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
      return;
    }

    clearAuthenticationAttributes(request, response);
    getRedirectStrategy().sendRedirect(request, response, targetUrl);
  }

  protected String determineTargetUrl(
      HttpServletRequest request,
      HttpServletResponse response,
      Authentication authentication
  ) {
    Optional<String> redirectUri = CookieUtil.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME)
        .map(Cookie::getValue);

    String targetUrl = redirectUri.orElse(getDefaultTargetUrl());
    String token = getJwtToken(authentication);
    return UriComponentsBuilder.fromUriString(targetUrl)
        .queryParam("token", token)
        .build().toUriString();
  }

  protected void clearAuthenticationAttributes(HttpServletRequest request,
      HttpServletResponse response) {
    super.clearAuthenticationAttributes(request);
    oauth2RequestRepository.removeAuthorizationRequestCookies(request, response);
  }

  private String getJwtToken(Authentication authentication) {
    DefaultOidcUser oidcUser = (DefaultOidcUser) authentication.getPrincipal();
    var attributes = oidcUser.getAttributes();
    String email = (String) attributes.get("email");
    User user = userRepository.findByEmail(email);
    return JwtTokenUtil.generateToken(user);
  }

}
