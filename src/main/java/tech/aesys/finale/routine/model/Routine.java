package tech.aesys.finale.routine.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "routine")
public @Data class Routine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "routine_id", nullable = false)
    private Long id;

    @Column(name = "nome_routine", nullable = false)
    private String nomeRoutine;

    @OneToMany(mappedBy = "routine", cascade = CascadeType.ALL)
    private List<Alert> alerts;
}
