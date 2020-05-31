package api.clue.repository;

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

public class PwaRepositoryImplDbUnitTest {
  @SpringBootTest(classes = ClueApplication.class)
  @TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DataSetExecutorListener.class})
  @Nested
  public class FindDbTest {

    @Autowired
    private PwaRepository pwaRepository;

    @Test
    public void testFindPapersByAuthorId() {
      Long authorId = 1L;
      Author author = new Author();
      author.setAuthorId(authorId);
      List<Paper> papers = this.pwaRepository.findPapersByAuthorId(author);
      papers.forEach(paper -> System.out.println(paper.getPaperId()));
      assertNotNull(papers);
      assertFalse(papers.isEmpty());
    }

    @Test
    public void testFindAuthorsByPaperId() {
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
    private PwaRepository pwaRepository;

    @Test
    public void testInsert() {
      Author author = new Author();
      author.setAuthorId(1L);
      Paper paper = new Paper();
      paper.setPaperId(1L);
      this.pwaRepository.add(author, paper);
    }

  }


}
