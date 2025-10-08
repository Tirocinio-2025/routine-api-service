package tech.aesys.finale.routine.service;


import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import tech.aesys.finale.routine.exception.RoutineNonTrovataException;
import tech.aesys.finale.routine.mapper.AlertMapper;
import tech.aesys.finale.routine.mapper.RoutineMapper;
import tech.aesys.finale.routine.model.*;
import tech.aesys.finale.routine.repository.AlertRepository;
import tech.aesys.finale.routine.repository.AlertWeatherCodeRepository;
import tech.aesys.finale.routine.repository.RoutineRepository;
import tech.aesys.finale.routine.repository.WeatherCodeRepository;
import tech.aesys.finale.routine.swagger.model.AlertInput;
import tech.aesys.finale.routine.swagger.model.AlertOutput;
import tech.aesys.finale.routine.swagger.model.RoutineInput;
import tech.aesys.finale.routine.swagger.model.RoutineOutput;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoutineServiceImpl implements RoutineService {

    private final RoutineRepository routineRepository;
    private final AlertRepository alertRepository;
    private final WeatherCodeRepository weatherCodeRepository;
    private final RoutineMapper routineMapper;
    private final AlertMapper alertMapper;
    private final AlertWeatherCodeRepository alertWeatherCodeRepository;


    public RoutineServiceImpl(RoutineRepository routineRepository, RoutineMapper routineMapper, AlertMapper alertMapper, AlertRepository alertRepository, WeatherCodeRepository weatherCodeRepository, AlertWeatherCodeRepository alertWeatherCodeRepository) {
        this.routineRepository = routineRepository;
        this.routineMapper = routineMapper;
        this.alertMapper = alertMapper;
        this.alertRepository = alertRepository;
        this.weatherCodeRepository = weatherCodeRepository;
        this.alertWeatherCodeRepository = alertWeatherCodeRepository;
    }

    @Override
    public RoutineOutput createRoutine(RoutineInput routineInput) {

        Routine routine = this.createRoutineEntity(routineInput);

        routine = routineRepository.save(routine);
        return routineMapper.toOutput(routine);

    }

    private Routine createRoutineEntity(RoutineInput routineInput) {
        Routine routine = routineMapper.toEntity(routineInput);

        List<Alert> alerts = createAlerts(routine, routineInput.getAlerts());

        routine.setAlerts(alerts);

        return routine;

    }

    private List<Alert> createAlerts(Routine routine, @Valid List<@Valid AlertInput> alerts) {
        var response = alerts.stream()
                .map(alertMapper::toEntity)
                .peek(alert -> alert.setRoutine(routine))
                .collect(Collectors.toList());


        for (int i = 0; i < alerts.size(); i++) {
            joinAlertWeatherCodes(response.get(i), alerts.get(i).getCodici());
        }

        return response;
    }

    private void joinAlertWeatherCodes(Alert alert, List<Long> codici) {
        List<WeatherCode> weatherCodes = weatherCodeRepository.findAllByCodeIn(codici);

        alert.setCodici(new HashSet<>());

        for (WeatherCode weatherCode : weatherCodes) {
            AlertWeatherCode alertWeatherCode = new AlertWeatherCode();
            alertWeatherCode.setWeatherCode(weatherCode);
            alertWeatherCode.setAlert(alert);

            AlertWeatherCodePK pk = new AlertWeatherCodePK();
            pk.setCode(weatherCode.getCode());
            pk.setAlertId(alert.getId());
            alertWeatherCode.setId(pk);
            alert.getCodici().add(alertWeatherCode);
        }
    }


    @Override
    public void deleteRoutine(Long id) throws RoutineNonTrovataException {
        Routine routine = routineRepository.findById(id).orElseThrow(() -> new RoutineNonTrovataException("Routine con id " + id + " non trovata"));
        routineRepository.delete(routine);

    }

    @Override
    public List<AlertOutput> getAlertsForRoutine(Long id) {
        Routine routine = routineRepository.findById(id).orElseThrow(() -> new RoutineNonTrovataException("Routine con id " + id + " non trovata"));
        List<Alert> alerts = alertRepository.getAlertByRoutineId(routine.getId());
        return alerts.stream().map(alertMapper::toOutput).toList();
    }

    @Override
    public List<RoutineOutput> getAllRoutines() {

        List<Routine> routines = routineRepository.findAll();
        return routines.stream().map(routineMapper::toOutput).toList();

    }

    @Override
    public RoutineOutput getRoutineById(Long id) throws RoutineNonTrovataException {
        Routine routine = routineRepository.findById(id).orElseThrow(() -> new RoutineNonTrovataException("Routine con id " + id + " non trovata"));
        return routineMapper.toOutput(routine);
    }

}
