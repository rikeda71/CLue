package api.clue.repository.mapper;

import api.clue.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

  @Select("SELECT * FROM users WHERE email = #{email}")
  @Results(id = "Task", value = {
      @Result(property = "id", column = "id"),
      @Result(property = "name", column = "name"),
      @Result(property = "email", column = "email"),
      @Result(property = "imageUrl", column = "image_url"),
      @Result(property = "userType", column = "user_type")
  })
  User findByEmail(String email);

  @Insert("INSERT INTO users(name, email, image_url, user_type) VALUES(#{name}, #{email}, #{imageUrl}, #{userType})")
  void save(User user);
}
