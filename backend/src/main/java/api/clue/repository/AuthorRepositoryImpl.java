package api.clue.repository;

import api.clue.domain.Author;
import api.clue.domain.Paper;
import api.clue.repository.mapper.AuthorMapper;
import api.clue.repository.mapper.PwaMapper;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorRepositoryImpl implements AuthorRepository {

  private final AuthorMapper authorMapper;

  private final PwaMapper pwaMapper;

  public AuthorRepositoryImpl(AuthorMapper authorMapper, PwaMapper pwaMapper) {
    this.authorMapper = authorMapper;
    this.pwaMapper = pwaMapper;
  }

  @Override
  public Author findById(Long authorId) {
    return this.authorMapper.findById(authorId);
  }

  @Override
  public List<Author> findByName(String name) {
    return this.authorMapper.findByAuthorName(name);
  }

  @Override
  public List<Author> findByPaperId(Paper paper) {
    return this.pwaMapper.findAuthorsByPaperId(paper);
  }

  @Override
  public void add(Author author) {
    this.authorMapper.insert(author);
  }

  @Override
  public void remove(Long authorId) {
    this.authorMapper.delete(authorId);
  }
}
