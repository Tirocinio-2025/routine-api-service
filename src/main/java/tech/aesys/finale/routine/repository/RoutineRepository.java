package tech.aesys.finale.routine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.aesys.finale.routine.model.Routine;



@Repository
public interface RoutineRepository extends JpaRepository<Routine, Long> {


}
