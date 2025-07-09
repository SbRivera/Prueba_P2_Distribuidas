package ec.edu.espe.carenotifier.scheduler;

import ec.edu.espe.carenotifier.service.NotificationService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BatchNotificationScheduler {

    private final NotificationService service;

    public BatchNotificationScheduler(NotificationService service) {
        this.service = service;
    }

    @Scheduled(cron = "0 */30 * * * *")
    public void enviarLotesBajaPrioridad() {
        service.sendBatchLowPriority();
    }
}
