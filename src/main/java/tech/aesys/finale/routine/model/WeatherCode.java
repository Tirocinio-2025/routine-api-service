package tech.aesys.finale.routine.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "weather_codes")
public class WeatherCode implements Serializable {

    @Id
    @Column(name = "code", nullable = false)
    private Long code;

    @Column(name = "day")
    private String dayDesc;

    @Column(name = "night")
    private String nightDesc;

    @OneToMany( mappedBy = "weatherCode")
    private Set<AlertWeatherCode> codici;

    public Set<AlertWeatherCode> getCodici() {
        return codici;
    }

    public void setCodici(Set<AlertWeatherCode> codici) {
        this.codici = codici;
    }

    public String getDayDesc() {
        return dayDesc;
    }

    public void setDayDesc(String dayDesc) {
        this.dayDesc = dayDesc;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getNightDesc() {
        return nightDesc;
    }

    public void setNightDesc(String nightDesc) {
        this.nightDesc = nightDesc;
    }
}

