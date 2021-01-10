package api.clue.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonInclude(Include.NON_NULL)
public class ResponseUser {

  int id;

  String name;

  String email;

  String imageUrl;

  UserType userType;
}
