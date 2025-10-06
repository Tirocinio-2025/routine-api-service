package tech.aesys.finale.routine.model;

import jakarta.persistence.*;

import java.util.Objects;

@Embeddable
public class AlertWeatherCodePK {

    @Column(name = "alert_id", nullable = false)
    private Long alert_id;
    @Column(name = "code", nullable = false)
    private Long code;

    public AlertWeatherCodePK() {
    }

    public AlertWeatherCodePK(Long id, Long code) {
        this.alert_id = id;
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AlertWeatherCodePK that = (AlertWeatherCodePK) o;
        return Objects.equals(alert_id, that.alert_id) && Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(alert_id, code);
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public Long getAlert_id() {
        return alert_id;
    }

    public void setAlert_id(Long alert_id) {
        this.alert_id = alert_id;
    }
}
