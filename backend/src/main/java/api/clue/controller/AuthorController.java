package api.clue.web;

import api.clue.domain.Author;
import api.clue.service.AuthorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthorController {

    @Autowired
    private AuthorServiceImpl authorServiceImpl;

    @RequestMapping(value = "/authors", method = RequestMethod.GET)
    public List<Author> findAuthors(@RequestParam(value = "name", required = false) String name) {
        List<Author> authors = authorServiceImpl.findAuthor(name);
        return authors;
    }

    @RequestMapping(value = "/authors/{id}", method = RequestMethod.GET)
    public String authorById(@PathVariable("id") int id) {
        String name = authorServiceImpl.findById(id);
        return name;
    }

    @RequestMapping(value = "/authors", method = RequestMethod.POST)
    public void insertAuthor(@RequestParam("name") String name) {
        authorServiceImpl.insertAuthor(name);
    }

    @RequestMapping(value = "/authors/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable("id") int id) {
        // authorService.deleteById(id);
    }

}
