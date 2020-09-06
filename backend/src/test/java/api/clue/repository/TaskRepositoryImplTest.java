package api.clue.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import api.clue.domain.Task;
import api.clue.repository.mapper.TaskMapper;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class TaskRepositoryImplTest {

  @Mock
  private TaskMapper taskMapper;

  @BeforeEach
  public void before() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testFindAll() {
    List<Task> tasks = new ArrayList<>();
    Mockito.doReturn(tasks).when(this.taskMapper).findAll();
    var result = new TaskRepositoryImpl(this.taskMapper).findAll();
    assertEquals(tasks, result);
    Mockito.verify(this.taskMapper, Mockito.times(1)).findAll();
  }

}
