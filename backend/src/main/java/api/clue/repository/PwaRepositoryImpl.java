package api.clue.repository;

import api.clue.domain.Author;
import api.clue.domain.Paper;
import api.clue.repository.mapper.PwaMapper;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class PwaRepositoryImpl implements PwaRepository {

  private final PwaMapper pwaMapper;

  public PwaRepositoryImpl(PwaMapper pwaMapper) {
    this.pwaMapper = pwaMapper;
  }

  @Override
  public List<Author> findAuthorsByPaperId(Paper paper) {
    return this.pwaMapper.findAuthorsByPaperId(paper);
  }

  @Override
  public List<Paper> findPapersByAuthorId(Author author) {
    return this.pwaMapper.findPapersByAuthorId(author);
  }

  @Override
  public void add(Author author, Paper paper) {
    this.pwaMapper.insert(author, paper);
  }
}
