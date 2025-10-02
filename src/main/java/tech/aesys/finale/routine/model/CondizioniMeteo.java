package tech.aesys.finale.routine.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity(name = "condizioni_meteo")
public @Data @AllArgsConstructor class CondizioniMeteo {


    @Id
    @Column(name = "codice", nullable = false)
    private Long codice;

    @Column(name = "tipo", nullable = false)
    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    public CondizioniMeteo() {

    }

    public enum Tipo {
        SOLEGGIATO,
        PARZIALMENTE_NUVOLOSO,
        NUVOLOSO
    }
}

