package tech.aesys.finale.routine.service;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tech.aesys.finale.routine.model.Routine;

import java.util.List;

@Service
public class RoutineServiceImpl implements RoutineService {


    @Override
    public ResponseEntity<List<Routine>> getRoutines(Long id) {
        return null;

    }
}
