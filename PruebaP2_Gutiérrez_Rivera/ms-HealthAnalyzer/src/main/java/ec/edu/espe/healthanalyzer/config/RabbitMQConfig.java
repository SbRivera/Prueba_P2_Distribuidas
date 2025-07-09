package ec.edu.espe.healthanalyzer.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String VITAL_SIGN_QUEUE = "vital.signs.cola";
    public static final String ALERT_QUEUE = "alerts.cola";

    @Bean
    public Queue vitalSignsQueue() {
        return QueueBuilder.durable(VITAL_SIGN_QUEUE).build();
    }

    @Bean
    public Queue alertQueue() {
        return QueueBuilder.durable(ALERT_QUEUE).build();
    }
}
