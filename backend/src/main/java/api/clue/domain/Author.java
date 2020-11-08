package api.clue.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Author {

  private Long authorId;

  private String name;

  private List<Paper> papers;

}