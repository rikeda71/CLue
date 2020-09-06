package api.clue.service;

import api.clue.domain.Year;
import api.clue.repository.YearRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class YearServiceImpl implements YearService {

  private final YearRepository yearRepository;

  public YearServiceImpl(YearRepository yearRepository) {
    this.yearRepository = yearRepository;
  }

  @Override
  public List<Year> findAll() {
    return this.yearRepository.findAll();
  }
}
