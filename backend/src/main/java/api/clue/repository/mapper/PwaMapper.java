package api.clue.repository.mapper;

import api.clue.domain.Author;
import api.clue.domain.Paper;
import api.clue.repository.mapper.sqlprovider.DynamicSqlProvider;
import java.util.List;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

@Mapper
public interface PwaMapper {

  @SelectProvider(value = PwaSqlProvider.class, method = "findPapersByAuthor")
  @Results(id = "Paper", value = {
      @Result(column = "paper_id", many = @Many(select = "api.clue.repository.mapper.PaperMapper.findById"))
  })
  List<Paper> findPapersByAuthorId(Author author);

  @SelectProvider(value = PwaSqlProvider.class, method = "findAuthorsByPaper")
  @Results(id = "Author", value = {
      @Result(column = "author_id", many = @Many(select = "api.clue.repository.mapper.AuthorMapper.findById"))
  })
  List<Author> findAuthorsByPaperId(Paper paper);

  @InsertProvider(value = PwaSqlProvider.class, method = "insert")
  void insert(Author author, Paper paper);

  class PwaSqlProvider extends DynamicSqlProvider {

    public String findPapersByAuthor(Author author) {
      return new SQL()
          .SELECT("paper_id")
          .FROM("paper_written_author")
          .WHERE("author_id = #{authorId}")
          .toString();
    }

    public String findAuthorsByPaper(Paper paper) {
      return new SQL()
          .SELECT("author_id")
          .FROM("paper_written_author")
          .WHERE("paper_id = #{paperId}")
          .toString();
    }

    public String insert(Author author, Paper paper) {
      return new SQL()
          .INSERT_INTO("paper_written_author")
          .VALUES("author_id, paper_id", "#{author.authorId}, #{paper.paperId}")
          .toString();
    }
  }
}
