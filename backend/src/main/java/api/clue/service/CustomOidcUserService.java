package api.clue.service;

import api.clue.domain.GoogleOAuth2UserInfo;
import api.clue.domain.User;
import api.clue.domain.UserType;
import api.clue.repository.UserRepository;
import java.util.Map;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

@Service
public class CustomOidcUserService extends OidcUserService {

  private UserRepository userRepository;

  public CustomOidcUserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
    OidcUser oidcUser = super.loadUser(userRequest);
    Map attributes = oidcUser.getAttributes();
    GoogleOAuth2UserInfo userInfo = new GoogleOAuth2UserInfo();
    userInfo.setEmail((String) attributes.get("email"));
    userInfo.setId((String) attributes.get("sub"));
    userInfo.setImageUrl((String) attributes.get("picture"));
    userInfo.setName((String) attributes.get("name"));
    updateUser(userInfo);
    return oidcUser;
  }

  private void updateUser(GoogleOAuth2UserInfo userInfo) {
    User user = userRepository.findByEmail(userInfo.getEmail());
    if (user != null) {
      return;
    }
    user = new User();
    user.setEmail(userInfo.getEmail());
    user.setImageUrl(userInfo.getImageUrl());
    user.setName(userInfo.getName());
    user.setUserType(UserType.google);
    userRepository.save(user);
  }

}
