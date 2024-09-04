package react.spring.react_spring_pjt.openapi.service;

import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import react.spring.react_spring_pjt.openapi.util.CategoryCode;

import react.spring.react_spring_pjt.openapi.domain.ForecastItemDTO;
import react.spring.react_spring_pjt.openapi.domain.ForecastItems;

import java.util.List;

@Service
public class ForecastService {

    public List<ForecastItemDTO> parsingJson(String str) {
        ObjectMapper mapper = new ObjectMapper();
        List<ForecastItemDTO> list = null;
        try {
            ForecastItems items = mapper.readValue(str, ForecastItems.class);
            list = items.getItems();
            System.out.println("service pasrsing json size = " + list.size());

            for (ForecastItemDTO item : list) {
                decodeCategry(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private void decodeCategry(ForecastItemDTO item) {
        String name = CategoryCode.valueOf(item.getCategory()).getName();
        String value = CategoryCode.getCodeValue(item.getCategory(), item.getFcstValue());
        String unit = CategoryCode.valueOf(item.getCategory()).getUnit();
        System.out.println("name = " + name);
        System.out.println("value = " + value);
        System.out.println("unit = " + unit);
        System.out.println("==================");
        item.setCategory(name);
        item.setFcstValue(value + unit);
    }
}
