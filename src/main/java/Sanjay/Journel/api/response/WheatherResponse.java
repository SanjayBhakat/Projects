package Sanjay.Journel.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WheatherResponse {

    private Current current;


    public class Current{
        @Getter
        @Setter
        private int temperature;
        @JsonProperty("weather_descriptions")
        private List<String> weatherDescriptions;
        private int feelslike;
    }

}
