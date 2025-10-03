package tech.aesys.finale.routine.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "routine")
public class Routine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "routine_id", nullable = false)
    private Long id;

    @Column(name = "nome_routine", nullable = false)
    private String nomeRoutine;

    @OneToMany(mappedBy = "routine", fetch = FetchType.LAZY)
    private List<Alert> alerts;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeRoutine() {
        return nomeRoutine;
    }

    public void setNomeRoutine(String nomeRoutine) {
        this.nomeRoutine = nomeRoutine;
    }

    public List<Alert> getAlerts() {
        return alerts;
    }

    public void setAlerts(List<Alert> alerts) {
        this.alerts = alerts;
    }
}
