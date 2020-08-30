package api.clue.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import api.clue.ClueApplication;
import api.clue.domain.Author;
import api.clue.domain.Paper;
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
public class PwaRepositoryImplDbUnitTest {

  @Autowired
  private PwaRepository pwaRepository;

  @Autowired
  private AuthorRepository authorRepository;

  @Autowired
  private PaperRepository paperRepository;

  private final Long AUTHOR_ID = 1L;
  private final Long PAPER_ID = 1L;

  @Test
  @Order(2)
  public void testFindPapersByAuthorId() {
    Author author = new Author();
    author.setAuthorId(AUTHOR_ID);
    List<Paper> papers = this.pwaRepository.findPapersByAuthorId(author);
    papers.forEach(paper -> System.out.println(paper.getPaperId()));
    assertNotNull(papers);
    assertFalse(papers.isEmpty());
  }

  @Test
  @Order(2)
  public void testFindAuthorsByPaperId() {
    Long paperId = 1L;
    Paper paper = new Paper();
    paper.setPaperId(paperId);
    List<Author> authors = this.pwaRepository.findAuthorsByPaperId(paper);
    assertNotNull(authors);
    assertFalse(authors.isEmpty());
  }

  @Test
  @Order(1)
  public void testInsert() {
    Author author = new Author();
    author.setAuthorId(AUTHOR_ID);
    author.setName("name in pwa repository test");
    Paper paper = new Paper();
    paper.setPaperId(PAPER_ID);
    paper.setTitle("paper title");
    paper.setLabel("SA");
    paper.setLang("english");
    paper.setConference("ACL2019");
    paper.setYear(2019);
    paper.setUrl("https://www.google.com");

    author.setPapers(new ArrayList<>(){{add(paper);}});
    paper.setAuthors(new ArrayList<>(){{add(author);}});
    this.authorRepository.add(author);
    this.paperRepository.add(paper);
    this.pwaRepository.add(author, paper);
  }

}
