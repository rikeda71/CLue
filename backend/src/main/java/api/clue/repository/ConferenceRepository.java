package api.clue.repository;

import api.clue.domain.Conference;
import java.util.List;

public interface ConferenceRepository {

  List<Conference> findAll();

}
