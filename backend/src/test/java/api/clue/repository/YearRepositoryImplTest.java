package api.clue.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import api.clue.domain.Year;
import api.clue.repository.mapper.YearMapper;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class YearRepositoryImplTest {

  @Mock
  private YearMapper yearMapper;

  @BeforeEach
  public void before() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testFindAll() {
    List<Year> years = new ArrayList<>();
    Mockito.doReturn(years).when(this.yearMapper).findAll();
    var result = new YearRepositoryImpl(this.yearMapper).findAll();
    assertEquals(years, result);
    Mockito.verify(this.yearMapper, Mockito.times(1)).findAll();
  }

}
