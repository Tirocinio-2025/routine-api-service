package tech.aesys.finale.routine.dto.condition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrentCondition {

    private String dayDesc;

    private Integer icon;

    private Long code;
}

