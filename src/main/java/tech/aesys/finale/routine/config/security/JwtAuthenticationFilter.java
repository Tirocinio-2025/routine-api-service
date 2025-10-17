package tech.aesys.finale.routine.config.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import tech.aesys.finale.routine.dto.auth.TokenValidationResponse;
import tech.aesys.finale.routine.exception.InvalidTokenException;
import tech.aesys.finale.routine.exception.TokenValidationException;
import tech.aesys.finale.routine.service.UserDetailsService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            try {
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        null,
                        authHeader,
                        null);
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(auth);

                TokenValidationResponse validation =  userDetailsService.getUser();

                List<SimpleGrantedAuthority> authorities = validation.getRoles().stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

                auth = new UsernamePasswordAuthenticationToken(
                        validation.getUsername(),
                        authHeader,
                        authorities);
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(auth);

                if (validation.isValid() && validation.getUsername() != null) {
                    log.info("Token validato con successo per l'utente: {}", validation.getUsername());

                } else {
                    log.warn("Token non valido: {}", validation.getMessage());
                    throw new InvalidTokenException("Token non valido o scaduto: " + validation.getMessage());
                }
            } catch (InvalidTokenException e) {
                log.error("Token non valido: {}", e.getMessage());
                throw e;
            } catch (Exception e) {
                log.error("Errore durante la validazione del token: {}", e.getMessage());
                throw new TokenValidationException("Errore durante la validazione del token", e);
            }
        }

        chain.doFilter(request, response);
    }
}
