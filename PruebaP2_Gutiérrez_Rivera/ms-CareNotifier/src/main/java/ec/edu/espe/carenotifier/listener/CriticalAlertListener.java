package ec.edu.espe.carenotifier.listener;

import ec.edu.espe.carenotifier.entity.Notification;
import ec.edu.espe.carenotifier.service.NotificationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class CriticalAlertListener {

    private final NotificationService service;
    private final ObjectMapper objectMapper;

    public CriticalAlertListener(NotificationService service, ObjectMapper objectMapper) {
        this.service = service;
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = "alerts.cola")
    public void procesarEvento(String payload) {
        try {
            var node = objectMapper.readTree(payload);
            String type = node.get("type").asText();
            String deviceId = node.get("deviceId").asText();
            double value = node.get("value").asDouble();

            String priority = "INFO";
            if ("CriticalHeartRateAlert".equals(type)) priority = "EMERGENCY";
            else if ("OxygenLevelCritical".equals(type)) priority = "WARNING";

            Notification n = Notification.create(type, deviceId, priority);

            if ("EMERGENCY".equals(priority)) {
                service.sendNotification(n); // Enviar inmediato
            } else {
                service.savePending(n); // Guardar para lote
            }

        } catch (Exception e) {
            System.err.println("Error procesando evento: " + e.getMessage());
        }
    }
}
