package tech.aesys.finale.routine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.aesys.finale.routine.model.AlertWeatherCode;
import tech.aesys.finale.routine.model.AlertWeatherCodePK;

@Repository
public interface AlertWeatherCodeRepository extends JpaRepository<AlertWeatherCode, AlertWeatherCodePK> {
}
