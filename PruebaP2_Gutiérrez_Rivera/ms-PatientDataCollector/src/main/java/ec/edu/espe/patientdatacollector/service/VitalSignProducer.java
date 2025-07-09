package ec.edu.espe.patientdatacollector.service;

import ec.edu.espe.patientdatacollector.config.RabbitMQConfig;
import ec.edu.espe.patientdatacollector.dto.NewVitalSignEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VitalSignProducer {

    private final AmqpTemplate amqpTemplate;
    private final ObjectMapper objectMapper;
    private final List<NewVitalSignEvent> fallbackCache = new ArrayList<>();

    public VitalSignProducer(AmqpTemplate amqpTemplate, ObjectMapper objectMapper) {
        this.amqpTemplate = amqpTemplate;
        this.objectMapper = objectMapper;
    }

    public void publishEvent(NewVitalSignEvent event) {
        try {
            String json = objectMapper.writeValueAsString(event);
            amqpTemplate.convertAndSend(RabbitMQConfig.VITAL_SIGN_QUEUE, json);
        } catch (Exception e) {
            System.out.println("Fallo al enviar evento a RabbitMQ. Guardando en caché.");
            fallbackCache.add(event);
        }
    }

    public void retryCachedEvents() {
        List<NewVitalSignEvent> toRetry = new ArrayList<>(fallbackCache);
        fallbackCache.clear();
        toRetry.forEach(this::publishEvent);
    }

    public boolean hasCached() {
        return !fallbackCache.isEmpty();
    }
}
