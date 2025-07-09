package ec.edu.espe.patientdatacollector.dto;

import java.time.Instant;
import java.util.UUID;

public class NewVitalSignEvent {
    private String eventId;
    private String deviceId;
    private String type;
    private Double value;
    private Instant timestamp;

    // Getters
    public String getEventId() {
        return eventId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getType() {
        return type;
    }

    public Double getValue() {
        return value;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    // Setters
    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    // Método estático para crear un evento con ID generado
    public static NewVitalSignEvent from(String deviceId, String type, Double value, Instant timestamp) {
        NewVitalSignEvent evt = new NewVitalSignEvent();
        evt.setEventId("EVT-" + UUID.randomUUID());
        evt.setDeviceId(deviceId);
        evt.setType(type);
        evt.setValue(value);
        evt.setTimestamp(timestamp);
        return evt;
    }
}
