package ec.edu.espe.healthanalyzer.repository;

import ec.edu.espe.healthanalyzer.entity.MedicalAlert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalAlertRepository extends JpaRepository<MedicalAlert, String> {
}
