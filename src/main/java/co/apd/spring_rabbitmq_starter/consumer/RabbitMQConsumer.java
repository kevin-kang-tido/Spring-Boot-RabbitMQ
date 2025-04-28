package co.apd.spring_rabbitmq_starter.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMQConsumer {


    @RabbitListener(queues ="${spring.rabbitmq.name}") // this annotation will read message base the queues name
    public void consumeMessage(String message) {  // TODO  This Consumer Message is subscribed to Queues by the RabbitListener

        // log get message
        log.info("Here is  the Consuming message: {}", message);

    }



}
