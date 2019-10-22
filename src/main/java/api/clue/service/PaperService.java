package api.clue.service;

import api.clue.domain.Paper;
import api.clue.mapper.PaperMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaperService {

    @Autowired
    private PaperMapper paperMapper;

    public Paper findById(Integer id) {
        Paper paper = paperMapper.findById(id);
        return paper;
    }

    public List<Paper> findPapers(Integer year, String task, String title, String introduction,
                                  String lang, String conference, String author) {
        List<Paper> papers = paperMapper.findPapers(year, task, title, introduction,
                                                    lang, conference, author);
        return papers;
    }

    public void insertPaper(Integer year, String label, String task, String title,
                            String url, String introduction, String lang, String conference, List<String> authors) {
        Paper paper = new Paper();
        paper.setYear(year.intValue());
        paper.setLabel(label);
        paper.setTask(task);
        paper.setTitle(title);
        paper.setUrl(url);
        paper.setIntroduction(introduction);
        paper.setLang(lang);
        paper.setConference(conference);

        paperMapper.insertPaper(paper);
    }

    public void updatePaper(int id, Integer year, String label, String task, String title,
                            String url, String introduction, String lang, String conference, List<String> authors) {
        paperMapper.updatePaper(id, year, label, task, title, url, introduction, lang, conference, authors);
    }

    public void deletePaper(int id) {
        paperMapper.deletePaper(id);
    }
}
