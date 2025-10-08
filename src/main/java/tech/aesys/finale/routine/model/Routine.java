package tech.aesys.finale.routine.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "routine")
public class Routine {

    public Routine() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "routine_id", nullable = false)
    private Long id;

    @Column(name = "nome_routine", nullable = false)
    private String nomeRoutine;

    @OneToMany(mappedBy = "routine", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Alert> alerts;


}
