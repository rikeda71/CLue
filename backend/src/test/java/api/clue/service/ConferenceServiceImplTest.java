package api.clue.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import api.clue.domain.Conference;
import api.clue.repository.ConferenceRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class ConferenceServiceImplTest {

  @Mock
  private ConferenceRepository conferenceRepository;

  @BeforeEach
  public void before() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testFindAll() {
    List<Conference> conferences = new ArrayList<>();
    Mockito.doReturn(conferences).when(this.conferenceRepository).findAll();

    var result = new ConferenceServiceImpl(this.conferenceRepository).findAll();
    assertEquals(conferences, result);
    Mockito.verify(this.conferenceRepository, Mockito.times(1)).findAll();
  }

}
