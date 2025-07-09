package ec.edu.espe.carenotifier.config;


import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String ALERT_QUEUE = "alerts.cola";

    @Bean
    public Queue alertsQueue() {
        return QueueBuilder.durable(ALERT_QUEUE).build();
    }
}
