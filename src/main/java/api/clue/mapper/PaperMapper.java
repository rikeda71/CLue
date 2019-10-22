package api.clue.mapper;

import api.clue.domain.Paper;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

@Mapper
public interface PaperMapper {

    @Select("SELECT * FROM papers WHERE id = #{id}")
    Paper findById(int id);

    @SelectProvider(type=findByAnySQLProvider.class, method="select")
    List<Paper> findPapers(@Param("task") String task,
                           @Param("title") String title,
                           @Param("introduction") String introduction,
                           @Param("lang") String lang,
                           @Param("conference") String conference);

    @InsertProvider(type=insertByAnySQLProvider.class, method="insert")
    void insertPaper(@Param("year") int year,
                     @Param("label") String label,
                     @Param("task") String  task,
                     @Param("title") String title,
                     @Param("url") String url,
                     @Param("introduction") String introduction,
                     @Param("conference") String conference);

    @UpdateProvider(type=updateByAnySQLProvider.class, method="update")
    void updatePaper(@Param("id") int id,
                     @Param("year") int year,
                     @Param("label") String label,
                     @Param("task") String task,
                     @Param("title") String title,
                     @Param("url") String url,
                     @Param("introduction") String introduction,
                     @Param("conference") String conference
                     );

    @Delete("DELETE FROM papers WHERE id = #{id}")
    void deletePaper(int id);

    // 論文検索用の動的SQL
    class findByAnySQLProvider {
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
    }

    // 論文情報追加用の動的SQL
    class insertByAnySQLProvider {
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
                    if (url != null) {
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
    }

    // 論文情報更新用の動的SQL
    class updateByAnySQLProvider {
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
                   if (url != null) {
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
}
