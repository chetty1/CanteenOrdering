package Services;

import Model.WeatherObject;
import Repositories.weatherRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by chett_000 on 2017/09/20.
 */

public class Weather {

    @Autowired
    weatherRepository repository;

@PostConstruct
    public void Weather() throws IOException {
     try {
         RestTemplate restTemplate = new RestTemplate();
         ResponseEntity<String> response = restTemplate.getForEntity("http://api.openweathermap.org/data/2.5/weather?q=Pinetown&units=metric&APPID=7779f6b5aa7cafdea5d217376eb911dd", String.class);

         ObjectMapper mapper = new ObjectMapper();
         JsonNode root = mapper.readTree(response.getBody());

         SimpleDateFormat format = new SimpleDateFormat("dd/MM/YYYY");


         WeatherObject obj = new WeatherObject(root.findValue("temp_max").asText(), root.findValues("weather").get(0).findValue("main").asText(), format.format(new Date()));
         repository.save(obj);
         System.out.println(root.findValues("weather").get(0).findValue("main"));
     }
     catch (Exception e ){

     }
    }
}
