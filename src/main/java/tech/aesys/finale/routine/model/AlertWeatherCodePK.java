package tech.aesys.finale.routine.model;

import jakarta.persistence.*;

import java.util.Objects;

@Embeddable
public class AlertWeatherCodePK {

    @Column(name = "alert_id", nullable = false)
    private Long alertId;
    @Column(name = "code", nullable = false)
    private Long code;

    public AlertWeatherCodePK() {
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AlertWeatherCodePK that = (AlertWeatherCodePK) o;
        return Objects.equals(alertId, that.alertId) && Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(alertId, code);
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public Long getAlertId() {
        return alertId;
    }

    public void setAlertId(Long alertId) {
        this.alertId = alertId;
    }
}
