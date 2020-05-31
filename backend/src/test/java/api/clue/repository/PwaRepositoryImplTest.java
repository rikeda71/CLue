package api.clue.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import api.clue.domain.Author;
import api.clue.domain.Paper;
import api.clue.repository.mapper.AuthorMapper;
import api.clue.repository.mapper.PwaMapper;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class PwaRepositoryImplTest {

  @Mock
  private PwaMapper pwaMapper;

  @BeforeEach
  public void before() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testFindPapersByAuthorId() {
    // setup
    List<Paper> papers = new ArrayList<>();
    Long authorId = 1L;
    Author author = new Author();
    author.setAuthorId(authorId);
    Mockito.doReturn(papers).when(this.pwaMapper).findPapersByAuthorId(author);
    // when
    var result = new PwaRepositoryImpl(this.pwaMapper).findPapersByAuthorId(author);
    // then
    assertEquals(result, papers);
    Mockito.verify(this.pwaMapper, Mockito.times(1)).findPapersByAuthorId(author);
  }

  @Test
  public void testFindAuthorsByPaperId() {
    // setup
    List<Author> authors = new ArrayList<>();
    Long paperId = 1L;
    Paper paper = new Paper();
    paper.setPaperId(paperId);
    Mockito.doReturn(authors).when(this.pwaMapper).findAuthorsByPaperId(paper);
    // when
    var result = new PwaRepositoryImpl(this.pwaMapper).findAuthorsByPaperId(paper);
    // then
    assertEquals(result, authors);
    Mockito.verify(this.pwaMapper, Mockito.times(1)).findAuthorsByPaperId(paper);
  }

  @Test
  public void testAdd() {
    // setup
    Author author = new Author();
    Paper paper = new Paper();
    Mockito.doNothing().when(this.pwaMapper).insert(author, paper);
    // when
    new PwaRepositoryImpl(this.pwaMapper).add(author, paper);
    // then
    Mockito.verify(this.pwaMapper, Mockito.times(1)).insert(author, paper);
  }

}
