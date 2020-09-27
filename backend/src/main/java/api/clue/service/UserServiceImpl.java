package api.clue.service;

import api.clue.domain.User;
import api.clue.repository.UserRepository;
import api.clue.util.JwtTokenUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  private final BCryptPasswordEncoder bcryptEncoder;

  public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bcryptEncoder) {
    this.userRepository = userRepository;
    this.bcryptEncoder = bcryptEncoder;
  }

  @Override
  public String signUp(User user) {
    User dbUser = userRepository.findByEmail(user.getEmail());
    if (dbUser != null) {
      throw new RuntimeException("User already exist.");
    }
    user.setPassword(bcryptEncoder.encode(user.getPassword()));
    userRepository.save(user);
    return JwtTokenUtil.generateToken(user);
  }

}
