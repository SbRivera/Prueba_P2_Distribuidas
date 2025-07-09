package ec.edu.espe.patientdatacollector.repository;

import ec.edu.espe.patientdatacollector.entity.VitalSign;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VitalSignRepository extends JpaRepository<VitalSign, Long> {
    List<VitalSign> findByDeviceId(String deviceId);

}
