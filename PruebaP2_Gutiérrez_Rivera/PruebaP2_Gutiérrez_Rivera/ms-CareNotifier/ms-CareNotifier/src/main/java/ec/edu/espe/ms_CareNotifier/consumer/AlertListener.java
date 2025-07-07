package ec.edu.espe.ms_CareNotifier.consumer;

import ec.edu.espe.ms_CareNotifier.service.NotificationService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AlertListener {

    private final NotificationService notificationService;

    public AlertListener(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @RabbitListener(queues = "alerts.queue")
    public void handleAlert(Map<String, Object> event) {
        notificationService.notifyMedicalStaff(event);
    }
}
