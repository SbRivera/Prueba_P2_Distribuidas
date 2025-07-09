package ec.edu.espe.carenotifier.service;

import ec.edu.espe.carenotifier.entity.Notification;
import ec.edu.espe.carenotifier.repository.NotificationRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {

    private final NotificationRepository repository;
    private final List<Notification> pending = new ArrayList<>();

    public NotificationService(NotificationRepository repository) {
        this.repository = repository;
    }

    public void sendNotification(Notification n) {
        try {
            simulateSend(n);
            repository.save(n);
            System.out.println("Notificación enviada: " + n.getEventType() + " → " + n.getRecipient());
        } catch (Exception e) {
            System.out.println("Error al enviar. Se agrega a pendientes.");
            savePending(n);
        }
    }

    public void savePending(Notification n) {
        pending.add(n);
    }

    @Scheduled(fixedRate = 1800000) // cada 30 minutos
    public void reenviarPendientes() {
        if (pending.isEmpty()) return;

        System.out.println("Reintentando envío de " + pending.size() + " notificaciones pendientes");

        List<Notification> enviadas = new ArrayList<>();

        for (Notification n : pending) {
            boolean success = retrySend(n);
            if (success) {
                repository.save(n);
                enviadas.add(n);
            }
        }

        pending.removeAll(enviadas);
    }

    private boolean retrySend(Notification n) {
        int maxRetries = 3;
        long backoff = 1000; // 1 segundo inicial

        for (int i = 1; i <= maxRetries; i++) {
            try {
                simulateSend(n);
                return true;
            } catch (Exception e) {
                try {
                    Thread.sleep(backoff);
                } catch (InterruptedException ignored) {}
                backoff *= 2;
            }
        }

        System.out.println("Falló el reenvío de " + n.getEventType());
        return false;
    }

    private void simulateSend(Notification n) {
        System.out.println("[NOTIFICACIÓN " + n.getPriority() + "] Evento: " + n.getEventType() + " para dispositivo: " + n.getRecipient());
    }

    public List<Notification> listarTodas() {
        return repository.findAll();
    }
    public void sendBatchLowPriority() {
        if (pending.isEmpty()) return;

        System.out.println("Enviando lote de notificaciones de baja prioridad...");

        List<Notification> enviadas = new ArrayList<>();

        for (Notification n : pending) {
            if (!"EMERGENCY".equalsIgnoreCase(n.getPriority())) {
                boolean success = retrySend(n);
                if (success) {
                    repository.save(n);
                    enviadas.add(n);
                }
            }
        }

        pending.removeAll(enviadas);
    }

}
