package espe.edu.ec.ms_PatientDataCollector.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "vital_signs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VitalSign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String deviceId;
    private String type;
    private double value;
    private Instant timestamp;
}