package api.clue.repository;

import api.clue.domain.Author;
import api.clue.domain.Paper;
import java.util.List;

public interface PwaRepository {

  List<Author> findAuthorsByPaperId(Paper paper);

  List<Paper> findPapersByAuthorId(Author author);

  void add(Author author, Paper paper);

}
