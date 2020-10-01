package api.clue.controller;

import api.clue.domain.Author;
import api.clue.domain.User;
import api.clue.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {

  private final AuthorService authorService;

  public AuthorController(AuthorService authorService) {
    this.authorService = authorService;
  }

  @GetMapping(value = "/{authorId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Author findById(@PathVariable("authorId") Long authorId) {
    return this.authorService.findById(authorId);
  }

  @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Author> findByName(@RequestParam(value = "name") String name) {
    return this.authorService.findByName(name);
  }

  @GetMapping(value = "/paper/{paperId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Author> findByPaperId(@PathVariable(value = "paperId") Long paperId) {
    return this.authorService.findByPaperId(paperId);
  }

  @PostMapping(value = "")
  public ResponseEntity<Void> add(@AuthenticationPrincipal User user, @RequestBody Author author) {
    if (user != null) {
      this.authorService.add(author);
      return new ResponseEntity<>(HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.FORBIDDEN);
  }

  @DeleteMapping("/{authorId}")
  public ResponseEntity<Void> remove(@AuthenticationPrincipal User user, @PathVariable("authorId") Long authorId) {
    if (user != null) {
      this.authorService.remove(authorId);
      return new ResponseEntity<>(HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.FORBIDDEN);
  }

}
