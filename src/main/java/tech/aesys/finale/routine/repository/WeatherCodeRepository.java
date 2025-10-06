package tech.aesys.finale.routine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.aesys.finale.routine.model.WeatherCode;

import java.util.List;

@Repository
public interface WeatherCodeRepository extends JpaRepository<WeatherCode, Long> {

    List<WeatherCode> findAllByCodeIn(List<Long> ids);
}
