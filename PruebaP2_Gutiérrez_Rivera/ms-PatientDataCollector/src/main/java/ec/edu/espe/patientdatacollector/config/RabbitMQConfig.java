package ec.edu.espe.patientdatacollector.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;

@Configuration
public class RabbitMQConfig {

    public static final String VITAL_SIGN_QUEUE = "vital.signs.cola";

    @Bean
    public Queue vitalSignsQueue() {
        return QueueBuilder.durable(VITAL_SIGN_QUEUE).build();
    }
}