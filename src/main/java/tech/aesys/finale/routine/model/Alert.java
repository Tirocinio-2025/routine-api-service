package tech.aesys.finale.routine.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity(name = "alert")
public @Data class Alert implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alert_id", nullable = false)
    private Long alert_id;

    @Column(name = "ora_inizio", nullable = false)
    private LocalDateTime oraInizio;

    @Column(name = "ora_fine", nullable = false)
    private LocalDateTime oraFine;

    @ElementCollection
    @CollectionTable(name = "alert_codici", joinColumns = @JoinColumn(name = "alert_id"))
    @Column(name = "codice", nullable = false)
    private Set<String> codici;

    @Column(name = "testo_notifica", nullable = false, length = 100)
    private String testoNotifica;

    @Column(name = "citta", nullable = false, length = 100)
    private String citta;

}
