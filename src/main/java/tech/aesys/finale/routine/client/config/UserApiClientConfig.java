package tech.aesys.finale.routine.client.config;

import feign.Logger;
import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@Slf4j
@RequiredArgsConstructor
public class UserApiClientConfig {


    private final FeignJwtRequestInterceptor feignJwtRequestInterceptor;

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }


    @Bean
    public RequestInterceptor requestInterceptor() {
        return feignJwtRequestInterceptor;
    }

}
