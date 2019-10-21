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

    public List<Paper> findPapers(String task, String title, String introduction,
                                   String lang, String conference) {
        List<Paper> papers = paperMapper.findPapers(task, title, introduction,
                                                    lang, conference);
        return papers;
    }

    public void addPaper(int year, String label, String title,
                         String url, String introduction, String conference) {
        paperMapper.addPaper(year, label, title, url, introduction, conference);
    }

    public void updatePaper(int id, int year, String label, String title,
                            String url, String introduction, String conference) {
        paperMapper.updatePaper(id, year, label, title, url, introduction, conference);
    }

    public void deletePaper(int id) {
        paperMapper.deletePaper(id);
    }
}
