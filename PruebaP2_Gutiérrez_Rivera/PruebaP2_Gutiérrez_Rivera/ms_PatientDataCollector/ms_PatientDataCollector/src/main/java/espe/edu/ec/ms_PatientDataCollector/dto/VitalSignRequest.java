package espe.edu.ec.ms_PatientDataCollector.dto;

import lombok.*;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VitalSignRequest {
    private String deviceId;
    private String type;
    private double value;
    private Instant timestamp;
}