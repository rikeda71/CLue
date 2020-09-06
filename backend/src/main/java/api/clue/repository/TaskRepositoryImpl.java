package api.clue.repository;

import api.clue.domain.Task;
import api.clue.repository.mapper.TaskMapper;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class TaskRepositoryImpl implements TaskRepository {

  private final TaskMapper taskMapper;

  public TaskRepositoryImpl(TaskMapper taskMapper) {
    this.taskMapper = taskMapper;
  }

  @Override
  public List<Task> findAll() {
    return this.taskMapper.findAll();
  }
}
