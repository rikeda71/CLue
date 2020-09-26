package api.clue.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

  private int id;

  private String name;

  private String email;

  private String imageUrl;

  private UserType userType;
}
