package api.clue.service;

import api.clue.ClueServiceException;
import api.clue.domain.Author;
import api.clue.domain.Paper;
import api.clue.domain.PaperSearchProvider;
import api.clue.repository.AuthorRepository;
import api.clue.repository.PaperRepository;
import api.clue.repository.PwaRepository;
import java.util.Arrays;
import java.util.Collections;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaperServiceImpl implements PaperService {

  private final PaperRepository paperRepository;

  private final AuthorRepository authorRepository;

  private final PwaRepository pwaRepository;

  public PaperServiceImpl(PaperRepository paperRepository, AuthorRepository authorRepository,
      PwaRepository pwaRepository) {
    this.paperRepository = paperRepository;
    this.authorRepository = authorRepository;
    this.pwaRepository = pwaRepository;
  }

  @Override
  public List<Paper> find(PaperSearchProvider provider, Integer offset, Integer limit) {
    String title = provider.getTitle();
    String intro = provider.getIntroduction();
    if (title != null) {
      var titleWords = title.replaceAll("　.+", " ").replaceAll("\\s.+", " ");
      provider.setTitleWords(Arrays.asList(titleWords.split(" ")));
    }
    if (intro != null) {
      var introWords = intro.replaceAll("　.+", " ").replaceAll("\\s.+", " ");
      provider.setIntroWords(Arrays.asList(introWords.split(" ")));
    }

    return this.paperRepository.find(provider, offset, limit);
  }

  @Override
  public Paper findById(Long paperId) {
    return this.paperRepository.findById(paperId);
  }

  @Override
  public List<Paper> findByAuthorId(Long authorId) {
    Author author = new Author();
    author.setAuthorId(authorId);
    return this.paperRepository.findByAuthorId(author);
  }

  @Override
  public void add(Paper paper) throws ClueServiceException {
    if (paper.getAuthorNames() == null) {
      throw new ClueServiceException("Request Body of paper must have `author names` parameter");
    }
    this.paperRepository.add(paper);
    // paper id が必要になるので取得
    List<String> authorNames = paper.getAuthorNames();
    PaperSearchProvider provider = new PaperSearchProvider();
    provider.setTitleWords(Collections.singletonList(paper.getTitle()));
    paper = this.paperRepository.find(provider, 0, 1).get(0);
    paper.setAuthorNames(authorNames);
    for (String name : paper.getAuthorNames()) {
      Author author = new Author();
      author.setName(name);
      author.setPapers(Collections.singletonList(paper));
      var findResult = this.authorRepository.findByName(name);
      // 著者が存在しない場合追加する
      if (findResult == null || findResult.size() == 0) {
        this.authorRepository.add(author);
        findResult = this.authorRepository.findByName(name);
      }
      this.pwaRepository.add(findResult.get(0), paper);
    }

  }

  @Override
  public void set(Paper paper) {
    this.paperRepository.set(paper);
  }

  @Override
  public void remove(Long paperId) {
    this.paperRepository.remove(paperId);
  }

}
