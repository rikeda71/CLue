package api.clue.repository.mapper;

import api.clue.domain.Year;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface YearMapper {

  @Select("SELECT * FROM year ORDER BY year ASC")
  @Results(id = "Year", value = {
      @Result(property = "yearId", column = "year_id"),
      @Result(property = "year", column = "year")
  })
  List<Year> findAll();

}
