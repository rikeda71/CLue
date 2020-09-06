package api.clue.repository;

import api.clue.domain.Task;
import java.util.List;

public interface TaskRepository {

  List<Task> findAll();

}
