package ec.edu.espe.healthanalyzer.listener;

import ec.edu.espe.healthanalyzer.dto.NewVitalSignEvent;
import ec.edu.espe.healthanalyzer.service.AlertService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VitalSignListener {

    @Autowired
    private AlertService alertService;

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = "vital.signs.cola")
    public void recibirEvento(String mensaje) {
        try {
            NewVitalSignEvent evento = objectMapper.readValue(mensaje, NewVitalSignEvent.class);
            alertService.procesarEvento(evento);
            System.out.println("Evento recibido: " + evento);
        } catch (Exception e) {
            System.err.println("Error al procesar evento: " + e.getMessage());
        }
    }
}
