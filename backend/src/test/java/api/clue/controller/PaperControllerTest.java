package api.clue.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import api.clue.domain.Author;
import api.clue.domain.Paper;
import api.clue.domain.PaperSearchProvider;
import api.clue.service.PaperService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class PaperControllerTest implements ControllerTestInterface {

  @Mock
  private PaperService paperService;

  @InjectMocks
  private PaperController target;

  private MockMvc mockMvc;

  @BeforeEach
  public void before() {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(this.target).build();
  }

  @Test
  public void testFindWithNullParameter() throws Exception {
    // setup
    List<Paper> papers = new ArrayList<>();
    Mockito.doReturn(papers).when(this.paperService).find(ArgumentMatchers.any(PaperSearchProvider.class));
    // when
    var result = mockMvc.perform(MockMvcRequestBuilders
        .get("/api/v1/papers"))
        .andExpect(status().isOk())
        .andReturn();
    // then
    assertEquals(result.getResponse().getContentAsString(), entity2String(papers));
    Mockito.verify(this.paperService, Mockito.times(1)).find(ArgumentMatchers.any(PaperSearchProvider.class));
  }

  @Test
  public void testFindWithSearchProvider() throws Exception {
    // setup
    List<Paper> papers = new ArrayList<>();
    PaperSearchProvider provider = new PaperSearchProvider();
    var label = "SA";
    provider.setLabel(label);
    Mockito.doReturn(papers).when(this.paperService).find(ArgumentMatchers.any(PaperSearchProvider.class));
    // when
    var result = mockMvc.perform(MockMvcRequestBuilders
        .get("/api/v1/papers")
        .param("label", label))
        .andExpect(status().isOk())
        .andReturn();
    // then
    assertEquals(result.getResponse().getContentAsString(), entity2String(papers));
    Mockito.verify(this.paperService, Mockito.times(1)).find(ArgumentMatchers.any(PaperSearchProvider.class));
  }

  @Test
  public void testFindById() throws Exception {
    // setup
    Paper paper = new Paper();
    Long paperId = 1L;
    paper.setPaperId(paperId);
    Mockito.doReturn(paper).when(this.paperService).findById(paperId);
    // when
    var result = mockMvc.perform(MockMvcRequestBuilders
        .get("/api/v1/papers/" + paperId))
        .andExpect(status().isOk())
        .andReturn();
    // then
    assertEquals(result.getResponse().getContentAsString(), entity2String(paper));
    Mockito.verify(this.paperService, Mockito.times(1)).findById(paperId);
  }

  @Test
  public void testFindByAuthorId() throws Exception {
    // setup
    Long authorId = 1L;
    Author author = new Author();
    author.setAuthorId(authorId);
    List<Author> authors = new ArrayList<>(){{add(author);}};
    Paper paper = new Paper(){{setAuthors(authors);}};
    List<Paper> papers = new ArrayList<>(){{add(paper);}};
    Mockito.doReturn(papers).when(this.paperService).findByAuthorId(authorId);
    // when
    var result = mockMvc.perform(MockMvcRequestBuilders
        .get("/api/v1/papers/author/" + authorId))
        .andExpect(status().isOk())
        .andReturn();
    // then
    assertEquals(result.getResponse().getContentAsString(), entity2String(papers));
    Mockito.verify(this.paperService, Mockito.times(1)).findByAuthorId(authorId);
  }

  @Test
  public void testAdd() throws Exception {
    // setup
    Paper paper = new Paper();
    Mockito.doNothing().when(this.paperService).add(ArgumentMatchers.any(Paper.class));
    // when
    mockMvc.perform(MockMvcRequestBuilders
        .post("/api/v1/papers")
        .contentType(MediaType.APPLICATION_JSON)
        .content(entity2String(paper)))
        .andExpect(status().isOk());
    // then
    Mockito.verify(this.paperService, Mockito.times(1)).add(ArgumentMatchers.any(Paper.class));
  }

  @Test
  public void testSet() throws Exception {
    // setup
    Long paperId = 1L;
    Paper paper = new Paper();
    paper.setPaperId(paperId);
    Mockito.doNothing().when(this.paperService).set(ArgumentMatchers.any(Paper.class));
    // when
    mockMvc.perform(MockMvcRequestBuilders
        .patch("/api/v1/papers/" + paperId)
        .contentType(MediaType.APPLICATION_JSON)
        .content(entity2String(paper)))
        .andExpect(status().isOk());
    // then
    Mockito.verify(this.paperService, Mockito.times(1)).set(ArgumentMatchers.any(Paper.class));
  }

  @Test
  public void testRemove() throws Exception {
    // setup
    Long paperId = 1L;
    Mockito.doNothing().when(this.paperService).remove(paperId);
    // when
    mockMvc.perform(MockMvcRequestBuilders
        .delete("/api/v1/papers/" + paperId))
        .andExpect(status().isOk());
    // then
    Mockito.verify(this.paperService, Mockito.times(1)).remove(paperId);
  }

}
