package tech.aesys.finale.routine.client.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class FeignJwtRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getCredentials() != null) {
            String jwt = authentication.getCredentials().toString();
            template.header("Authorization", "Bearer " + jwt);
            template.header("Content-Type", "application/json");
            template.header("Accept", "application/json");
        }
    }

}
