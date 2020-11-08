package api.clue.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import api.clue.ClueApplication;
import api.clue.domain.Author;
import api.clue.domain.Paper;
import api.clue.domain.PaperSearchProvider;
import api.clue.repository.util.DataSetExecutorListener;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@SpringBootTest(classes = ClueApplication.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DataSetExecutorListener.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PaperRepositoryImplDbUnitTest {

  private final Integer OFFSET_VALUE = 0;
  private final Integer LIMIT_VALUE = 10;
  private final Long AUTHOR_ID = 2000L;
  private final Long PAPER_ID = 2000L;

  @Autowired
  private PaperRepository paperRepository;

  @Test
  @Order(3)
  public void testFindById() {
    var papers = this.paperRepository.find(new PaperSearchProvider(), 0, 100);
    Long paperId = papers.get(0).getPaperId();
    Paper paper = this.paperRepository.findById(paperId);
    System.out.println();
    assertNotNull(paper);
  }

  @Test
  @Order(3)
  public void testFindByAuthorIdWithPaperRepository() {
    Author author = new Author();
    author.setAuthorId(AUTHOR_ID);
    List<Paper> papers = this.paperRepository.findByAuthorId(author);
    assertNotNull(papers);
  }

  @Test
  @Order(3)
  public void testFindBySearchProvider() {
    PaperSearchProvider provider = new PaperSearchProvider();
    provider.setYear(2019);
    provider.setLabel("SA");
    List<Paper> papers = this.paperRepository.find(provider, OFFSET_VALUE, LIMIT_VALUE);
    assertNotNull(papers);
    assertFalse(papers.isEmpty());
  }

  @Test
  @Order(1)
  public void testInsert() {
    int beforePaperNum = this.paperRepository.find(new PaperSearchProvider(), 0, 100).size();
    Paper paper = new Paper();
    var year = 2019;
    var title = "title";
    var url = "https://www.google.co.jp/";
    var label = "SA";
    var task = "Sentiment Analysis";
    paper.setYear(year);
    paper.setTitle(title);
    paper.setUrl(url);
    paper.setLabel(label);
    paper.setTask(task);

    Author author = new Author();
    author.setName("name");
    paper.setAuthors(new ArrayList<>(){{add(author);}});

    this.paperRepository.add(paper);

    int afterPaperNum = this.paperRepository.find(new PaperSearchProvider(), 0, 100).size();
    assertEquals(beforePaperNum + 1, afterPaperNum);
  }

  @Test
  @Order(2)
  public void testUpdate() {
    var title = "updatedtitle";
    Paper paper = this.paperRepository.findById(PAPER_ID);
    paper.setTitle(title);

    this.paperRepository.set(paper);

    Paper result = this.paperRepository.findById(PAPER_ID);
    assertEquals(result.getTitle(), title);
  }

  @Test
  @Order(4)
  public void testDelete() {
    int beforePaperNum = this.paperRepository.find(new PaperSearchProvider(), OFFSET_VALUE, LIMIT_VALUE).size();

    this.paperRepository.remove(PAPER_ID);

    int afterPaperNum = this.paperRepository.find(new PaperSearchProvider(), OFFSET_VALUE, LIMIT_VALUE).size();
    assertEquals(beforePaperNum - 1, afterPaperNum);
  }
}
