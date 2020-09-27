package api.clue.service;

import api.clue.domain.User;
import api.clue.repository.UserRepository;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserRepository userRepository;

  public UserDetailsServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  /**
   * emailからユーザ情報を取得するメソッド
   * UserDetailsServiceの仕様上、ByUsernameとなっているが、
   * Google認証を使っているので、emailアドレスを機転にユーザ情報を取得
   * @param email gmail address
   * @return user instance
   */
  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User user = this.userRepository.findByEmail(email);
    if (user == null) {
      throw new UsernameNotFoundException("Invalid email address or password");
    }
    // passwordはDBに格納していないため、imageUrlを適当に入れている
    return new org.springframework.security.core.userdetails.User(
        user.getEmail(), user.getImageUrl(), getAuthority()
    );
  }

  private List<SimpleGrantedAuthority> getAuthority() {
    return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
  }
}
