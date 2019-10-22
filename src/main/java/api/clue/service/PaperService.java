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
                         String url, String introduction, String conference) {
        paperMapper.insertPaper(year, label, task, title, url, introduction, conference);
    }

    public void updatePaper(int id, Integer year, String label, String task, String title,
                            String url, String introduction, String conference) {
        paperMapper.updatePaper(id, year, label, task, title, url, introduction, conference);
    }

    public void deletePaper(int id) {
        paperMapper.deletePaper(id);
    }
}
