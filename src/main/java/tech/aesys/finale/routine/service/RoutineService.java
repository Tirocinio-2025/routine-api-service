package tech.aesys.finale.routine.service;


import tech.aesys.finale.routine.swagger.model.AlertOutput;
import tech.aesys.finale.routine.swagger.model.RoutineInput;
import tech.aesys.finale.routine.swagger.model.RoutineOutput;

import java.util.List;

public interface RoutineService {

    RoutineOutput createRoutine(RoutineInput routineInput);
    void deleteRoutine(Long id);
    List<AlertOutput> getAlertsForRoutine(Long id);
    List<RoutineOutput> getAllRoutines();
    RoutineOutput getRoutineById(Long id);
    RoutineOutput patchRoutine(Long id, RoutineInput routineInput);


}
