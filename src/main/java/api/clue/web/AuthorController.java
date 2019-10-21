package api.clue.web;

import api.clue.domain.Author;
import api.clue.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @RequestMapping(value="/authors", method=RequestMethod.GET)
    public List<Author> authorsAll() {
        List<Author> authors = authorService.findAll();
        return authors;
    }

    @RequestMapping(value="authors/{id}", method=RequestMethod.GET)
    public String authorById(@PathVariable("id") int id) {
        String name = authorService.findById(id);
        return name;
    }

}
