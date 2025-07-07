package ec.edu.espe.ms_CareNotifier.repository;

import ec.edu.espe.ms_CareNotifier.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, String> {
}
