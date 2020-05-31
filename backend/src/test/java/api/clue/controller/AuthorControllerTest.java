package api.clue.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import api.clue.domain.Author;
import api.clue.domain.Paper;
import api.clue.service.AuthorService;
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

public class AuthorControllerTest implements ControllerTestInterface {

  @Mock
  private AuthorService authorService;

  @InjectMocks
  private AuthorController target;

  private MockMvc mockMvc;

  @BeforeEach
  public void before() {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(this.target).build();
  }

  @Test
  public void testFindById() throws Exception {
    // setup
    Long authorId = 1L;
    Author author = new Author();
    author.setAuthorId(authorId);
    Mockito.doReturn(author).when(this.authorService).findById(authorId);
    // when
    var result = this.mockMvc.perform(MockMvcRequestBuilders
        .get("/api/v1/authors/" + authorId))
        .andExpect(status().isOk())
        .andReturn();
    // then
    assertEquals(result.getResponse().getContentAsString(), entity2String(author));
    Mockito.verify(this.authorService, Mockito.times(1)).findById(authorId);
  }

  @Test
  public void testFindByName() throws Exception {
    // setup
    var name = "name";
    Author author = new Author(){{setName(name);}};
    List<Author> authors = new ArrayList<>(){{add(author);}};
    Mockito.doReturn(authors).when(this.authorService).findByName(name);
    // when
    var result = this.mockMvc.perform(MockMvcRequestBuilders
        .get("/api/v1/authors")
        .param("name", name))
        .andExpect(status().isOk())
        .andReturn();
    // then
    assertEquals(result.getResponse().getContentAsString(), entity2String(authors));
    Mockito.verify(this.authorService, Mockito.times(1)).findByName(name);
  }

  @Test
  public void testFindByPaperId() throws Exception {
    // setup
    Long paperId = 1L;
    Paper paper = new Paper(){{setPaperId(paperId);}};
    List<Paper> papers = new ArrayList<>(){{add(paper);}};
    Author author = new Author();
    List<Author> authors = new ArrayList<>(){{add(author);}};
    Mockito.doReturn(authors).when(this.authorService).findByPaperId(paperId);
    // when
    var result = this.mockMvc.perform(MockMvcRequestBuilders
        .get("/api/v1/authors/paper/" + paperId))
        .andExpect(status().isOk())
        .andReturn();
    // then
    assertEquals(result.getResponse().getContentAsString(), entity2String(authors));
    Mockito.verify(this.authorService, Mockito.times(1)).findByPaperId(paperId);
  }

  @Test
  public void testAdd() throws Exception {
    // setup
    Author author = new Author();
    Mockito.doNothing().when(this.authorService).add(ArgumentMatchers.any(Author.class));
    // when
    mockMvc.perform(MockMvcRequestBuilders
        .post("/api/v1/authors")
        .contentType(MediaType.APPLICATION_JSON)
        .content(entity2String(author)))
        .andExpect(status().isOk());
    // then
    Mockito.verify(this.authorService, Mockito.times(1)).add(ArgumentMatchers.any(Author.class));
  }

  @Test
  public void testRemove() throws Exception {
    // setup
    Long authorId = 1L;
    Mockito.doNothing().when(this.authorService).remove(authorId);
    // when
    mockMvc.perform(MockMvcRequestBuilders
        .delete("/api/v1/authors/" + authorId))
        .andExpect(status().isOk());
    // then
    Mockito.verify(this.authorService, Mockito.times(1)).remove(authorId);
  }

}
