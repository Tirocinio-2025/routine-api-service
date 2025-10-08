package tech.aesys.finale.routine.service;

import org.springframework.stereotype.Service;
import tech.aesys.finale.routine.model.WeatherCode;

import java.util.List;

@Service
public interface WeatherApiUpdateCodesService {
    List<WeatherCode> getAllWeatherCodes();
}
