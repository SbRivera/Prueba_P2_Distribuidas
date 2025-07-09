package ec.edu.espe.healthanalyzer.config;

import ec.edu.espe.healthanalyzer.entity.MedicalAlert;
import ec.edu.espe.healthanalyzer.repository.MedicalAlertRepository;
import ec.edu.espe.healthanalyzer.service.MedicalAlertPublisher;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class DailyReportScheduler {

    private final MedicalAlertPublisher publisher;
    private final MedicalAlertRepository repository;

    public DailyReportScheduler(MedicalAlertPublisher publisher, MedicalAlertRepository repository) {
        this.publisher = publisher;
        this.repository = repository;
    }

    @Scheduled(cron = "0 0 0 * * *") // cada día a las 00:00
    public void emitirReporteDiario() {
        MedicalAlert alerta = MedicalAlert.fromEvent(
                "DailyReportGenerated",
                "SYSTEM",
                0.0,
                0.0
        );
        repository.save(alerta);
        publisher.publish(alerta);
        System.out.println("Reporte diario emitido y almacenado");
    }
}
