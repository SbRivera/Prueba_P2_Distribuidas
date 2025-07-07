package ec.edu.espe.ms_HealthAnalyzer.consumer;

import ec.edu.espe.ms_HealthAnalyzer.service.AlertService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class VitalSignListener {

    private final AlertService alertService;

    public VitalSignListener(AlertService alertService) {
        this.alertService = alertService;
    }

    @RabbitListener(queues = "vital-signs.queue")
    public void receiveNewVitalSign(Map<String, Object> vitalEvent) {
        alertService.analyzeAndAlert(vitalEvent);
    }
}
