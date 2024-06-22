package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @JsonIgnoreProperties(ignoreUnknown = true)
public class StarWarsPlanetPojo {

    private String name;
    private String climate;
    private String terrain;
}
