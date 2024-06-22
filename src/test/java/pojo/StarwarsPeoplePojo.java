package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter @JsonIgnoreProperties(ignoreUnknown = true)
public class StarwarsPeoplePojo {

    private String name;
    private String height;
    private String mass;
    private String gender;
    private List<String> films;
    private List<String> vehicles;
}
