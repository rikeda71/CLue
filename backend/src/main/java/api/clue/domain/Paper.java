package api.clue.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Paper {

  private Long paperId;

  @NotNull
  private Integer year;

  private String label;

  private String task;

  private String session;

  @NotNull
  private String title;

  @NotNull
  private String url;

  @NotNull
  private String introduction;

  private String lang;

  @NotNull
  private String conference;

  private List<Author> authors;

  private List<String> authorNames;
}
