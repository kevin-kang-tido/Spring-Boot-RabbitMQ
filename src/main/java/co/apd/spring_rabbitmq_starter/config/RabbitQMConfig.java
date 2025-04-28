package co.apd.spring_rabbitmq_starter.config;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration  // TODO class for configuration RabbitMQ
public class RabbitQMConfig {

    // Get queues from the properties
    @Value("${spring.rabbitmq.name}")
    private String queueName;

    // Get queues from the properties
    @Value("${spring.rabbitmq.json-queue}")
    private String jsonQueueName;

    @Value("${spring.rabbitmq.exchange}")
    private String getExchangeName;

    @Value("${spring.rabbitmq.routing-key}")
    private String routingKey;

    @Value("${spring.rabbitmq.routing-json-key}")
    private String routingJsonKey;

    // spring Bean for Queues RabbitMQ
    @Bean
    public Queue queue() {
        return new  Queue(queueName);
    }

    @Bean
    public Queue jsonQueue() {
        return new  Queue(jsonQueueName);
    }

    // Spring Bean for Rabbit Exchange
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(getExchangeName);
    }
    // binding between queues and top-exchange
    @Bean
    public Binding binding() {
       return BindingBuilder.bind(
               queue())
               .to(topicExchange())
               .with(routingKey);
    }

    // binding between queues and top-exchange
    @Bean
    public Binding bindingJSON() {
        return BindingBuilder.bind(
                        jsonQueue())
                .to(topicExchange())
                .with(routingJsonKey);
    }

    // convert  message  to json
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {

        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());

        return rabbitTemplate;
    }






}
