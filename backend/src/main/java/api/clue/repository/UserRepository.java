package api.clue.repository;

import api.clue.domain.User;

public interface UserRepository {

  User findByEmail(String email);

  void save(User user);

}
