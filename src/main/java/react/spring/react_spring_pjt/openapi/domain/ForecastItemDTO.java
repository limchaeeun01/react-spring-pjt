package react.spring.react_spring_pjt.openapi.domain;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import react.spring.react_spring_pjt.openapi.service.ForecastService;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ForecastItemDTO {

    @Autowired
    private ForecastService forecastService;

    @JsonProperty("beachNum")
    private String beachNum;

    @JsonProperty("baseDate")
    private String baseDate;

    @JsonProperty("baseTime")
    private String baseTime;

    @JsonProperty("category")
    private String category;

    @JsonProperty("fcstDate")
    private String fcstDate;

    @JsonProperty("fcstTime")
    private String fcstTime;

    @JsonProperty("fcstValue")
    private String fcstValue;

    @JsonProperty("nx")
    private int nx;

    @JsonProperty("ny")
    private int ny;

    private String categoryName;

}
