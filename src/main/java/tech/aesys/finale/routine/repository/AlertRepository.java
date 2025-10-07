package tech.aesys.finale.routine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tech.aesys.finale.routine.model.Alert;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {

    List<Alert> getAlertByRoutineId(Long id);

    @Query("SELECT DISTINCT a FROM Alert a LEFT JOIN FETCH a.codici c LEFT JOIN FETCH c.weatherCode")
    List<Alert> findAllWithWeatherCodes();

    @Query("SELECT a FROM Alert a LEFT JOIN FETCH a.codici c LEFT JOIN FETCH c.weatherCode WHERE a.id = :id")
    Optional<Alert> findByIdWithWeatherCodes(Long id);
}
