package ec.edu.espe.healthanalyzer.dto;

import java.time.Instant;
import java.util.UUID;

public class MedicalAlertEvent {

    private String alertId;
    private String type;
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

    // Constructor estático para crear eventos
    public static MedicalAlertEvent create(String type, String deviceId, Double value, Double threshold) {
        MedicalAlertEvent evt = new MedicalAlertEvent();
        evt.setAlertId("ALT-" + UUID.randomUUID());
        evt.setType(type);
        evt.setDeviceId(deviceId);
        evt.setValue(value);
        evt.setThreshold(threshold);
        evt.setTimestamp(Instant.now());
        return evt;
    }
}
