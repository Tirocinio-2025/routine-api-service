package tech.aesys.finale.routine.service;


import org.springframework.stereotype.Service;
import tech.aesys.finale.routine.dto.condition.CurrentCondition;

import java.util.List;


@Service
public interface CodeService {
    List<CurrentCondition> getAllCurrentConditions();
}
