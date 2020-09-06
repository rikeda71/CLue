package api.clue.controller;

import api.clue.domain.Year;
import api.clue.service.YearService;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/year")
public class YearController {

  private final YearService yearService;

  public YearController(YearService yearService) {
    this.yearService = yearService;
  }

  @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Year> findAll() {
    return this.yearService.findAll();
  }

}
