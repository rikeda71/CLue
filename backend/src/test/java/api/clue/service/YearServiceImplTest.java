package api.clue.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import api.clue.domain.Year;
import api.clue.repository.YearRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class YearServiceImplTest {

  @Mock
  private YearRepository yearRepository;

  @BeforeEach
  public void before() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testFindAll() {
    List<Year> years = new ArrayList<>();
    Mockito.doReturn(years).when(this.yearRepository).findAll();

    var result = new YearServiceImpl(this.yearRepository).findAll();
    assertEquals(years, result);
    Mockito.verify(this.yearRepository, Mockito.times(1)).findAll();
  }

}
