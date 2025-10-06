package tech.aesys.finale.routine.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tech.aesys.finale.routine.dto.request.AlertDtoRequest;
import tech.aesys.finale.routine.dto.response.AlertDtoResponse;
import tech.aesys.finale.routine.model.Alert;
import tech.aesys.finale.routine.swagger.model.AlertInput;
import tech.aesys.finale.routine.swagger.model.AlertOutput;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public interface AlertMapper {

    AlertMapper INSTANCE = Mappers.getMapper(AlertMapper.class);

    AlertDtoResponse toDto (Alert entity);

    Alert toEntity (AlertDtoRequest dto);

    AlertDtoRequest toDtoRequest (Alert entity);

    Alert toEntity (AlertDtoResponse dto);

    AlertDtoRequest toDtoRequest (AlertInput input);
    AlertInput toInput( AlertDtoRequest dto);

    AlertOutput toOutput (AlertDtoResponse entity);
    AlertDtoResponse toDtoResponse (AlertOutput output);

    default LocalDateTime mapStringToLocalDateTime(String value) {
        return value != null ? LocalDateTime.parse(value) : null;
    }
    default String mapLocalDateTimeToString(LocalDateTime value) {
        return value != null ? value.toString() : null;
    }
    default Set<String> mapListLongToSetString(List<Long> codici) {
        return codici != null ? codici.stream().map(String::valueOf).collect(Collectors.toSet()) : null;
    }
    default List<Long> mapSetStringToListLong(Set<String> codici) {
        return codici != null ? codici.stream().map(Long::valueOf).collect(Collectors.toList()) : null;
    }
}
