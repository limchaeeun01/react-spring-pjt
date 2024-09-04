package react.spring.react_spring_pjt.openapi.ctrl;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import react.spring.react_spring_pjt.openapi.domain.ForecastItemDTO;
import react.spring.react_spring_pjt.openapi.service.ForecastService;

@RestController
@RequestMapping("/api")
public class ForcastController {

    @Value("${openApi.serviceKey}")
    private String serviceKey;

    @Value("${openApi.callBackUrl}")
    private String callBackUrl;

    @Value("${openApi.dataType}")
    private String dataType;

    @Autowired
    private ForecastService forecastService;

    @GetMapping("forcast")
    public ResponseEntity<List<ForecastItemDTO>> callForecastApi(
            @RequestParam(value = "base_time") String baseTime,
            @RequestParam(value = "base_date") String baseDate,
            @RequestParam(value = "beach_num") String beachNum
    ) {

        System.out.println("client end point : /api/forecast");
        System.out.println("serviceKey = " + serviceKey);
        System.out.println("callBackUrl = " + callBackUrl);
        System.out.println("dataType = " + dataType);
        System.out.println("params = " + baseTime + "\t" + baseDate + "\t" + beachNum);

        String requestURL = callBackUrl
                +"?serviceKey="+serviceKey
                +"&dataType="+dataType
                +"&base_date="+baseDate
                +"&base_time="+baseTime
                +"&beach_num="+beachNum;

        System.out.println("url check = " + requestURL);

        HttpURLConnection http = null;
        InputStream stream = null;
        String result = null;
        List<ForecastItemDTO> list = null;
        try {
            URL url = new URL(requestURL);
            http = (HttpURLConnection) url.openConnection();
            System.out.println("http connection = " + http);
            int code = http.getResponseCode();
            System.out.println("http response code = " + code);
            if (code == 200) {
                stream = http.getInputStream();
                result = readString(stream);
                System.out.println("result = " + result);

                list = forecastService.parsingJson(result);
            } else {

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    public String readString(InputStream stream) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
        String input = null;
        StringBuilder result = new StringBuilder();
        while ((input = br.readLine()) != null) {
            result.append(input + "\n\r");
        }
        br.close();
        return result.toString();
    }

}
