package ec.edu.espe.ms_HealthAnalyzer.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AnalyzerScheduler {

    @Scheduled(cron = "0 0 0 * * *") // Cada 24 horas
    public void generateDailyReport() {
        System.out.println("🔔 DailyReportGenerated emitido (simulado)");
    }

    @Scheduled(cron = "0 0 */6 * * *") // Cada 6 horas
    public void checkInactiveDevices() {
        System.out.println("🚨 DeviceOfflineAlert emitido (simulado)");
    }
}
