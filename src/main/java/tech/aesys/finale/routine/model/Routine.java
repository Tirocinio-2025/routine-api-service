package tech.aesys.finale.routine.model;



import jakarta.persistence.*;
import lombok.Data;



import java.util.List;



@Entity(name = "routine")
public @Data class Routine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "routine_id", nullable = false)
    private Long id;

    @Column(name = "nome_routine", nullable = false)
    private String nomeRoutine;

    @JoinColumn(name = "fk_alert", referencedColumnName = "alert_id")
    @OneToMany(cascade = CascadeType.ALL)
    private List<Alert> alerts;



}

