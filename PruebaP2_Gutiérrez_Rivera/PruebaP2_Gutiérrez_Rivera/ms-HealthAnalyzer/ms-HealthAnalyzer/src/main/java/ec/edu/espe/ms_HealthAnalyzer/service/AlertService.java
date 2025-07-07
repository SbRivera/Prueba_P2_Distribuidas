package ec.edu.espe.ms_HealthAnalyzer.service;


import ec.edu.espe.ms_HealthAnalyzer.model.MedicalAlert;
import ec.edu.espe.ms_HealthAnalyzer.repository.MedicalAlertRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@Service
public class AlertService {

    private final MedicalAlertRepository repository;
    private final RabbitTemplate rabbitTemplate;

    public AlertService(MedicalAlertRepository repository, RabbitTemplate rabbitTemplate) {
        this.repository = repository;
        this.rabbitTemplate = rabbitTemplate;
    }

    public void analyzeAndAlert(Map<String, Object> vital) {
        String type = (String) vital.get("type");
        Double value = ((Number) vital.get("value")).doubleValue();
        String deviceId = (String) vital.get("deviceId");
        Instant timestamp = Instant.parse((String) vital.get("timestamp"));

        MedicalAlert alert = null;

        if ("heart-rate".equalsIgnoreCase(type) && (value > 140 || value < 40)) {
            alert = buildAlert("CriticalHeartRateAlert", value, 140.0, deviceId, timestamp);
        } else if ("oxygen".equalsIgnoreCase(type) && value < 90) {
            alert = buildAlert("OxygenLevelCritical", value, 90.0, deviceId, timestamp);
        }

        if (alert != null) {
            repository.save(alert);
            rabbitTemplate.convertAndSend("alerts.exchange", "alerts.critical", alert);
        }
    }

    private MedicalAlert buildAlert(String type, Double value, Double threshold, String deviceId, Instant timestamp) {
        MedicalAlert alert = new MedicalAlert();
        alert.setAlertId("ALT-" + UUID.randomUUID());
        alert.setType(type);
        alert.setDeviceId(deviceId);
        alert.setValue(value);
        alert.setThreshold(threshold);
        alert.setTimestamp(timestamp);
        return alert;
    }
}
