package tech.aesys.finale.routine.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.aesys.finale.routine.exception.AlertNotFoundException;
import tech.aesys.finale.routine.mapper.AlertMapper;
import tech.aesys.finale.routine.model.Alert;
import tech.aesys.finale.routine.model.AlertWeatherCode;
import tech.aesys.finale.routine.model.AlertWeatherCodePK;
import tech.aesys.finale.routine.model.WeatherCode;
import tech.aesys.finale.routine.repository.AlertRepository;
import tech.aesys.finale.routine.repository.WeatherCodeRepository;
import tech.aesys.finale.routine.swagger.model.AlertInput;
import tech.aesys.finale.routine.swagger.model.AlertOutput;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlertServiceImpl implements AlertService {
    private final AlertRepository alertRepository;
    private final WeatherCodeRepository weatherCodeRepository;
    private final AlertMapper alertMapper;

    @Override
    public AlertOutput createAlert(AlertInput alertInput) {
        var alert = alertMapper.toEntity(alertInput);

        if (alertInput.getCodici() != null && !alertInput.getCodici().isEmpty()) {
            Set<AlertWeatherCode> alertWeatherCodes = createAlertWeatherCodes(
                    alert, alertInput.getCodici());
            alert.setCodici(alertWeatherCodes);
        }
        alertRepository.save(alert);

        return alertMapper.toOutput(alert);
    }

    private Set<AlertWeatherCode> createAlertWeatherCodes(Alert alert, List<Long> codiciInput) {

        List<WeatherCode> weatherCodes = weatherCodeRepository.findAllByCodeIn(codiciInput);


        Map<Long, WeatherCode> weatherCodeMap = weatherCodes.stream()
                .collect(Collectors.toMap(WeatherCode::getCode, Function.identity()));


        Set<AlertWeatherCode> alertWeatherCodes = new HashSet<>();
        for (Long code : codiciInput) {
            WeatherCode weatherCode = weatherCodeMap.get(code);
            if (weatherCode != null) {
                AlertWeatherCodePK pk = new AlertWeatherCodePK();
                pk.setAlertId(alert.getId());
                pk.setCode(code);

                AlertWeatherCode alertWeatherCode = new AlertWeatherCode();
                alertWeatherCode.setId(pk);
                alertWeatherCode.setAlert(alert);
                alertWeatherCode.setWeatherCode(weatherCode);

                alertWeatherCodes.add(alertWeatherCode);
            }
        }

        return alertWeatherCodes;
    }

    @Override
    public void deleteAlert(Long id) {
        Alert alert = alertRepository.findById(id).orElseThrow(() -> new AlertNotFoundException("Alert non trovato"));
        alertRepository.delete(alert);
    }

    @Override
    public AlertOutput getAlertById(Long id) {
        Alert alert = alertRepository.findByIdWithWeatherCodes(id).orElseThrow(() -> new AlertNotFoundException("Alert non trovato"));
        return alertMapper.toOutput(alert);
    }

    @Override
    public List<AlertOutput> getAllAlerts() {
        return alertRepository.findAllWithWeatherCodes().stream()
                .map(alertMapper::toOutput)
                .collect(Collectors.toList());
    }

    @Override
    public AlertOutput patchAlert(Long id, AlertInput alertInput) {
        Alert alert = alertRepository.findById(id).orElseThrow(() -> new AlertNotFoundException("Alert non trovato"));

        if (alertInput.getCitta() != null) alert.setCitta(alertInput.getCitta());
        if (alertInput.getOraFine() != null) alert.setOraFine(alertMapper.mapStringToLocalDateTime(alertInput.getOraFine()));
        if (alertInput.getOraInizio() != null) alert.setOraInizio(alertMapper.mapStringToLocalDateTime(alertInput.getOraInizio()));
        if (alertInput.getTipoMessaggio() != null) alert.setTestoNotifica(alertInput.getTipoMessaggio());

        if (alertInput.getCodici() != null) {
            Set<AlertWeatherCode> nuoviCodici = createAlertWeatherCodes(alert, alertInput.getCodici());
            alert.setCodici(nuoviCodici);
        }

        Alert updatedAlert = alertRepository.save(alert);
        return alertMapper.toOutput(updatedAlert);
    }

    @Override
    public AlertOutput updateAlert(Long id, AlertInput alertInput) {
        Alert alert = alertRepository.findById(id).orElse(null);
        if (alert == null) return null;

        alert.setCitta(alertInput.getCitta());
        alert.setOraFine(alertMapper.mapStringToLocalDateTime(alertInput.getOraFine()));
        alert.setOraInizio(alertMapper.mapStringToLocalDateTime(alertInput.getOraInizio()));
        alert.setTestoNotifica(alertInput.getTipoMessaggio());

        Set<AlertWeatherCode> codici = createAlertWeatherCodes(alert, alertInput.getCodici());
        alert.setCodici(codici);

        Alert updatedAlert = alertRepository.save(alert);
        return alertMapper.toOutput(updatedAlert);
    }
}