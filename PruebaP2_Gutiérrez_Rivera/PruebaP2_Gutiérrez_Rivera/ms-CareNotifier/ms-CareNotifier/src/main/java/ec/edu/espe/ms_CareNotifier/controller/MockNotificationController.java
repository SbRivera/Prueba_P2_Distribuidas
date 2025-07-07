package ec.edu.espe.ms_CareNotifier.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/mock")
public class MockNotificationController {

    @PostMapping("/email")
    public String simulateEmail(@RequestBody Map<String, Object> payload) {
        System.out.println("📨 Simulación de email: " + payload);
        return "Email simulado";
    }

    @PostMapping("/sms")
    public String simulateSMS(@RequestBody Map<String, Object> payload) {
        System.out.println("📱 Simulación de SMS: " + payload);
        return "SMS simulado";
    }
}
