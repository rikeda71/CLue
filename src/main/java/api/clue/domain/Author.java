package api.clue.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Author {
    @JsonProperty
    private int id;

    @JsonProperty
    private String name;

}