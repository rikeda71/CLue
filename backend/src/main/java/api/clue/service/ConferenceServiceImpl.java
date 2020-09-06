package api.clue.service;

import api.clue.domain.Conference;
import api.clue.repository.ConferenceRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ConferenceServiceImpl implements ConferenceService {

  private final ConferenceRepository conferenceRepository;

  public ConferenceServiceImpl(ConferenceRepository conferenceRepository) {
    this.conferenceRepository = conferenceRepository;
  }

  @Override
  public List<Conference> findAll() {
    return this.conferenceRepository.findAll();
  }
}
