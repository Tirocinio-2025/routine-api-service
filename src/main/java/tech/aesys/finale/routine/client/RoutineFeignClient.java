package tech.aesys.finale.routine.client;


import org.springframework.cloud.openfeign.FeignClient;
import tech.aesys.finale.routine.client.config.RoutineFeignConfig;

@FeignClient(name = "RoutineFeignClient",
        url = "",
        configuration = RoutineFeignConfig.class
)
public interface RoutineFeignClient {

}
