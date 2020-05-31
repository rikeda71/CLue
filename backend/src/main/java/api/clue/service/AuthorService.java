package api.clue.service;

import api.clue.domain.Author;
import java.util.List;

public interface AuthorService {

  Author findById(Long authorId);

  List<Author> findByName(String name);

  List<Author> findByPaperId(Long paperId);

  void add(Author author);

  void remove(Long authorId);

}
