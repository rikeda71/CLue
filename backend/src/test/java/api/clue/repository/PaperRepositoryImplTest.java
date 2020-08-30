package api.clue.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import api.clue.domain.Author;
import api.clue.domain.Paper;
import api.clue.domain.PaperSearchProvider;
import api.clue.repository.mapper.PaperMapper;
import api.clue.repository.mapper.PwaMapper;
import java.util.ArrayList;
import java.util.List;
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

  private final Integer OFFSET_VALUE = 0;
  private final Integer LIMIT_VALUE = 10;


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

  @Test
  public void testFind() {
    // setup
    PaperSearchProvider provider = new PaperSearchProvider();
    var label = "SA";
    provider.setLabel(label);
    List<Paper> papers = new ArrayList<>();
    Mockito.doReturn(papers).when(this.paperMapper).findPapers(provider, OFFSET_VALUE, LIMIT_VALUE);
    // when
    var result = new PaperRepositoryImpl(this.paperMapper, this.pwaMapper).find(provider, OFFSET_VALUE, LIMIT_VALUE);
    // then
    assertEquals(result, papers);
    Mockito.verify(this.paperMapper, Mockito.times(1)).findPapers(provider, OFFSET_VALUE, LIMIT_VALUE);
  }

  @Test
  public void testFindByAuthorId() {
    // setup
    Long authorId = 1L;
    Author author = new Author();
    author.setAuthorId(1L);
    List<Paper> papers = new ArrayList<>();
    Mockito.doReturn(papers).when(this.pwaMapper).findPapersByAuthorId(author);
    // when
    var result = new PaperRepositoryImpl(this.paperMapper, this.pwaMapper).findByAuthorId(author);
    // then
    assertEquals(result, papers);
    Mockito.verify(this.pwaMapper, Mockito.times(1)).findPapersByAuthorId(author);
  }

  @Test
  public void testAdd() {
    // setup
    Paper paper = new Paper();
    paper.setTitle("title");
    paper.setYear(2020);
    paper.setUrl("http://www.aaa.com");
    Mockito.doNothing().when(this.paperMapper).insert(paper);
    // when
    new PaperRepositoryImpl(this.paperMapper, this.pwaMapper).add(paper);
    // then
    Mockito.verify(this.paperMapper, Mockito.times(1)).insert(paper);
  }

  @Test
  public void testSet() {
    // setup
    Paper paper = new Paper();
    paper.setPaperId(1L);
    paper.setTitle("title");
    paper.setYear(2020);
    paper.setUrl("http://www.aaa.com");
    Mockito.doNothing().when(this.paperMapper).update(paper);
    // when
    new PaperRepositoryImpl(this.paperMapper, this.pwaMapper).set(paper);
    // then
    Mockito.verify(this.paperMapper, Mockito.times(1)).update(paper);
  }

  @Test
  public void testRemove() {
    // setup
    Long paperId = 1L;
    Mockito.doNothing().when(this.paperMapper).delete(paperId);
    // when
    new PaperRepositoryImpl(this.paperMapper, this.pwaMapper).remove(paperId);
    // then
    Mockito.verify(this.paperMapper, Mockito.times(1)).delete(paperId);
  }

}
