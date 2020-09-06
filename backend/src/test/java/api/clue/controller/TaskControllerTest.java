package api.clue.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import api.clue.domain.Task;
import api.clue.service.TaskService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class TaskControllerTest implements ControllerTestInterface {

  @Mock
  private TaskService taskService;

  @InjectMocks
  private TaskController target;

  private MockMvc mockMvc;

  @BeforeEach
  public void before() {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(this.target).build();
  }

  @Test
  public void testFindAll() throws Exception {
    List<Task> tasks = new ArrayList<>();
    Mockito.doReturn(tasks).when(this.taskService).findAll();

    var result = this.mockMvc.perform(MockMvcRequestBuilders
        .get("/api/v1/task"))
        .andExpect(status().isOk())
        .andReturn();

    assertEquals(entity2String(tasks), result.getResponse().getContentAsString());
    Mockito.verify(this.taskService, Mockito.times(1)).findAll();
  }

}
