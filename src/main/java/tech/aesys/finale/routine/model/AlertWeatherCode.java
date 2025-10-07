package tech.aesys.finale.routine.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;


@Entity(name = "alert_weather_codes")

@AllArgsConstructor
public class AlertWeatherCode {

    public  AlertWeatherCode() {}

    @EmbeddedId
    private AlertWeatherCodePK id;

    @ManyToOne
    @MapsId("alertId")
    private Alert alert;

    @ManyToOne
    @MapsId("code")
    private WeatherCode weatherCode;

    public Alert getAlert() {
        return alert;
    }

    public WeatherCode getWeatherCode() {
        return weatherCode;
    }

    public void setId(AlertWeatherCodePK id) {
        this.id = id;
    }

    public void setAlert(Alert alert) {
        this.alert = alert;
    }

    public void setWeatherCode(WeatherCode weatherCode) {
        this.weatherCode = weatherCode;
    }

    public AlertWeatherCodePK getId() {
        return id;
    }

}
