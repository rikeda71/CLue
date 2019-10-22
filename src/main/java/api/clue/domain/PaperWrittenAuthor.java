package api.clue.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class PaperWrittenAuthor {
    private int author_id;
    private int paper_id;
}
