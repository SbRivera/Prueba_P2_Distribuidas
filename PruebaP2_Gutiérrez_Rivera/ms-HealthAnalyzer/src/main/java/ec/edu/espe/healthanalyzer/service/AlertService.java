package ec.edu.espe.healthanalyzer.service;

import ec.edu.espe.healthanalyzer.dto.NewVitalSignEvent;
import ec.edu.espe.healthanalyzer.entity.MedicalAlert;
import ec.edu.espe.healthanalyzer.repository.MedicalAlertRepository;
import org.springframework.stereotype.Service;

@Service
public class AlertService {

    private final MedicalAlertRepository repository;
    private final MedicalAlertPublisher publisher;

    public AlertService(MedicalAlertRepository repository, MedicalAlertPublisher publisher) {
        this.repository = repository;
        this.publisher = publisher;
    }

    public void procesarEvento(NewVitalSignEvent evento) {
        String tipo = evento.getType();
        Double valor = evento.getValue();

        try {
            if ("heart-rate".equalsIgnoreCase(tipo) && (valor > 140 || valor < 40)) {
                Double umbral = valor > 140 ? 140.0 : 40.0;

                MedicalAlert alert = MedicalAlert.fromEvent("CriticalHeartRateAlert", evento.getDeviceId(), valor, umbral);
                repository.save(alert);
                publisher.publish(alert);
            }

            if ("oxygen".equalsIgnoreCase(tipo) && valor < 90) {
                MedicalAlert alert = MedicalAlert.fromEvent("OxygenLevelCritical", evento.getDeviceId(), valor, 90.0);
                repository.save(alert);
                publisher.publish(alert);
            }
        } catch (Exception ex) {
            System.err.println("Error al procesar evento de tipo: " + tipo +
                    " con valor: " + valor +
                    " - Mensaje: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

}
