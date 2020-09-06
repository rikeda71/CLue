package api.clue.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaperSearchProvider {

  private Integer year;

  private String label;

  private String task;

  private String title;

  private String introduction;

  private String lang;

  private String conference;

  private List<String> titleWords;

  private List<String> introWords;

}
