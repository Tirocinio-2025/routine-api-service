package tech.aesys.finale.routine.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.aesys.finale.routine.exception.AlertNotFoundException;
import tech.aesys.finale.routine.mapper.AlertMapper;
import tech.aesys.finale.routine.model.Alert;
import tech.aesys.finale.routine.repository.AlertRepository;
import tech.aesys.finale.routine.swagger.model.AlertInput;
import tech.aesys.finale.routine.swagger.model.AlertOutput;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AlertServiceImpl implements AlertService {
    private final AlertRepository alertRepository;
    private final AlertMapper alertMapper;

    public AlertServiceImpl(AlertRepository alertRepository, AlertMapper alertMapper) {
        this.alertRepository = alertRepository;
        this.alertMapper = alertMapper;
    }

    @Override
    public AlertOutput createAlert(AlertInput alertInput) {
        Alert alert = alertMapper.toEntity(alertMapper.toDtoRequest(alertInput));
        Alert savedAlert = alertRepository.save(alert);
        return alertMapper.toOutput(alertMapper.toDto(savedAlert));
    }

    @Override
    public void deleteAlert(Long id) {
       Alert alert =  alertRepository.findById(id).orElseThrow(() -> new AlertNotFoundException("Alert non trovato"));
       alertRepository.delete(alert);
    }

    @Override
    public AlertOutput getAlertById(Long id) {
        Alert alert = alertRepository.findById(id).orElseThrow(() -> new AlertNotFoundException("Alert non trovato"));
        return alertMapper.toOutput(alertMapper.toDto(alert));
    }

    @Override
    public List<AlertOutput> getAllAlerts() {
        return  alertRepository.findAll().stream()
                .map(alertMapper::toDto)
                .map(alertMapper::toOutput)
                .collect(Collectors.toList());
    }

    @Override
    public AlertOutput patchAlert(Long id, AlertInput alertInput) {
        Alert alert = alertRepository.findById(id).orElseThrow(() -> new AlertNotFoundException("Alert non trovato"));

        if(alertInput.getCitta() != null)  alert.setCitta(alertInput.getCitta());
        if(alertInput.getOraFine() != null) alert.setOraFine(alertMapper.mapStringToLocalDateTime(alertInput.getOraFine()));
        if(alertInput.getOraInizio() != null) alert.setOraInizio(alertMapper.mapStringToLocalDateTime(alertInput.getOraInizio()));
        if(alertInput.getTipoMessaggio() != null) alert.setTestoNotifica(alertInput.getTipoMessaggio());
        if(alertInput.getCodici() != null) alert.setCodici(new HashSet<>(alertInput.getCodici()));

        Alert updatedAlert = alertRepository.save(alert);
        return alertMapper.toOutput(alertMapper.toDto(updatedAlert));

    }

    @Override
    public AlertOutput updateAlert(Long id, AlertInput alertInput) {
        Alert alert = alertRepository.findById(id).orElse(null);
        if(alert == null) return null;
        alert.setCitta(alertInput.getCitta());
        alert.setOraFine(alertMapper.mapStringToLocalDateTime(alertInput.getOraFine()));
        alert.setOraInizio(alertMapper.mapStringToLocalDateTime(alertInput.getOraInizio()));
        alert.setTestoNotifica(alertInput.getTipoMessaggio());
        alert.setCodici(new HashSet<>(alertInput.getCodici()));
        Alert updatedAlert = alertRepository.save(alert);
        return alertMapper.toOutput(alertMapper.toDto(updatedAlert));
    }
}
