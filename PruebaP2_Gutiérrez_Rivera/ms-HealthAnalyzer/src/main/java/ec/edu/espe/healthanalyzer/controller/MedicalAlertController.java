package ec.edu.espe.healthanalyzer.controller;

import ec.edu.espe.healthanalyzer.entity.MedicalAlert;
import ec.edu.espe.healthanalyzer.repository.MedicalAlertRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/health")
public class MedicalAlertController {

    private final MedicalAlertRepository repository;

    public MedicalAlertController(MedicalAlertRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<MedicalAlert> obtenerAlertas() {
        return repository.findAll();
    }
}
