package api.clue.controller;

import api.clue.domain.User;
import api.clue.service.UserService;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

  private UserService userService;

  public AuthController(UserService userService) {
    this.userService = userService;
  }

  // @PostMapping("/signup")
  // public String login(@ModelAttribute("signup") User user){
  //   String token = userService.signUp(user);
  //   return UriComponentsBuilder.fromUriString(homeUrl)
  //       .queryParam("auth_token", token)
  //       .build().toUriString();
  // }
}
