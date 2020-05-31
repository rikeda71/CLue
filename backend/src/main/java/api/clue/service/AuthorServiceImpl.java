package api.clue.service;

import api.clue.domain.Author;
import api.clue.domain.Paper;
import api.clue.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

  private final AuthorRepository authorRepository;

  public AuthorServiceImpl(AuthorRepository authorRepository) {
    this.authorRepository = authorRepository;
  }

  @Override
  public Author findById(Long authorId) {
    return this.authorRepository.findById(authorId);
  }

  @Override
  public List<Author> findByName(String name) {
    return this.authorRepository.findByName(name);
  }

  @Override
  public List<Author> findByPaperId(Long paperId) {
    Paper paper = new Paper();
    paper.setPaperId(paperId);
    return this.authorRepository.findByPaperId(paper);
  }

  @Override
  public void add(Author author) {
    this.authorRepository.add(author);
  }

  @Override
  public void remove(Long authorId) {
    this.authorRepository.remove(authorId);
  }
}
