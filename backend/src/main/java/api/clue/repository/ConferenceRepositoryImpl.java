package api.clue.repository;

import api.clue.domain.Conference;
import api.clue.repository.mapper.ConferenceMapper;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class ConferenceRepositoryImpl implements ConferenceRepository {

  private final ConferenceMapper conferenceMapper;

  public ConferenceRepositoryImpl(ConferenceMapper conferenceMapper) {
    this.conferenceMapper = conferenceMapper;
  }

  @Override
  public List<Conference> findAll() {
    return this.conferenceMapper.findAll();
  }
}
