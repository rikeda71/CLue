package api.clue.mapper;

import api.clue.domain.Author;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AuthorMapper {
    @Select("SELECT * from authors")
    List<Author> findAll();

    @Select("SELECT name from authors where id = #{id}")
    String findById(@Param("id") Integer id);

}
