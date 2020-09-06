package api.clue.service;

import api.clue.domain.Task;
import api.clue.repository.TaskRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

  private final TaskRepository taskRepository;

  public TaskServiceImpl(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  @Override
  public List<Task> findAll() {
    return this.taskRepository.findAll();
  }
}
