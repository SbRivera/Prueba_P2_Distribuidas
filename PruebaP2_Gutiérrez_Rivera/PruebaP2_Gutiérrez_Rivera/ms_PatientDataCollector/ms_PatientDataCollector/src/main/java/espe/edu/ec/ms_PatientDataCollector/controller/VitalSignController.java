package espe.edu.ec.ms_PatientDataCollector.controller;


import espe.edu.ec.ms_PatientDataCollector.dto.VitalSignRequest;
import espe.edu.ec.ms_PatientDataCollector.entity.VitalSign;
import espe.edu.ec.ms_PatientDataCollector.repository.VitalSignRepository;
import espe.edu.ec.ms_PatientDataCollector.service.VitalSignService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conjunta/2p/vital-signs")
@RequiredArgsConstructor
public class VitalSignController {

    private final VitalSignService service;
    private final VitalSignRepository repository;

    @PostMapping
    public void postVitalSign(@RequestBody VitalSignRequest request) {
        service.saveVitalSign(request);
    }

    @GetMapping("/{deviceId}")
    public List<VitalSign> getByDeviceId(@PathVariable String deviceId) {
        return repository.findByDeviceId(deviceId);
    }
}
