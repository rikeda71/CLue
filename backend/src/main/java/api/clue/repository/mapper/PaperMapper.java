package api.clue.repository.mapper;

import api.clue.domain.Paper;
import api.clue.domain.PaperSearchProvider;
import api.clue.repository.mapper.sqlprovider.DynamicSqlProvider;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.jdbc.SQL;

@Mapper
public interface PaperMapper {

  @Select("SELECT * FROM papers WHERE paper_id = #{paperId}")
  @Results(id = "Paper", value = {
      @Result(property = "paperId", column = "paper_id"),
      @Result(property = "authors", column = "paper_id", many = @Many(select = "api.clue.repository.mapper.PwaMapper.findAuthorsByPaperId"))
  })
  Paper findById(Long paperId);

  @SelectProvider(type = PaperSqlProvider.class, method = "find")
  @ResultMap(value = "Paper")
  List<Paper> findPapers(PaperSearchProvider provider, Integer offset, Integer limit);

  @InsertProvider(type = PaperSqlProvider.class, method = "insert")
  @ResultMap(value = "Paper")
  void insert(Paper paper);

  @UpdateProvider(type = PaperSqlProvider.class, method = "update")
  void update(Paper paper);

  @Delete("DELETE FROM papers WHERE paper_id = #{paperId}")
  void delete(Long paperId);

  class PaperSqlProvider extends DynamicSqlProvider {
    public String find(PaperSearchProvider provider, Integer offset, Integer limit) {
      return new SQL() {
        {
          SELECT("paper_id, year, label, task, session, title, "
              + "url, introduction, lang, conference");
          FROM("papers");
          if (provider.getYear() != null) {
            WHERE("year = " + provider.getYear());
          }
          if (provider.getTask() != null) {
            WHERE("task = '" + provider.getTask() + "'");
          }
          if (provider.getTitle() != null) {
            WHERE("title LIKE CONCAT('%', '" + provider.getTitle() + "', '%')");
          }
          if (provider.getIntroduction() != null) {
            WHERE("introduction LIKE CONCAT('%', '" + provider.getIntroduction() + "', '%')");
          }
          if (provider.getLang() != null) {
            WHERE("lang = '" + provider.getLang() + "'");
          }
          if (provider.getConference() != null) {
            WHERE("conference = '" + provider.getConference() + "'");
          }
          ORDER_BY("year DESC, paper_id ASC");
          OFFSET(offset * limit);
          LIMIT(limit);
        }
      }.toString();
    }

    // 論文情報追加用の動的SQL
    public String insert(Paper paper) {
      return new SQL() {
        {
          INSERT_INTO("papers");
          if (paper.getYear() > 0) {
            VALUES("year", "#{year}");
          }
          if (paper.getLabel() != null && paper.getTask() != null) {
            VALUES("label", "#{label}");
            VALUES("task", "#{task}");
          }
          if (paper.getTitle() != null) {
            VALUES("title", "#{title}");
          }
          if (paper.getUrl() != null && isURL(paper.getUrl())) {
            VALUES("url", "#{url}");
          }
          if (paper.getIntroduction() != null) {
            VALUES("introduction", "#{introduction}");
          }
          if (paper.getLang() != null) {
            VALUES("lang", "#{lang}");
          }
          if (paper.getConference() != null) {
            VALUES("conference", "#{conference}");
          }
        }
      }.toString();
    }

    // 論文情報更新用の動的SQL
    public String update(Paper paper) {
      return new SQL() {
        {
          UPDATE("papers");
          if (paper.getYear() != null) {
            SET("year = #{year}");
          }
          if (paper.getLabel() != null && paper.getTask() != null) {
            SET("label = #{label}");
            SET("task = #{task}");
          }
          if (paper.getTitle() != null) {
            SET("title = #{title}");
          }
          if (paper.getUrl() != null && isURL(paper.getUrl())) {
            SET("url = #{url}");
          }
          if (paper.getIntroduction() != null) {
            SET("introduction = #{introduction}");
          }
          if (paper.getLang() != null) {
            SET("lang = #{lang}");
          }
          if (paper.getConference() != null) {
            SET("conference = #{conference}");
          }
          WHERE("paper_id = #{paperId}");
        }
      }.toString();
    }
  }

}
