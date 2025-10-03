package tech.aesys.finale.routine.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import tech.aesys.finale.routine.dto.request.RoutineDtoRequest;
import tech.aesys.finale.routine.dto.response.AlertDtoResponse;
import tech.aesys.finale.routine.dto.response.RoutineDtoResponse;
import tech.aesys.finale.routine.model.Alert;
import tech.aesys.finale.routine.model.Routine;

@Mapper(componentModel = "spring")
public interface RoutineMapper {

    RoutineMapper INSTANCE = Mappers.getMapper(RoutineMapper.class);


    RoutineDtoRequest toDtoRequest (Routine entity);


    RoutineDtoResponse toDtoResponse (Routine entity);


    Routine toEntity (RoutineDtoResponse dto);


    Routine toEntity (RoutineDtoRequest dto);



//
//    @Mapping(source = "id", target = "id", qualifiedByName = "decryptId")
//    @Named("decryptId")
//    default Long decryptId(String id) {
//        return id != null ? Long.parseLong(MyUtils.decrypt(id)) : null;
//    }



}
