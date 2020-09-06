package api.clue.controller;

import api.clue.domain.Conference;
import api.clue.service.ConferenceService;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/conference")
public class ConferenceController {

  private final ConferenceService conferenceService;

  public ConferenceController(ConferenceService conferenceService) {
    this.conferenceService = conferenceService;
  }

  @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Conference> findAll() {
    return this.conferenceService.findAll();
  }

}
