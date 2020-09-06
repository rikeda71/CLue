package api.clue.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import api.clue.domain.Conference;
import api.clue.repository.mapper.ConferenceMapper;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class ConferenceRepositoryImplTest {

  @Mock
  private ConferenceMapper conferenceMapper;

  @BeforeEach
  public void before() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testFindAll() {
    List<Conference> conferences = new ArrayList<>();
    Mockito.doReturn(conferences).when(this.conferenceMapper).findAll();
    var result = new ConferenceRepositoryImpl(this.conferenceMapper).findAll();
    assertEquals(conferences, result);
    Mockito.verify(this.conferenceMapper, Mockito.times(1)).findAll();
  }

}