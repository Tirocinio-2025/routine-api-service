package tech.aesys.finale.routine.service;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tech.aesys.finale.routine.client.RoutineFeignClient;
import tech.aesys.finale.routine.model.Routine;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoutineServiceImpl implements RoutineService {

    private final RoutineFeignClient routineFeignClient;


    @Override
    public ResponseEntity<List<Routine>> getRoutines(Long id) {
        return null;
    }
}
