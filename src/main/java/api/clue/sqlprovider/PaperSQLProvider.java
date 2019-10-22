package api.clue.sqlprovider;

import api.clue.domain.Paper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class PaperSQLProvider extends DynamicSQLProvider{

    // 論文検索用の動的SQL
    public String select(
            @Param("year") Integer year,
            @Param("task") String task,
            @Param("title") String title,
            @Param("introduction") String introduction,
            @Param("lang") String lang,
            @Param("conference") String conference,
            @Param("author") String author
    ) {
        return new SQL() {
            {
                SELECT("*");
                FROM("papers");
                if (year != null) {
                    WHERE("year = #{year}");
                }
                if (task != null) {
                    WHERE("task = #{task}");
                }
                if (title != null) {
                    WHERE("title LIKE CONCAT('%', #{title}, '%')");
                }
                if (introduction != null) {
                    WHERE("introduction LIKE CONCAT('%', #{introduction}, '%')");
                }
                if (lang != null) {
                    WHERE("lang = #{lang}");
                }
                if (conference != null) {
                    WHERE("conference = #{conference}");
                }
                if (author != null) {
                    String firstCondition = "(SELECT paper_id FROM paper_written_author WHERE author_id IN";
                    String secondCondition = "(SELECT id FROM authors WHERE name = #{author}))";
                    WHERE("id IN" + firstCondition + secondCondition);
                }
                ORDER_BY("year DESC");
            }
        }.toString();
    }

    // 論文情報追加用の動的SQL
    public String insert(Paper paper) {
        return new SQL() {
            {
                INSERT_INTO("papers");
                VALUES("id", "0");
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
    public String update(
            @Param("id") int id,
            @Param("year") Integer year,
            @Param("label") String label,
            @Param("task") String task,
            @Param("title") String title,
            @Param("url") String url,
            @Param("introduction") String introduction,
            @Param("lang") String lang,
            @Param("conference") String conference
    ) {
        return new SQL() {
            {
                UPDATE("papers");
                if (year != null) {
                    SET("year = #{year}");
                }
                if (label != null && task != null) {
                    SET("label = #{label}");
                    SET("task = #{task}");
                }
                if (title != null) {
                    SET("title = #{title}");
                }
                if (url != null && isURL(url)) {
                    SET("url = #{url}");
                }
                if (introduction != null) {
                    SET("introduction = #{introduction}");
                }
                if (lang != null) {
                    SET("lang = #{lang}");
                }
                if (conference != null) {
                    SET("conference = #{conference}");
                }
                WHERE("id = #{id}");
            }
        }.toString();
    }
}
