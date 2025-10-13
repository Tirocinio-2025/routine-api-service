package tech.aesys.finale.routine.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.aesys.finale.routine.dto.condition.CurrentCondition;
import tech.aesys.finale.routine.service.CodeService;

import java.util.List;

@RestController()
@RequiredArgsConstructor
public class CurrentConditionController {

    private final CodeService codeService;

    @GetMapping("/api/current-condition")
    public List<CurrentCondition> getAllCurrentConditions() {
        return codeService.getAllCurrentConditions();
    }

}
