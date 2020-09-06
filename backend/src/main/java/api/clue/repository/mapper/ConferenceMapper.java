package api.clue.repository.mapper;

import api.clue.domain.Conference;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ConferenceMapper {

  @Select("SELECT * FROM conference ORDER BY conference_id ASC")
  @Results(id = "Conference", value = {
      @Result(property = "conferenceId", column = "conference_id"),
      @Result(property = "conferenceName", column = "conference_name")
  })
  List<Conference> findAll();

}
