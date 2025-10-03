package tech.aesys.finale.routine.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tech.aesys.finale.routine.dto.request.AlertDtoRequest;
import tech.aesys.finale.routine.dto.response.AlertDtoResponse;
import tech.aesys.finale.routine.model.Alert;


@Mapper(componentModel = "spring")
public interface AlertMapper {

    AlertMapper INSTANCE = Mappers.getMapper(AlertMapper.class);


    AlertDtoResponse toDto (Alert entity);


    Alert toEntity (AlertDtoRequest dto);


    AlertDtoRequest toDtoRequest (Alert entity);


    Alert toEntity (AlertDtoResponse dto);



}
