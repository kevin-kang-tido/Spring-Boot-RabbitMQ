package co.apd.spring_rabbitmq_starter.publisher;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitMQProducer {

    @Value("${spring.rabbitmq.exchange}")
    private String exchangeName;

    @Value("${spring.rabbitmq.routing-key}")
    private String routingKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQProducer.class);

    // Using Rabbit Template to send the message
    private final  RabbitTemplate rabbitTemplate;

    public void sendMessage(String message) {
        LOGGER.info("Sending message: {}", message);
        rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
    }

}