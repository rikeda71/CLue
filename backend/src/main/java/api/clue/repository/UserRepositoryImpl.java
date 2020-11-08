package api.clue.repository;

import api.clue.domain.User;
import api.clue.repository.mapper.UserMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

  private final UserMapper userMapper;

  public UserRepositoryImpl(UserMapper userMapper) {
    this.userMapper = userMapper;
  }

  @Override
  public User findByEmail(String email) {
    return this.userMapper.findByEmail(email);
  }

  @Override
  public void save(User user) {
    this.userMapper.save(user);
  }
}
