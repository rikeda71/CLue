package api.clue.controller;

import api.clue.ClueServiceException;
import api.clue.domain.Paper;
import api.clue.domain.PaperSearchProvider;
import api.clue.domain.User;
import api.clue.service.PaperService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/papers")
public class PaperController {

  private final PaperService paperService;

  public PaperController(PaperService paperService) {
    this.paperService = paperService;
  }

  @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Paper> find(PaperSearchProvider provider, @RequestParam(required = false) Integer offset, @RequestParam(required = false) Integer limit) {
    offset = offset != null ? offset : 0;
    limit = limit != null ? limit : 100;

    return this.paperService.find(provider, offset, limit);
  }

  @GetMapping(value = "/{paperId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Paper findById(@PathVariable("paperId") Long paperId) {
    return this.paperService.findById(paperId);
  }

  @GetMapping(value = "/author/{authorId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Paper> findByAuthorId(@PathVariable("authorId") Long authorId) {
    return this.paperService.findByAuthorId(authorId);
  }

  @PostMapping(value = "")
  public ResponseEntity<Void> add(@AuthenticationPrincipal User user, @RequestBody Paper paper)
      throws ClueServiceException {
    if (user != null) {
      this.paperService.add(paper);
      return new ResponseEntity<>(HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.FORBIDDEN);
  }

  @PatchMapping(value = "/{paperId}")
  public ResponseEntity<Void> set(@AuthenticationPrincipal User user, @PathVariable Long paperId, @RequestBody Paper paper) {
    if (user != null) {
      if (!paperId.equals(paper.getPaperId())) {
        paper.setPaperId(paperId);
      }
      this.paperService.set(paper);
      return new ResponseEntity<>(HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.FORBIDDEN);
  }

  @DeleteMapping("/{paperId}")
  public ResponseEntity<Void> remove(@AuthenticationPrincipal User user, @PathVariable Long paperId) {
    if (user != null) {
      this.paperService.remove(paperId);
      return new ResponseEntity<>(HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.FORBIDDEN);
  }

}
