package api.clue.domain;

import lombok.Data;

@Data
public class GoogleOAuth2UserInfo {

  private String id;

  private String name;

  private String email;

  private String imageUrl;

}
