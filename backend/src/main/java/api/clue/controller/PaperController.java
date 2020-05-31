package api.clue.controller;

import api.clue.domain.Paper;
import api.clue.domain.PaperSearchProvider;
import api.clue.service.PaperService;
import org.springframework.http.MediaType;
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
  public List<Paper> find(PaperSearchProvider provider) {
    return this.paperService.find(provider);
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
  public void add(@RequestBody Paper paper) {
    this.paperService.add(paper);
  }

  @PatchMapping(value = "/{paperId}")
  public void set(@PathVariable Long paperId, @RequestBody Paper paper) {
    if (paperId != paper.getPaperId()) {
      paper.setPaperId(paperId);
    }
    this.paperService.set(paper);
  }

  @DeleteMapping("/{paperId}")
  public void remove(@PathVariable Long paperId) {
    this.paperService.remove(paperId);
  }

}
