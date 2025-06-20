package Sanjay.Journel.service;

import Sanjay.Journel.Service.RedisService;
import Sanjay.Journel.Service.WeatherService;
import Sanjay.Journel.api.response.WheatherResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class WeatherServiceTests {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private RedisService redisService;

    @InjectMocks
    private WeatherService weatherService;

    @Test
    void testGetWeather_FromRedis() {
        WheatherResponse mockResponse = new WheatherResponse();
        when(redisService.get("weather_of_City", WheatherResponse.class)).thenReturn(mockResponse);

        WheatherResponse result = weatherService.getWeather("City");

        assertNotNull(result);
        verify(restTemplate, never()).exchange(anyString(), any(), any(), eq(WheatherResponse.class));
    }

    @Test
    void testGetWeather_FromApi() {
        when(redisService.get("weather_of_City", WheatherResponse.class)).thenReturn(null);
        when(restTemplate.exchange(anyString(), any(), any(), eq(WheatherResponse.class)))
                .thenReturn(ResponseEntity.ok(new WheatherResponse()));

        WheatherResponse result = weatherService.getWeather("City");

        assertNotNull(result);
        verify(redisService).set(anyString(), any(), anyLong());
    }
}
