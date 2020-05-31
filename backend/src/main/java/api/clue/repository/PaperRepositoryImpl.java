package api.clue.repository;

import api.clue.domain.Author;
import api.clue.domain.Paper;
import api.clue.domain.PaperSearchProvider;
import api.clue.repository.mapper.PaperMapper;
import api.clue.repository.mapper.PwaMapper;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class PaperRepositoryImpl implements PaperRepository {

  private final PaperMapper paperMapper;

  private final PwaMapper pwaMapper;

  public PaperRepositoryImpl(PaperMapper paperMapper, PwaMapper pwaMapper) {
    this.paperMapper = paperMapper;
    this.pwaMapper = pwaMapper;
  }

  @Override
  public List<Paper> find(PaperSearchProvider provider) {
    return this.paperMapper.findPapers(provider);
  }

  @Override
  public List<Paper> findByAuthorId(Author author) {
    return this.pwaMapper.findPapersByAuthorId(author);
  }

  @Override
  public Paper findById(Long paperId) {
    return this.paperMapper.findById(paperId);
  }

  @Override
  public void add(Paper paper) {
    this.paperMapper.insert(paper);
  }

  @Override
  public void set(Paper paper) {
    this.paperMapper.update(paper);
  }

  @Override
  public void remove(Long paperId) {
    this.paperMapper.delete(paperId);
  }
}
