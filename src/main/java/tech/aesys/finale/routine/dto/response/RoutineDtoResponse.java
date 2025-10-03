package tech.aesys.finale.routine.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.aesys.finale.routine.model.Alert;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class RoutineDtoResponse {


    private Long id;
    private String nomeRoutine;
    private List<AlertDtoResponse> alerts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeRoutine() {
        return nomeRoutine;
    }

    public void setNomeRoutine(String nomeRoutine) {
        this.nomeRoutine = nomeRoutine;
    }

    public List<AlertDtoResponse> getAlerts() {
        return alerts;
    }

    public void setAlerts(List<AlertDtoResponse> alerts) {
        this.alerts = alerts;
    }
}
