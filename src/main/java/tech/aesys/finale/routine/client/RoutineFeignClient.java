package tech.aesys.finale.routine.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tech.aesys.finale.routine.client.config.RoutineFeignConfig;
import tech.aesys.finale.routine.model.WeatherCode;

import java.util.List;


@FeignClient(name = "RoutineFeignClient",
        url = "${weather.codes.remote}",
        configuration = RoutineFeignConfig.class
)
public interface RoutineFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/weather/codes")
    List<WeatherCode> getAllWeatherCodes();

}
