package api.clue.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import api.clue.domain.Conference;
import api.clue.service.ConferenceService;
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

public class ConferenceControllerTest implements ControllerTestInterface {

  @Mock
  private ConferenceService conferenceService;

  @InjectMocks
  private ConferenceController target;

  private MockMvc mockMvc;

  @BeforeEach
  public void before() {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(this.target).build();
  }

  @Test
  public void testFindAll() throws Exception {
    List<Conference> conferences = new ArrayList<>();
    Mockito.doReturn(conferences).when(this.conferenceService).findAll();

    var result = this.mockMvc.perform(MockMvcRequestBuilders
        .get("/api/v1/conference"))
        .andExpect(status().isOk())
        .andReturn();

    assertEquals(entity2String(conferences), result.getResponse().getContentAsString());
    Mockito.verify(this.conferenceService, Mockito.times(1)).findAll();
  }

}
