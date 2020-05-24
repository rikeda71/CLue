package api.clue.repository.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.jdbc.SQL;

import api.clue.domain.Author;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

@Mapper
public interface AuthorMapper {

  @SelectProvider(value = AuthorSqlProvider.class, method = "find")
  @Results(id = "Author", value = {
      @Result(property = "authorId", column = "author_id")
  })
  Author findById(Long authorId);

  @SelectProvider(value = AuthorSqlProvider.class, method = "find")
  @ResultMap(value = "Author")
  List<Author> findByAuthorName(String name);

  @Insert("INSERT INTO authors(name) VALUES(#{name})")
  void insertAuthor(Author author);

  @Delete("DELETE FROM authors WHERE author_id = #{authorId}")
  void deleteAuthor(Long authorId);

  class AuthorSqlProvider {

    public String find(Long authorId, String name) {
      return new SQL() {
        {
          SELECT("*");
          FROM("authors");
          if (name != null) {
            WHERE("name LIKE CONCAT('%', #{name}, '%')");
          }
          if (authorId != null) {
            WHERE("author_id = #{authorId}");
          }
          ORDER_BY("id ASC");
        }
      }.toString();
    }
  }
}
