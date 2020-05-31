package api.clue.web;

import api.clue.domain.Paper;
import api.clue.service.PaperServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PaperController {

    @Autowired
    private PaperServiceImpl paperServiceImpl;

    @RequestMapping(value = "/papers/{id}", method = RequestMethod.GET)
    public Paper paperById(@PathVariable("id") int id) {
        Paper paper = paperServiceImpl.findById(id);
        return paper;
    }

    @RequestMapping(value = "/papers", method = RequestMethod.GET)
    public List<Paper> findPapers(@RequestParam(name = "year", required = false) Integer year,
            @RequestParam(name = "task", required = false) String task,
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "introduction", required = false) String introduction,
            @RequestParam(name = "lang", required = false) String lang,
            @RequestParam(name = "conference", required = false) String conference,
            @RequestParam(name = "author", required = false) String author) {
        List<Paper> papers = paperServiceImpl
            .findPapers(year, task, title, introduction, lang, conference, author);
        return papers;
    }

    @RequestMapping(value = "papers", method = RequestMethod.POST)
    public void insertPaper(@RequestParam(value = "year", required = false) Integer year,
            @RequestParam(value = "label", required = false) String label,
            @RequestParam(value = "task", required = false) String task, @RequestParam(value = "title") String title,
            @RequestParam(value = "url") String url,
            @RequestParam(value = "introduction", required = false) String introduction,
            @RequestParam(value = "lang", required = false) String lang,
            @RequestParam(value = "conference", required = false) String conference,
            @RequestParam(value = "authors", required = false) List<String> authors) {
        paperServiceImpl
            .insertPaper(year, label, task, title, url, introduction, lang, conference, authors);
    }

    @RequestMapping(value = "/papers/{id}", method = RequestMethod.PATCH)
    public void updatePaper(@PathVariable("id") int id, @RequestParam(value = "year", required = false) Integer year,
            @RequestParam(value = "label", required = false) String label,
            @RequestParam(value = "task", required = false) String task,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "url", required = false) String url,
            @RequestParam(value = "introduction", required = false) String introduction,
            @RequestParam(value = "lang", required = false) String lang,
            @RequestParam(value = "conference", required = false) String conference,
            @RequestParam(value = "authors", required = false) List<String> authors) {
        paperServiceImpl
            .updatePaper(id, year, label, task, title, url, introduction, lang, conference, authors);
    }

    @RequestMapping(value = "papers/{id}", method = RequestMethod.DELETE)
    public void deletePaper(@PathVariable("id") int id) {
        paperServiceImpl.deletePaper(id);
    }
}
