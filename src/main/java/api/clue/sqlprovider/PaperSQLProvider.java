package api.clue.sqlprovider;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class PaperSQLProvider extends DynamicSQLProvider{

    // 論文検索用の動的SQL
    public String select(
            @Param("task") String task,
            @Param("title") String title,
            @Param("introduction") String introduction,
            @Param("lang") String lang,
            @Param("conference") String conference
    ) {
        return new SQL() {
            {
                SELECT("*");
                FROM("papers");
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
            }
        }.toString();
    }

    // 論文情報追加用の動的SQL
    public String insert(
            @Param("year") int year,
            @Param("label") String label,
            @Param("task") String task,
            @Param("title") String title,
            @Param("url") String url,
            @Param("introduction") String introduction,
            @Param("conference") String conference
    ) {
        return new SQL() {
            {
                INSERT_INTO("papers");
                VALUES("id", "0");
                if (year > 0) {
                    VALUES("year", "#{year}");
                }
                if (label != null && task != null) {
                    VALUES("label", "#{label}");
                    VALUES("task", "#{task}");
                }
                if (title != null) {
                    VALUES("title", "#{title}");
                }
                if (url != null && isURL(url)) {
                    VALUES("url", "#{url}");
                }
                if (introduction != null) {
                    VALUES("introduction", "#{introduction}");
                }
                if (conference != null) {
                    VALUES("conference", "#{conference}");
                }

            }
        }.toString();
    }

    // 論文情報更新用の動的SQL
    public String update(
            @Param("id") int id,
            @Param("year") int year,
            @Param("label") String label,
            @Param("task") String task,
            @Param("title") String title,
            @Param("url") String url,
            @Param("introduction") String introduction,
            @Param("conference") String conference
    ) {
        return new SQL() {
            {
                UPDATE("papers");
                if (year > 0) {
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
                if (conference != null) {
                    SET("conference = #{conference}");
                }
                WHERE("id = #{id}");
            }
        }.toString();
    }
}
