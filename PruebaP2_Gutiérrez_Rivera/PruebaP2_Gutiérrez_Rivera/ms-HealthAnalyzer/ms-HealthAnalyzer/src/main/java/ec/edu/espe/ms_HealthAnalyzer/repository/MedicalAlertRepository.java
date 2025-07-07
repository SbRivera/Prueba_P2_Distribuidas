package ec.edu.espe.ms_HealthAnalyzer.repository;


import ec.edu.espe.ms_HealthAnalyzer.model.MedicalAlert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalAlertRepository extends JpaRepository<MedicalAlert, String> {
}
