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

    public List<Author> findAll() {
        List<Author> authors = authorMapper.findAll();
        System.out.println(authors);
        return authors;
    }

    public String findById(Integer id) {
        String name = authorMapper.findById(id);
        return name;
    }
}
