package api.clue.service;

import api.clue.domain.Author;
import api.clue.mapper.AuthorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorMapper authorMapper;

    public List<Author> findAuthor(String name) {
        List<Author> authors = authorMapper.findAuthor(name);
        return authors;
    }

    public String findById(Integer id) {
        String name = authorMapper.findById(id);
        return name;
    }


    public void insertAuthor(String name) {
        Author author = new Author();
        author.setName(name);
        authorMapper.insertAuthor(author);
    }

    public void deleteById(int id) {
        authorMapper.deleteAuthor(id);
    }
}
