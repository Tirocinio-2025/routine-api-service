package tech.aesys.finale.routine.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import tech.aesys.finale.routine.model.Alert;
import tech.aesys.finale.routine.model.AlertWeatherCode;
import tech.aesys.finale.routine.model.AlertWeatherCodePK;
import tech.aesys.finale.routine.swagger.model.AlertInput;
import tech.aesys.finale.routine.swagger.model.AlertOutput;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface AlertMapper {

    @Mapping(source = "codici", target = "codici", qualifiedByName = "mapLongToAlertWeatherCode")
    @Mapping(source = "tipoMessaggio", target = "testoNotifica")
    Alert toEntity(AlertInput alertInput);

    @Mapping(source = "codici", target = "codici", qualifiedByName = "mapAlertWeatherCodeToLong")
    AlertOutput toOutput(Alert alert);

    @Named("mapAlertWeatherCodeToLong")
    default List<Long> mapAlertWeatherCodeToLong(Set<AlertWeatherCode> codici) {
        if (codici == null) return new ArrayList<>();
        return codici.stream()
                .map(codice -> codice.getId().getCode())
                .collect(Collectors.toList());
    }

    @Named("mapLongToAlertWeatherCode")
    default Set<AlertWeatherCode> mapLongToAlertWeatherCode(List<Long> codici) {
        if (codici == null) return new HashSet<>();
        return codici.stream()
                .map(code -> {
                    AlertWeatherCodePK pk = new AlertWeatherCodePK();
                    pk.setCode(code);
                    pk.setAlertId(null); // Sarà impostato nel service
                    AlertWeatherCode alertWeatherCode = new AlertWeatherCode();
                    alertWeatherCode.setId(pk);
                    return alertWeatherCode;
                })
                .collect(Collectors.toSet());
    }

    default LocalDateTime mapStringToLocalDateTime(String value) {
        return value != null ? LocalDateTime.parse(value) : null;
    }

    default String mapLocalDateTimeToString(LocalDateTime value) {
        return value != null ? value.toString() : null;
    }
}
