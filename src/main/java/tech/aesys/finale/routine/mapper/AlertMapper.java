package tech.aesys.finale.routine.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import tech.aesys.finale.routine.dto.request.AlertDtoRequest;
import tech.aesys.finale.routine.dto.response.AlertDtoResponse;
import tech.aesys.finale.routine.model.Alert;
import tech.aesys.finale.routine.model.AlertWeatherCode;
import tech.aesys.finale.routine.model.WeatherCode;
import tech.aesys.finale.routine.swagger.model.AlertInput;
import tech.aesys.finale.routine.swagger.model.AlertOutput;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Mapper(componentModel = "spring")
public interface AlertMapper {

    AlertMapper INSTANCE = Mappers.getMapper(AlertMapper.class);

    @Mapping(source = "codici", target = "codici", qualifiedByName = "mapCodiciEntity")
    @Mapping(source = "tipoMessaggio", target = "testoNotifica")
    Alert toEntity (AlertInput alertInput);

    @Mapping(source = "codes", target = "codici")
    Alert toEntity2 (AlertInput alertInput,Set<AlertWeatherCode> codes);

    @Mapping(source = "codici", target = "codici", qualifiedByName = "mapCodici")
    AlertOutput toOutput(Alert alert);

    @Named("mapCodici")
    public static List<Long> mapCodici(Set<AlertWeatherCode> codici) {
        List<Long> ids = new ArrayList<>();
        for (AlertWeatherCode codice : codici) {
            ids.add(codice.getId().getCode());
        }
        return ids;
    }

    @Named("mapCodiciEntity")
    public static Set<AlertWeatherCode> mapCodiciEntity(List<Long> codici) {
        return null;
    }

    default LocalDateTime mapStringToLocalDateTime(String value) {
        return value != null ? LocalDateTime.parse(value) : null;
    }

    default String mapLocalDateTimeToString(LocalDateTime value) {
        return value != null ? value.toString() : null;
    }
}





