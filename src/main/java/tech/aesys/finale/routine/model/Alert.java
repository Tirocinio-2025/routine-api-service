package tech.aesys.finale.routine.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "alert")
public  class Alert implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alert_id", nullable = false)
    private Long id;

    @Column(name = "ora_inizio", nullable = false)
    private LocalDateTime oraInizio;

    @Column(name = "ora_fine", nullable = false)
    private LocalDateTime oraFine;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "alert")
    private Set<AlertWeatherCode> codici;

    @Column(name = "testo_notifica", nullable = false, length = 100)
    private String testoNotifica;

    @Column(name = "citta", nullable = false, length = 100)
    private String citta;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "routine_id")
    private Routine routine;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getOraInizio() {
        return oraInizio;
    }

    public void setOraInizio(LocalDateTime oraInizio) {
        this.oraInizio = oraInizio;
    }

    public LocalDateTime getOraFine() {
        return oraFine;
    }

    public void setOraFine(LocalDateTime oraFine) {
        this.oraFine = oraFine;
    }

    public Set<AlertWeatherCode> getCodici() {
        return codici;
    }

    public void setCodici(Set<AlertWeatherCode> codici) {
        this.codici = codici;
    }

    public String getTestoNotifica() {
        return testoNotifica;
    }

    public void setTestoNotifica(String testoNotifica) {
        this.testoNotifica = testoNotifica;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public Routine getRoutine() {
        return routine;
    }

    public void setRoutine(Routine routine) {
        this.routine = routine;
    }
}
