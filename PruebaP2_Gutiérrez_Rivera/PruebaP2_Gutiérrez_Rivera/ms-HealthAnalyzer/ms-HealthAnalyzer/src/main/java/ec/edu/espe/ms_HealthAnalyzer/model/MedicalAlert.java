package ec.edu.espe.ms_HealthAnalyzer.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "medical_alerts")
public class MedicalAlert {

    @Id
    private String alertId;

    private String type;

    private String deviceId;

    private Double value;

    private Double threshold;

    private Instant timestamp;

    public String getAlertId() {
        return alertId;
    }

    public void setAlertId(String alertId) {
        this.alertId = alertId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Double getThreshold() {
        return threshold;
    }

    public void setThreshold(Double threshold) {
        this.threshold = threshold;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}

