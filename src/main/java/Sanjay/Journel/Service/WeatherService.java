package Sanjay.Journel.Service;


import Sanjay.Journel.api.response.WheatherResponse;
import Sanjay.Journel.cache.AppCache;
import Sanjay.Journel.constants.Placeholders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {

    @Value("${weather.api.key}")
    private  String apiKey  ;

    @Autowired
    private  RestTemplate restTemplate;

    @Autowired
    private AppCache appCache;

    @Autowired
    private RedisService redisService;


    public WheatherResponse getWeather(String city) {
        WheatherResponse wheatherResponse = redisService.get("weather_of_" + city, WheatherResponse.class);
        if (wheatherResponse != null) {
            return wheatherResponse;
        } else {
            String finalAPI = appCache.appCache.get(AppCache.keys.WEATHER_API.toString()).replace(Placeholders.CITY, city).replace(Placeholders.API_KEY, apiKey);
            ResponseEntity<WheatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WheatherResponse.class);
            WheatherResponse body = response.getBody();
            if (body != null) {
                redisService.set("weather_of_" + city, body, 300l);
            }
            return body;
        }

    }



}
