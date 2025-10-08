package tech.aesys.finale.routine.service;

import org.springframework.stereotype.Service;
import tech.aesys.finale.routine.model.WeatherCode;

import java.util.List;

@Service
public class WeatherApiUpdateCodesServiceImpl implements WeatherApiUpdateCodesService {

    @Override
    public List<WeatherCode> getAllWeatherCodes() {
        return List.of();
    }
}
