package api.clue.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import api.clue.domain.Paper;
import api.clue.repository.mapper.PaperMapper;
import api.clue.repository.mapper.PwaMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class PaperRepositoryImplTest {

  @Mock
  private PaperMapper paperMapper;

  @Mock
  private PwaMapper pwaMapper;


  @BeforeEach
  public void before() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testFindById() {
    // setup
    Long paperId = 1L;
    var paper = new Paper();
    Mockito.doReturn(paper).when(this.paperMapper).findById(paperId);
    // when
    var result = new PaperRepositoryImpl(this.paperMapper, this.pwaMapper).findById(paperId);
    // then
    assertEquals(result, paper);
    Mockito.verify(this.paperMapper, Mockito.times(1)).findById(paperId);
  }

}
