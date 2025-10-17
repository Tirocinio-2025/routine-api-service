package tech.aesys.finale.routine.service;

import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import tech.aesys.finale.routine.client.UserApiClient;
import tech.aesys.finale.routine.dto.auth.TokenValidationResponse;

@Service
@Primary
@RequiredArgsConstructor
public class UserDetailsService {

    private final UserApiClient userApiClient;

    public TokenValidationResponse getUser() {
        return userApiClient.getUser();
    }

    public String extractUsernameFromToken(String token) {
        return Jwts.parser()
                .build()
                .parseClaimsJwt(token)
                .getBody()
                .getSubject();
    }
}
