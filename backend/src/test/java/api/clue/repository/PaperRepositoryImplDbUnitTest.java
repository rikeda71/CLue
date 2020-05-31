package api.clue.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import api.clue.ClueApplication;
import api.clue.domain.Author;
import api.clue.domain.Paper;
import api.clue.domain.PaperSearchProvider;
import api.clue.repository.util.DataSetExecutorListener;
import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

public class PaperRepositoryImplDbUnitTest {

  @SpringBootTest(classes = ClueApplication.class)
  @TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DataSetExecutorListener.class})
  @Nested public class FindDbTest {

    @Autowired
    private PaperRepository paperRepository;

    @Autowired
    private PwaRepository pwaRepository;

    @Test
    public void testFindById() {
      Long paperId = 1L;
      Paper paper = this.paperRepository.findById(paperId);
      assertNotNull(paper);
      assertFalse(paper.getAuthors().isEmpty());
    }

    @Test
    public void testFindByAuthorIdWithPaperRepository() {
      Long authorId = 1L;
      Author author = new Author();
      author.setAuthorId(authorId);
      List<Paper> papers = this.paperRepository.findByAuthorId(author);
      assertNotNull(papers);
      assertFalse(papers.isEmpty());
    }

    @Test
    public void testFindBySearchProvider() {
      PaperSearchProvider provider = new PaperSearchProvider();
      provider.setYear(2019);
      provider.setLabel("SA");
      List<Paper> papers = this.paperRepository.find(provider);
      assertNotNull(papers);
      assertFalse(papers.isEmpty());
    }
  }

  @SpringBootTest(classes = ClueApplication.class)
  @TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DataSetExecutorListener.class})
  @Nested public class InsertDbTest {

    @Autowired
    private PaperRepository paperRepository;

    @Test
    public void testInsert() {
      Paper paper = new Paper();
      var year = 2030;
      var title = "title";
      var url = "https://www.google.co.jp/";
      paper.setYear(year);
      paper.setTitle(title);
      paper.setUrl(url);

      this.paperRepository.add(paper);

      List<Paper> papers = this.paperRepository.find(new PaperSearchProvider());
      Paper result = papers.get(0);
      assertEquals(result.getYear(), year);
      assertEquals(result.getTitle(), title);
      assertEquals(result.getUrl(), url);
    }

  }

  @SpringBootTest(classes = ClueApplication.class)
  @TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DataSetExecutorListener.class})
  @Nested public class UpdateDbTest {

    @Autowired
    private PaperRepository paperRepository;

    @Test
    public void testUpdate() {
      Paper paper = new Paper();
      var paperId = 1L;
      var year = 2020;
      var title = "updatedtitle";
      paper.setPaperId(paperId);
      paper.setYear(year);
      paper.setTitle(title);

      this.paperRepository.set(paper);

      Paper result = this.paperRepository.findById(paperId);
      assertEquals(result.getYear(), year);
      assertEquals(result.getTitle(), title);
    }

  }

  @SpringBootTest(classes = ClueApplication.class)
  @TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DataSetExecutorListener.class})
  @Nested public class DeleteDbTest {

    @Autowired
    private PaperRepository paperRepository;

    @Test
    public void testDelete() {
      Long paperId = 10L;
      int paperNum = this.paperRepository.find(new PaperSearchProvider()).size();

      this.paperRepository.remove(paperId);

      int paperNumAfterDelete = this.paperRepository.find(new PaperSearchProvider()).size();
      assertEquals(paperNum - 1, paperNumAfterDelete);
    }

  }
}
