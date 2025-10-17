package tech.aesys.finale.routine.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import tech.aesys.finale.routine.client.config.UserApiClientConfig;
import tech.aesys.finale.routine.dto.auth.TokenValidationResponse;

@FeignClient(
        name = "user-api",
        url = "${user-api.url}",
        configuration = UserApiClientConfig.class
)
public interface UserApiClient {

    @PostMapping("/user")
    TokenValidationResponse getUser();

}

