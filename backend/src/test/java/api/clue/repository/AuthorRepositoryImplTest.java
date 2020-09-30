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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.web.FilterChainProxy;

@SpringBootTest
public class AuthorRepositoryImplTest {

  @Mock
  private AuthorMapper authorMapper;

  @Mock
  private PwaMapper pwaMapper;

  @Autowired
  FilterChainProxy springSecurityFilterChain;

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
    Mockito.doReturn(author).when(this.authorMapper).findById(authorId);
    // when
    var result = new AuthorRepositoryImpl(this.authorMapper, this.pwaMapper).findById(authorId);
    // then
    assertEquals(result, author);
    Mockito.verify(this.authorMapper, Mockito.times(1)).findById(authorId);
  }

  @Test
  public void testFindByName() {
    // setup
    var name = "name";
    Author author = new Author();
    author.setName(name);
    List<Author> authors = new ArrayList<>();
    authors.add(author);
    Mockito.doReturn(authors).when(this.authorMapper).findByAuthorName(name);
    // when
    var result = new AuthorRepositoryImpl(this.authorMapper, this.pwaMapper).findByName(name);
    // then
    assertEquals(result, authors);
    Mockito.verify(this.authorMapper, Mockito.times(1)).findByAuthorName(name);
  }

  @Test
  public void testFindByPaperId() {
    // setup
    List<Author> authors = new ArrayList();
    Long paperId = 1L;
    Paper paper = new Paper();
    paper.setPaperId(paperId);
    Mockito.doReturn(authors).when(this.pwaMapper).findAuthorsByPaperId(paper);
    // when
    var result = new AuthorRepositoryImpl(this.authorMapper, this.pwaMapper).findByPaperId(paper);
    // then
    assertEquals(result, authors);
    Mockito.verify(this.pwaMapper, Mockito.times(1)).findAuthorsByPaperId(paper);
  }

  @Test
  public void testAdd() {
    // setup
    Author author = new Author();
    Mockito.doNothing().when(this.authorMapper).insert(author);
    // when
    new AuthorRepositoryImpl(this.authorMapper, this.pwaMapper).add(author);
    // then
    Mockito.verify(this.authorMapper, Mockito.times(1)).insert(author);
  }

  @Test
  public void testRemove() {
    // setup
    Long authorId = 1L;
    Mockito.doNothing().when(this.authorMapper).delete(authorId);
    // when
    new AuthorRepositoryImpl(this.authorMapper, this.pwaMapper).remove(authorId);
    // then
    Mockito.verify(this.authorMapper, Mockito.times(1)).delete(authorId);
  }

}
