package co.apd.spring_rabbitmq_starter.controller;

import co.apd.spring_rabbitmq_starter.publisher.RabbitMQProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/message")
@RequiredArgsConstructor
public class MessageController {

    private final RabbitMQProducer rabbitMQProducer;

    @GetMapping
    public String hello(){
        return "Hello RabbitMQ";
    }

    @GetMapping("/publish")
    public ResponseEntity<?> sendMessage(@RequestParam("message") String message) {

         rabbitMQProducer.sendMessage(message);

        // send message successfully
        return ResponseEntity.ok("Message has been sent successfully");
    }

}
