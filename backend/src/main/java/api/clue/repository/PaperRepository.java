package api.clue.repository;

import api.clue.domain.Author;
import api.clue.domain.Paper;
import api.clue.domain.PaperSearchProvider;
import java.util.List;
import org.springframework.stereotype.Repository;

public interface PaperRepository {

  List<Paper> find(PaperSearchProvider provider);

  List<Paper> findByAuthorId(Author author);

  Paper findById(Long id);

  void add(Paper paper);

  void set(Paper paper);

  void remove(Long paperId);
}
