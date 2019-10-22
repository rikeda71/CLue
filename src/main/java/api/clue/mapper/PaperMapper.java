package api.clue.mapper;

import api.clue.domain.Paper;
import api.clue.sqlprovider.PaperSQLProvider;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;

@Mapper
public interface PaperMapper {

    @Select("SELECT * FROM papers WHERE id = #{id}")
    Paper findById(int id);

    @SelectProvider(type= PaperSQLProvider.class, method = "select")
    List<Paper> findPapers(
            @Param("year") Integer year,
            @Param("task") String task,
            @Param("title") String title,
            @Param("introduction") String introduction,
            @Param("lang") String lang,
            @Param("conference") String conference,
            @Param("author") String author
    );

    @InsertProvider(type=PaperSQLProvider.class, method = "insert")
    @SelectKey(statement="select LAST_INSERT_ID()", keyProperty="id", before=false, resultType=int.class)
    void insertPaper(Paper paper);

    @UpdateProvider(type=PaperSQLProvider.class, method = "update")
    void updatePaper(
            @Param("id") int id,
            @Param("year") Integer year,
            @Param("label") String label,
            @Param("task") String task,
            @Param("title") String title,
            @Param("url") String url,
            @Param("introduction") String introduction,
            @Param("lang") String lang,
            @Param("conference") String conference,
            @Param("authors") List<String> authors
    );

    @Delete("DELETE FROM papers WHERE id = #{id}")
    void deletePaper(int id);

}
