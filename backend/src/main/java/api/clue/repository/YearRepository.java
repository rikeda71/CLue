package api.clue.repository;

import api.clue.domain.Year;
import java.util.List;

public interface YearRepository {

  List<Year> findAll();

}
