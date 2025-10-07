package tech.aesys.finale.routine.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import tech.aesys.finale.routine.dto.request.RoutineDtoRequest;
import tech.aesys.finale.routine.dto.response.RoutineDtoResponse;
import tech.aesys.finale.routine.model.Alert;
import tech.aesys.finale.routine.model.AlertWeatherCode;
import tech.aesys.finale.routine.model.Routine;
import tech.aesys.finale.routine.model.WeatherCode;
import tech.aesys.finale.routine.repository.WeatherCodeRepository;
import tech.aesys.finale.routine.swagger.model.RoutineInput;
import tech.aesys.finale.routine.swagger.model.RoutineOutput;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Mapper(uses = AlertMapper.class,componentModel = "spring")
public interface RoutineMapper {

    RoutineMapper INSTANCE = Mappers.getMapper(RoutineMapper.class);


    Routine toEntity(RoutineInput routineInput);

    RoutineOutput toOutput(Routine routine);



}
