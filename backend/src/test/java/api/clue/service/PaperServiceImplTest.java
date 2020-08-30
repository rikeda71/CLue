package api.clue.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import api.clue.domain.Author;
import api.clue.domain.Paper;
import api.clue.domain.PaperSearchProvider;
import api.clue.repository.AuthorRepository;
import api.clue.repository.PaperRepository;
import api.clue.repository.PwaRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class PaperServiceImplTest {

  @Mock
  private PaperRepository paperRepository;

  @Mock
  private AuthorRepository authorRepository;

  @Mock
  private PwaRepository pwaRepository;

  private final Integer OFFSET_VALUE = 0;
  private final Integer LIMIT_VALUE = 10;

  @BeforeEach
  public void before() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testFind() {
    // setup
    List<Paper> papers = new ArrayList<>();
    PaperSearchProvider provider = new PaperSearchProvider();
    Mockito.doReturn(papers).when(this.paperRepository).find(provider, OFFSET_VALUE, LIMIT_VALUE);
    // when
    var result = new PaperServiceImpl(this.paperRepository, this.authorRepository, this.pwaRepository).find(provider, OFFSET_VALUE, LIMIT_VALUE);
    // then
    assertEquals(result, papers);
    Mockito.verify(this.paperRepository, Mockito.times(1)).find(provider,  OFFSET_VALUE, LIMIT_VALUE);
  }

  @Test
  public void testFindById() {
    // setup
    Paper paper = new Paper();
    Long paperId = 1L;
    paper.setPaperId(1L);
    Mockito.doReturn(paper).when(this.paperRepository).findById(paperId);
    // when
    var result = new PaperServiceImpl(this.paperRepository, this.authorRepository, this.pwaRepository).findById(paperId);
    // then
    assertEquals(result, paper);
    Mockito.verify(this.paperRepository, Mockito.times(1)).findById(paperId);
  }

  @Test
  public void testFindByAuthorId() {
    // setup
    List<Paper> papers = new ArrayList<>();
    Long authorId = 1L;
    Author author = new Author();
    author.setAuthorId(authorId);
    Mockito.doReturn(papers).when(this.paperRepository).findByAuthorId(author);
    // when
    var result = new PaperServiceImpl(this.paperRepository, this.authorRepository, this.pwaRepository).findByAuthorId(authorId);
    // then
    assertEquals(result, papers);
    Mockito.verify(this.paperRepository, Mockito.times(1)).findByAuthorId(author);
  }

  @Test
  public void testAdd() {
    // setup
    Paper paper = new Paper();
    Author author = new Author();
    List<Author> authors = new ArrayList<>();
    authors.add(author);
    paper.setAuthors(authors);
    Mockito.doNothing().when(this.paperRepository).add(paper);
    Mockito.doNothing().when(this.authorRepository).add(author);
    Mockito.doNothing().when(this.pwaRepository).add(author, paper);
    // when
    var target = new PaperServiceImpl(this.paperRepository, this.authorRepository, this.pwaRepository);
    target.add(paper);
    // then
    Mockito.verify(this.paperRepository, Mockito.times(1)).add(paper);
    Mockito.verify(this.authorRepository).add(author);
    Mockito.verify(this.pwaRepository).add(author, paper);
  }

  @Test
  public void testSet() {
    // setup
    Paper paper = new Paper();
    Mockito.doNothing().when(this.paperRepository).set(paper);
    // when
    new PaperServiceImpl(this.paperRepository, this.authorRepository, this.pwaRepository).set(paper);
    // then
    Mockito.verify(this.paperRepository, Mockito.times(1)).set(paper);
  }

  @Test
  public void testRemove() {
    // setup
    Long paperId = 1L;
    Mockito.doNothing().when(this.paperRepository).remove(paperId);
    // when
    new PaperServiceImpl(this.paperRepository, this.authorRepository, this.pwaRepository).remove(paperId);
    // then
    Mockito.verify(this.paperRepository, Mockito.times(1)).remove(paperId);
  }

}
