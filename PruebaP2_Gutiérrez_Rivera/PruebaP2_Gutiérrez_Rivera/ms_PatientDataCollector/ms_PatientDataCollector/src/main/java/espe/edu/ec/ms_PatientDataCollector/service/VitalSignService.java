package espe.edu.ec.ms_PatientDataCollector.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import espe.edu.ec.ms_PatientDataCollector.dto.VitalSignRequest;
import espe.edu.ec.ms_PatientDataCollector.entity.VitalSign;
import espe.edu.ec.ms_PatientDataCollector.repository.VitalSignRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class VitalSignService {

    private final VitalSignRepository repository;
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${app.rabbitmq.exchange}")
    private String exchange;

    @Value("${app.rabbitmq.routingkey}")
    private String routingKey;

    public void saveVitalSign(VitalSignRequest request) {
        // Validaciones
        if (request.getType().equals("heart-rate") && (request.getValue() < 30 || request.getValue() > 200)) {
            throw new IllegalArgumentException("Heart rate fuera de rango.");
        }

        VitalSign vitalSign = VitalSign.builder()
                .deviceId(request.getDeviceId())
                .type(request.getType())
                .value(request.getValue())
                .timestamp(request.getTimestamp() == null ? Instant.now() : request.getTimestamp())
                .build();

        repository.save(vitalSign);
        sendEvent(vitalSign);
    }

    private void sendEvent(VitalSign vitalSign) {
        try {
            String eventJson = objectMapper.writeValueAsString(vitalSign);
            rabbitTemplate.convertAndSend(exchange, routingKey, eventJson);
        } catch (JsonProcessingException e) {
            // Aquí podrías guardar en SQLite o memoria si RabbitMQ está caído
            System.err.println("Error al enviar evento. Guardar en almacenamiento temporal.");
        }
    }
}
