package api.clue.web;

import api.clue.domain.Author;
import api.clue.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @RequestMapping(value="/authors", method = RequestMethod.GET)
    public List<Author> findAuthors(@RequestParam(value = "name", required = false) String name) {
        List<Author> authors = authorService.findAuthor(name);
        return authors;
    }

    @RequestMapping(value="/authors/{id}", method = RequestMethod.GET)
    public String authorById(@PathVariable("id") int id) {
        String name = authorService.findById(id);
        return name;
    }

    @RequestMapping(value="/authors", method = RequestMethod.POST)
    public void insertAuthor(@RequestParam("name") String name) {
        authorService.insertAuthor(name);
    }

    @RequestMapping(value="/authors/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable("id") int id) {
        authorService.deleteById(id);
    }

}
