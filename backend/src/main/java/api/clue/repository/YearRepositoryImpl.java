package api.clue.repository;

import api.clue.domain.Year;
import api.clue.repository.mapper.YearMapper;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class YearRepositoryImpl implements YearRepository {

  private final YearMapper yearMapper;

  public YearRepositoryImpl(YearMapper yearMapper) {
    this.yearMapper = yearMapper;
  }

  @Override
  public List<Year> findAll() {
    return this.yearMapper.findAll();
  }
}
