package api.clue.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import api.clue.ClueApplication;
import api.clue.domain.Author;
import api.clue.domain.Paper;
import api.clue.repository.util.DataSetExecutorListener;
import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

public class AuthorRepositoryImplDbUnitTest {

  @SpringBootTest(classes = ClueApplication.class)
  @TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DataSetExecutorListener.class})
  @Nested public class FindDbTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PwaRepository pwaRepository;

    @Test
    public void testFindById() {
      Long authorId = 1L;
      Author author = this.authorRepository.findById(authorId);
      assertNotNull(author);
    }

    @Test
    public void testFindByName() {
      var authorName = "松本";
      List<Author> authors = this.authorRepository.findByName(authorName);
      assertNotNull(authors);
      assertFalse(authors.isEmpty());
    }

    @Test
    public void testFindByPaperId() {
      Long paperId = 1L;
      Paper paper = new Paper();
      paper.setPaperId(paperId);
      List<Author> authors = this.authorRepository.findByPaperId(paper);
      assertNotNull(authors);
      assertFalse(authors.isEmpty());
    }

    @Test
    public void testFindByPaperIdWithPwaRepository() {
      Long paperId = 1L;
      Paper paper = new Paper();
      paper.setPaperId(paperId);
      List<Author> authors = this.pwaRepository.findAuthorsByPaperId(paper);
      assertNotNull(authors);
      assertFalse(authors.isEmpty());
    }

  }

  @SpringBootTest(classes = ClueApplication.class)
  @TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DataSetExecutorListener.class})
  @Nested public class InsertDbTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void testInsert() {
      Author author = new Author();
      var name = "name";
      author.setName(name);

      this.authorRepository.add(author);

      Author result = this.authorRepository.findByName(name).get(0);
      assertEquals(result.getName(), author.getName());
    }
  }

  @SpringBootTest(classes = ClueApplication.class)
  @TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DataSetExecutorListener.class})
  @Nested public class DeleteDbTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void testDelete() {
      var authorId = 10L;
      Author author = this.authorRepository.findById(authorId);
      this.authorRepository.remove(authorId);
      this.authorRepository.add(author);
    }
  }


}
