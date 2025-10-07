package tech.aesys.finale.routine.dto.request;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AlertDtoRequest {

    private LocalDateTime oraInizio;
    private LocalDateTime oraFine;
    private Set<Long> codici;
    private String testoNotifica;
    private String citta;

}

