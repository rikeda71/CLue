package api.clue.controller;

import api.clue.domain.ResponseUser;
import api.clue.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

  /**
   * ログインユーザー情報を返すエンドポイント
   * @param user ログインユーザー情報
   * @return ログインユーザー情報
   */
  @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ResponseUser> get(@AuthenticationPrincipal User user) {
    if (user == null || user.getEmail() == null) {
      return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    var responseUser = ResponseUser.builder()
        .id(user.getId())
        .name(user.getName())
        .email(user.getEmail())
        .imageUrl(user.getImageUrl())
        .userType(user.getUserType()).build();
    System.out.println(responseUser);
    return new ResponseEntity<>(responseUser, HttpStatus.OK);
  }

}
