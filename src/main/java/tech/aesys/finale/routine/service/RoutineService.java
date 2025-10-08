package tech.aesys.finale.routine.service;


import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import tech.aesys.finale.routine.swagger.model.AlertInput;
import tech.aesys.finale.routine.swagger.model.AlertOutput;
import tech.aesys.finale.routine.swagger.model.RoutineInput;
import tech.aesys.finale.routine.swagger.model.RoutineOutput;

import java.util.List;
@Service
public interface RoutineService {

    RoutineOutput createRoutine(RoutineInput routineInput);
    void deleteRoutine(Long id);
    List<AlertOutput> getAlertsForRoutine(Long id);
    List<RoutineOutput> getAllRoutines();
    RoutineOutput getRoutineById(Long id);

    AlertOutput createAlertForRoutine(Long id, AlertInput alertInput);

    void unlinkAlertFromRoutine(Long id, Long alertId);
}
