package tech.aesys.finale.routine.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.aesys.finale.routine.model.WeatherCode;
import tech.aesys.finale.routine.service.WeatherApiUpdateCodesService;

import java.util.List;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class UpdateCodesController {
    private final WeatherApiUpdateCodesService weatherApiUpdateCodesService;

    @GetMapping("/weather/codes")
    public List<WeatherCode> getAllWeatherCodes() {
        return weatherApiUpdateCodesService.getAllWeatherCodes();

    }
}
