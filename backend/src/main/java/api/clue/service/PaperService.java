package api.clue.service;

import api.clue.ClueServiceException;
import api.clue.domain.Paper;
import api.clue.domain.PaperSearchProvider;
import java.util.List;

public interface PaperService {

  List<Paper> find(PaperSearchProvider provider, Integer offset, Integer limit);

  Paper findById(Long paperId);

  List<Paper> findByAuthorId(Long authorId);

  void add(Paper paper) throws ClueServiceException;

  void set(Paper paper);

  void remove(Long paperId);

}
