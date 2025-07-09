package ec.edu.espe.healthanalyzer.entity;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "medical_alerts")
public class MedicalAlert {

    @Id
    @Column(name = "alert_id")
    private String alertId;

    private String type;

    @Column(name = "device_id")
    private String deviceId;

    private Double value;

    private Double threshold;

    private Instant timestamp;

    // Getters
    public String getAlertId() {
        return alertId;
    }

    public String getType() {
        return type;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public Double getValue() {
        return value;
    }

    public Double getThreshold() {
        return threshold;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    // Setters
    public void setAlertId(String alertId) {
        this.alertId = alertId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public void setThreshold(Double threshold) {
        this.threshold = threshold;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    // Constructor de alerta a partir de un evento
    public static MedicalAlert fromEvent(String type, String deviceId, Double value, Double threshold) {
        MedicalAlert alert = new MedicalAlert();
        alert.setAlertId("ALT-" + UUID.randomUUID());
        alert.setType(type);
        alert.setDeviceId(deviceId);
        alert.setValue(value);
        alert.setThreshold(threshold);
        alert.setTimestamp(Instant.now());
        return alert;
    }
}
