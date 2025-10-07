package tech.aesys.finale.routine.service;


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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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
        Routine routine = routineMapper.toEntity(routineInput);
        routine.setAlerts(new ArrayList<>());
        routine = routineRepository.save(routine);

        List<Alert> alertList = new ArrayList<>();

        for (AlertInput alert : routineInput.getAlerts()) {

            List<WeatherCode> weatherCodes = weatherCodeRepository.findAllByCodeIn(alert.getCodici());


            Alert alertEntity = alertMapper.toEntity(alert);
            alertEntity.setRoutine(routine);

            alertEntity.setCodici(new HashSet<>());

            alertRepository.save(alertEntity);

            for (WeatherCode weatherCode : weatherCodes) {
                AlertWeatherCode alertWeatherCode = new AlertWeatherCode();
                alertWeatherCode.setWeatherCode(weatherCode);
                alertWeatherCode.setAlert(alertEntity);
                alertWeatherCode = alertWeatherCodeRepository.save(alertWeatherCode);
                alertEntity.getCodici().add(alertWeatherCode);
            }
            alertList.add(alertEntity);
        }
        routine.setAlerts(alertList);
        routine = routineRepository.save(routine);

        return routineMapper.toOutput(routine);

    }

    @Override
    public void deleteRoutine(Long id) {
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
    public RoutineOutput getRoutineById(Long id) {
        Routine routine = routineRepository.findById(id).orElseThrow(() -> new RoutineNonTrovataException("Routine con id " + id + " non trovata"));
        return routineMapper.toOutput(routine);
    }

    @Override
    public RoutineOutput patchRoutine(Long id, RoutineInput routineInput) {

        List<Alert> list = new ArrayList<>();
        for (AlertInput alert : routineInput.getAlerts()) {
            list.add(alertMapper.toEntity(alert));
        }
        Routine routine = routineRepository.findById(id).orElseThrow(() -> new RoutineNonTrovataException("Routine con id " + id + " non trovata"));
        routine = routineMapper.toEntity(routineInput);
        routine.setAlerts(list);
        routineRepository.saveAndFlush(routine);
        return routineMapper.toOutput(routine);

    }

}
