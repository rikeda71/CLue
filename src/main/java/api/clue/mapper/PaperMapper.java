package api.clue.mapper;

import api.clue.domain.Paper;
import api.clue.sqlprovider.PaperSQLProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PaperMapper {

    @Select("SELECT * FROM papers WHERE id = #{id}")
    Paper findById(int id);

    @SelectProvider(type= PaperSQLProvider.class, method="select")
    List<Paper> findPapers(@Param("task") String task,
                           @Param("title") String title,
                           @Param("introduction") String introduction,
                           @Param("lang") String lang,
                           @Param("conference") String conference);

    @InsertProvider(type=PaperSQLProvider.class, method="insert")
    void insertPaper(@Param("year") int year,
                     @Param("label") String label,
                     @Param("task") String  task,
                     @Param("title") String title,
                     @Param("url") String url,
                     @Param("introduction") String introduction,
                     @Param("conference") String conference);

    @UpdateProvider(type=PaperSQLProvider.class, method="update")
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

}
