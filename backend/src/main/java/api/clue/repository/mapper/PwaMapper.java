package api.clue.repository.mapper;

import api.clue.domain.Author;
import api.clue.domain.Paper;
import api.clue.repository.mapper.sqlprovider.DynamicSqlProvider;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

@Mapper
public interface PwaMapper {

  @Select("SELECT * FROM papers WHERE paper_id in (SELECT paper_id FROM paper_written_author WHERE author_id = #{authorId})")
  @Results(id = "Paper", value = {
      @Result(property = "paperId", column = "paper_id")
  })
  List<Paper> findPapersByAuthorId(Author author);

  @Select("SELECT * FROM authors WHERE author_id in (SELECT author_id FROM paper_written_author WHERE paper_id = #{paperId})")
  @Results(id = "Author", value = {
      @Result(property = "authorId", column = "author_id")
  })
  List<Author> findAuthorsByPaperId(Paper paper);

  @InsertProvider(value = PwaSqlProvider.class, method = "insert")
  void insert(Author author, Paper paper);

  class PwaSqlProvider extends DynamicSqlProvider {

    public String insert(Author author, Paper paper) {
      return new SQL()
          .INSERT_INTO("paper_written_author")
          .VALUES("author_id", author.getAuthorId().toString())
          .VALUES("paper_id", paper.getPaperId().toString())
          .toString();
    }
  }
}
