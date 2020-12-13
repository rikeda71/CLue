/**
 * ref: https://github.com/callicoder/spring-boot-react-oauth2-social-login-demo/blob/ada77d0f23ecd511a089ecbdcd6e89aa8d9a49b6/spring-social/src/main/java/com/example/springsocial/security/oauth2/HttpCookieOAuth2AuthorizationRequestRepository.java
 */

package api.clue.security;

import static api.clue.config.Constants.OAUTH2_REQUEST_COOKIE_NAME;
import static api.clue.config.Constants.REDIRECT_URI_PARAM_COOKIE_NAME;

import api.clue.util.CookieUtil;
import com.nimbusds.oauth2.sdk.util.StringUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.stereotype.Component;

@Component
public class CookieOAuth2AuthorizationRequestRepository
    implements AuthorizationRequestRepository<OAuth2AuthorizationRequest> {

  private static final int cookieExpireSeconds = 180;

  @Override
  public OAuth2AuthorizationRequest loadAuthorizationRequest(HttpServletRequest request) {
    return CookieUtil.getCookie(request, OAUTH2_REQUEST_COOKIE_NAME)
        .map(cookie -> CookieUtil.deserialize(cookie, OAuth2AuthorizationRequest.class))
        .orElse(null);
  }

  @Override
  public void saveAuthorizationRequest(
      OAuth2AuthorizationRequest authorizationRequest,
      HttpServletRequest request, HttpServletResponse response
  ) {
    if (authorizationRequest == null) {
      CookieUtil.deleteCookie(request, response, OAUTH2_REQUEST_COOKIE_NAME);
      CookieUtil.deleteCookie(request, response, REDIRECT_URI_PARAM_COOKIE_NAME);
      return;
    }

    CookieUtil.addCookie(response, OAUTH2_REQUEST_COOKIE_NAME,
        CookieUtil.serialize(authorizationRequest), cookieExpireSeconds);
    String redirectUriAfterLogin = request.getParameter(REDIRECT_URI_PARAM_COOKIE_NAME);
    if (StringUtils.isNotBlank(redirectUriAfterLogin)) {
      CookieUtil.addCookie(response, REDIRECT_URI_PARAM_COOKIE_NAME, redirectUriAfterLogin,
          cookieExpireSeconds);
    }
  }

  @Override
  public OAuth2AuthorizationRequest removeAuthorizationRequest(HttpServletRequest request) {
    return this.loadAuthorizationRequest(request);
  }

  public void removeAuthorizationRequestCookies(HttpServletRequest request,
      HttpServletResponse response) {
    CookieUtil.deleteCookie(request, response, OAUTH2_REQUEST_COOKIE_NAME);
    CookieUtil.deleteCookie(request, response, REDIRECT_URI_PARAM_COOKIE_NAME);
  }

}
