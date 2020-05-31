package api.clue.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import api.clue.domain.Author;
import api.clue.domain.Paper;
import api.clue.repository.AuthorRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class AuthorSerivceImplTest {

  @Mock
  private AuthorRepository authorRepository;

  @BeforeEach
  public void before() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testFindById() {
    // setup
    Long authorId = 1L;
    Author author = new Author();
    author.setAuthorId(authorId);
    Mockito.doReturn(author).when(this.authorRepository).findById(authorId);
    // when
    var result = new AuthorServiceImpl(this.authorRepository).findById(authorId);
    // then
    assertEquals(result, author);
    Mockito.verify(this.authorRepository, Mockito.times(1)).findById(authorId);
  }

  @Test
  public void testFindByName() {
    // setup
    var name = "name";
    Author author = new Author();
    author.setName(name);
    List<Author> authors = new ArrayList<>();
    authors.add(author);
    Mockito.doReturn(authors).when(this.authorRepository).findByName(name);
    // when
    var result = new AuthorServiceImpl(this.authorRepository).findByName(name);
    // then
    assertEquals(result, authors);
    Mockito.verify(this.authorRepository, Mockito.times(1)).findByName(name);
  }

  @Test
  public void testFindByPaperId() {
    // setup
    Long paperId = 1L;
    Paper paper = new Paper();
    paper.setPaperId(paperId);
    List<Author> authors = new ArrayList<>();
    Mockito.doReturn(authors).when(this.authorRepository).findByPaperId(paper);
    // when
    var result = new AuthorServiceImpl(this.authorRepository).findByPaperId(paperId);
    // then
    assertEquals(result, authors);
    Mockito.verify(this.authorRepository, Mockito.times(1)).findByPaperId(paper);
  }

  @Test
  public void testAdd() {
    // setup
    Author author = new Author();
    Mockito.doNothing().when(this.authorRepository).add(author);
    // when
    new AuthorServiceImpl(this.authorRepository).add(author);
    // then
    Mockito.verify(this.authorRepository, Mockito.times(1)).add(author);
  }

  @Test
  public void testRemove() {
    // setup
    Long authorId = 1L;
    Mockito.doNothing().when(this.authorRepository).remove(authorId);
    // when
    new AuthorServiceImpl(this.authorRepository).remove(authorId);
    // then
    Mockito.verify(this.authorRepository, Mockito.times(1)).remove(authorId);
  }

}
