package api.clue.service;

import api.clue.domain.Author;
import api.clue.domain.Paper;
import api.clue.domain.PaperSearchProvider;
import api.clue.repository.AuthorRepository;
import api.clue.repository.PaperRepository;
import api.clue.repository.PwaRepository;
import java.util.Arrays;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaperServiceImpl implements PaperService {

  private final PaperRepository paperRepository;

  private final AuthorRepository authorRepository;

  private final PwaRepository pwaRepository;

  public PaperServiceImpl(PaperRepository paperRepository, AuthorRepository authorRepository, PwaRepository pwaRepository) {
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
  public void add(Paper paper) {
    this.paperRepository.add(paper);
    // 著者が存在しない場合追加する
    for (Author author : paper.getAuthors()) {
      var findResult = this.authorRepository.findByName(author.getName());
      if (findResult == null || findResult.size() == 0) {
        this.authorRepository.add(author);
      }
      this.pwaRepository.add(author, paper);
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
