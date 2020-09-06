package api.clue.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import api.clue.domain.Task;
import api.clue.repository.TaskRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class TaskServiceImplTest {

  @Mock
  private TaskRepository taskRepository;

  @BeforeEach
  public void before() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testFindAll() {
    List<Task> tasks = new ArrayList<>();
    Mockito.doReturn(tasks).when(this.taskRepository).findAll();

    var result = new TaskServiceImpl(this.taskRepository).findAll();
    assertEquals(tasks, result);
    Mockito.verify(this.taskRepository, Mockito.times(1)).findAll();
  }

}
