package ec.edu.espe.patientdatacollector.config;

import ec.edu.espe.patientdatacollector.service.VitalSignProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class SchedulingConfig {

    @Autowired
    private VitalSignProducer producer;

    private int attempts = 0;

    @Scheduled(fixedDelay = 10000)
    public void reintentarEventosCacheados() {
        if (producer.hasCached() && attempts < 3) {
            System.out.println("Reintentando eventos almacenados localmente. Intento #" + (attempts + 1));
            producer.retryCachedEvents();
            attempts++;
        }
    }
}
