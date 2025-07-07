package espe.edu.ec.ms_PatientDataCollector.repository;

import espe.edu.ec.ms_PatientDataCollector.entity.VitalSign;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VitalSignRepository extends JpaRepository<VitalSign, Long> {
    List<VitalSign> findByDeviceId(String deviceId);
}