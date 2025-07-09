package ec.edu.espe.patientdatacollector.service;


import ec.edu.espe.patientdatacollector.dto.NewVitalSignEvent;
import ec.edu.espe.patientdatacollector.dto.VitalSignDto;
import ec.edu.espe.patientdatacollector.entity.VitalSign;
import ec.edu.espe.patientdatacollector.repository.VitalSignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class VitalSignService {

    @Autowired
    private VitalSignRepository vitalSignRepository;

    @Autowired
    private VitalSignProducer producer;

    public void guardarVitalSign(VitalSignDto dto) {
        if (dto.getDeviceId() == null || dto.getDeviceId().isBlank()) {
            throw new IllegalArgumentException("deviceId no puede ser nulo o vacío.");
        }

        if (dto.getType() == null || dto.getType().isBlank()) {
            throw new IllegalArgumentException("type no puede ser nulo o vacío.");
        }

        if (dto.getValue() == null) {
            throw new IllegalArgumentException("value no puede ser nulo.");
        }

        if (dto.getType().equalsIgnoreCase("heart-rate")) {
            if (dto.getValue() < 30 || dto.getValue() > 200) {
                throw new IllegalArgumentException("Frecuencia cardíaca fuera de rango permitido (30–200).");
            }
        }


        VitalSign sign = new VitalSign();
        sign.setDeviceId(dto.getDeviceId());
        sign.setType(dto.getType());
        sign.setValue(dto.getValue());
        sign.setTimestamp(Instant.now());

        vitalSignRepository.save(sign);

        NewVitalSignEvent event = NewVitalSignEvent.from(
                sign.getDeviceId(),
                sign.getType(),
                sign.getValue(),
                sign.getTimestamp()
        );

        producer.publishEvent(event);
    }


    public List<VitalSign> listarPorDispositivo(String deviceId) {
        return vitalSignRepository.findByDeviceId(deviceId);
    }

    public List<VitalSign> listarTodos() {
        return vitalSignRepository.findAll();
    }
}
