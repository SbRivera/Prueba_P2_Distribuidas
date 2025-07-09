package ec.edu.espe.carenotifier.repository;

import ec.edu.espe.carenotifier.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, String> {
    List<Notification> findByPriorityAndStatus(String priority, String status);
}
