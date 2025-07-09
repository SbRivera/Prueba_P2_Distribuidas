package ec.edu.espe.patientdatacollector.dto;

public class VitalSignDto {
    private String deviceId;
    private String type;
    private Double value;

    // Getters
    public String getDeviceId() {
        return deviceId;
    }

    public String getType() {
        return type;
    }

    public Double getValue() {
        return value;
    }

    // Setters
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
