package api.clue.controller;

import api.clue.domain.Paper;
import api.clue.domain.PaperSearchProvider;
import api.clue.service.PaperService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
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
  public List<Paper> find(@AuthenticationPrincipal OidcUser user, PaperSearchProvider provider, @RequestParam(required = false) Integer offset, @RequestParam(required = false) Integer limit) {
    offset = offset != null ? offset : 0;
    limit = limit != null ? limit : 100;

    System.out.println(user);
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
  public void remove(@AuthenticationPrincipal OidcUser user, @PathVariable Long paperId) {
    this.paperService.remove(paperId);
  }

}
