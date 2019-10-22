package api.clue.mapper;

import api.clue.domain.Author;
import api.clue.sqlprovider.AuthorSQLProvider;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

@Mapper
public interface AuthorMapper {
    @SelectProvider(value = AuthorSQLProvider.class, method = "select")
    List<Author> findAuthor(String name);

    @Select("SELECT name from authors where id = #{id}")
    String findById(@Param("id") Integer id);

    @Insert("INSERT INTO authors(id, name) VALUES(0, #{name})")
    @SelectKey(statement="select LAST_INSERT_ID()", keyProperty="id", before=false, resultType=int.class)
    void insertAuthor(Author author);

    @Delete("DELETE FROM authors WHERE id = #{id}")
    void deleteAuthor(int id);
}
