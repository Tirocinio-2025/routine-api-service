package tech.aesys.finale.routine.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tech.aesys.finale.routine.model.WeatherCode;

import java.util.List;


@FeignClient(name = "WeatherApiUpdateCodesFeign",
        url = "${weather.codes.remote}",
        configuration = tech.aesys.finale.routine.client.config.WeatherApiUpdateCodesFeignConfig.class
)
public interface WeatherApiUpdateCodesFeign {

    @RequestMapping(method = RequestMethod.GET, value = "/weather/codes")
    List<WeatherCode> getAllWeatherCodes();

}
