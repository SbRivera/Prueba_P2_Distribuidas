package ec.edu.espe.healthanalyzer.dto;

public class NewVitalSignEvent {

    private String eventId;
    private String deviceId;
    private String type;
    private Double value;
    private String timestamp;

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

    public String getTimestamp() {
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

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
