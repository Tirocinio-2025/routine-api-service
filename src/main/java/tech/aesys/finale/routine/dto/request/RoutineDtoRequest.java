package tech.aesys.finale.routine.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.aesys.finale.routine.model.Alert;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class  RoutineDtoRequest {

    private String nome;
    private List<AlertDtoRequest> alerts;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<AlertDtoRequest> getAlerts() {
        return alerts;
    }

    public void setAlerts(List<AlertDtoRequest> alerts) {
        this.alerts = alerts;
    }
}
