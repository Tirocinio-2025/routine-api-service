package tech.aesys.finale.routine.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.aesys.finale.routine.dto.condition.CurrentCondition;
import tech.aesys.finale.routine.repository.WeatherCodeRepository;


import java.util.List;

import static java.util.stream.Collectors.toList;


@Data
@RequiredArgsConstructor
@Service
public class CodeServiceImpl implements CodeService {

    private final WeatherCodeRepository WeatherCodeRepository;

    @Override
    public List<CurrentCondition> getAllCurrentConditions() {
        List<CurrentCondition> cc = WeatherCodeRepository.findAll().stream()
                .map(wc -> new CurrentCondition(wc.getDayDesc(), wc.getIcon(), wc.getCode()))
                .toList();

        return cc;
    }
}

