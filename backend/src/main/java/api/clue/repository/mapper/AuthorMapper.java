package api.clue.repository.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import api.clue.domain.Author;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthorMapper {

  @Select("SELECT * FROM authors WHERE author_id = #{authorId}")
  @Results(id = "Author", value = {
      @Result(property = "authorId", column = "author_id")
  })
  Author findById(Long authorId);

  @Select("SELECT * FROM authors WHERE name LIKE CONCAT('%', #{name}, '%') ORDER BY author_id ASC")
  @ResultMap(value = "Author")
  List<Author> findByAuthorName(String name);

  @Insert("INSERT INTO authors(name) VALUES(#{name})")
  void insertAuthor(Author author);

  @Delete("DELETE FROM authors WHERE author_id = #{authorId}")
  void deleteAuthor(Long authorId);

}
