package api.clue.repository.mapper;

import api.clue.domain.Task;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TaskMapper {

  @Select("SELECT * FROM task ORDER BY task_id ASC")
  @Results(id = "Task", value = {
      @Result(property = "taskId", column = "task_id"),
      @Result(property = "taskName", column = "task_name"),
      @Result(property = "taskLabelName", column = "task_label_name")
  })
  List<Task> findAll();

}
