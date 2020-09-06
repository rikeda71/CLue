package api.clue.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import api.clue.domain.Year;
import api.clue.service.YearService;
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

public class YearControllerTest implements ControllerTestInterface {

  @Mock
  private YearService yearService;

  @InjectMocks
  private YearController target;

  private MockMvc mockMvc;

  @BeforeEach
  public void before() {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(this.target).build();
  }

  @Test
  public void testFindAll() throws Exception {
    List<Year> years = new ArrayList<>();
    Mockito.doReturn(years).when(this.yearService).findAll();

    var result = this.mockMvc.perform(MockMvcRequestBuilders
        .get("/api/v1/year"))
        .andExpect(status().isOk())
        .andReturn();

    assertEquals(entity2String(years), result.getResponse().getContentAsString());
    Mockito.verify(this.yearService, Mockito.times(1)).findAll();
  }

}
