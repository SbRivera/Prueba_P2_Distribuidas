package ec.edu.espe.ms_CareNotifier.service;

import ec.edu.espe.ms_CareNotifier.model.Notification;
import ec.edu.espe.ms_CareNotifier.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@Service
public class NotificationService {

    private final NotificationRepository repository;

    public NotificationService(NotificationRepository repository) {
        this.repository = repository;
    }

    public void notifyMedicalStaff(Map<String, Object> event) {
        String type = (String) event.get("type");

        Notification notif = new Notification();
        notif.setNotificationId("NOTIF-" + UUID.randomUUID());
        notif.setEventType(type);
        notif.setRecipient("medico@example.com"); // simulado
        notif.setTimestamp(Instant.now());

        // Clasificación simple (EMERGENCY / WARNING / INFO)
        String status;
        if (type.contains("Critical")) {
            status = "EMERGENCY";
            System.out.println("📧 Email enviado a médico (simulado)");
        } else {
            status = "INFO";
            System.out.println("🔔 Notificación push (simulada)");
        }

        notif.setStatus(status);
        repository.save(notif);
    }
}
