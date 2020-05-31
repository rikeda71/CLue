package api.clue.repository;

import api.clue.domain.Author;
import api.clue.domain.Paper;
import java.util.List;

public interface AuthorRepository {

  List<Author> findByName(String name);

  List<Author> findByPaperId(Paper paper);

  Author findById(Long id);

  void add(Author author);

  void remove(Long id);

}
