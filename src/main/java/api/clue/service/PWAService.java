package api.clue.service;

import api.clue.domain.Author;
import api.clue.domain.Paper;
import api.clue.mapper.PWAMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PWAService {

    @Autowired
    PWAMapper pwaMapper;

    public void insertPWA(Author author, Paper paper) {
        pwaMapper.insert(author, paper);
    }
}
