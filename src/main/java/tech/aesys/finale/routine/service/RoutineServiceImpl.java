package tech.aesys.finale.routine.service;


import org.springframework.stereotype.Service;
import tech.aesys.finale.routine.dto.request.RoutineDtoRequest;
import tech.aesys.finale.routine.dto.response.RoutineDtoResponse;
import tech.aesys.finale.routine.mapper.RoutineMapper;
import tech.aesys.finale.routine.model.Routine;
import tech.aesys.finale.routine.repository.RoutineRepository;

@Service
public class RoutineServiceImpl {

    private final RoutineRepository routineRepository;
    private final RoutineMapper mapper;


    public RoutineServiceImpl(RoutineRepository routineRepository, RoutineMapper mapper) {
        this.routineRepository = routineRepository;
        this.mapper = mapper;
    }
}
