package ec.edu.espe.carenotifier.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.Instant;
import java.util.UUID;

@Entity
public class Notification {

    @Id
    private String notificationId;
    private String eventType;
    private String recipient;
    private String status;
    private String priority;
    private Instant timestamp;

    // Constructor vacío (requerido por JPA)
    public Notification() {
    }

    // Getters
    public String getNotificationId() {
        return notificationId;
    }

    public String getEventType() {
        return eventType;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getStatus() {
        return status;
    }

    public String getPriority() {
        return priority;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    // Setters
    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    // Método de fábrica
    public static Notification create(String eventType, String recipient, String priority) {
        Notification n = new Notification();
        n.notificationId = UUID.randomUUID().toString();
        n.eventType = eventType;
        n.recipient = recipient;
        n.priority = priority;
        n.status = "PENDING";
        n.timestamp = Instant.now();
        return n;
    }
}
