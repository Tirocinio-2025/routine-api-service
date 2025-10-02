package tech.aesys.finale.routine.service;

import org.springframework.http.ResponseEntity;
import tech.aesys.finale.routine.model.Routine;

import java.util.List;

public interface RoutineService {

    ResponseEntity<List<Routine>> getRoutines(Long id);

}
