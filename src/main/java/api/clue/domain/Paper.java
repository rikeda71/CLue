package api.clue.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Paper {

    @JsonProperty
    private int id;

    @JsonProperty
    private int year;
    private String label;

    @JsonProperty
    private String task;

    private String session;

    @JsonProperty
    private String title;

    @JsonProperty
    private String url;

    @JsonProperty
    private String introduction;

    @JsonProperty
    private String lang;

    @JsonProperty
    private String conference;
}
