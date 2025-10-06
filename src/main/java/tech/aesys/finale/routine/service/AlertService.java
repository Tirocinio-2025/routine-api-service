package tech.aesys.finale.routine.service;


import jakarta.validation.Valid;
import tech.aesys.finale.routine.swagger.model.AlertInput;
import tech.aesys.finale.routine.swagger.model.AlertOutput;

import java.util.List;

public interface AlertService {
    AlertOutput createAlert(@Valid AlertInput alertInput);

    void deleteAlert(Long id);

    AlertOutput getAlertById(Long id);

    List<AlertOutput> getAllAlerts();

    AlertOutput patchAlert(Long id, @Valid AlertInput alertInput);

    AlertOutput updateAlert(Long id, @Valid AlertInput alertInput);
}
