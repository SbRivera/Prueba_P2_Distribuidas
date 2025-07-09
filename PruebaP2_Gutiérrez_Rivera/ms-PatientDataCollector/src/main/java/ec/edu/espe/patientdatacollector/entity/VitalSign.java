package ec.edu.espe.patientdatacollector.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
public class VitalSign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String deviceId;
    private String type;
    private Double value;
    private Instant timestamp;

    // Constructor vacío requerido por JPA
    public VitalSign() {
    }

    // Getters
    public Long getId() {
        return id;
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
    public void setId(Long id) {
        this.id = id;
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
}
