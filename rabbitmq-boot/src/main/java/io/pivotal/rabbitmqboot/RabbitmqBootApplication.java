package io.pivotal.rabbitmqboot;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class RabbitmqBootApplication {

    static final String QUEUE = "spring.queue";

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(RabbitmqBootApplication.class, args);
        RabbitTemplate template = context.getBean(RabbitTemplate.class);
        while (true) {
            template.convertAndSend("", QUEUE, "hello");
            Thread.sleep(1000L);
        }
    }

    @Configuration
    static class AppConfiguration {

        @Bean
        public Queue queue() {
            return QueueBuilder.nonDurable(QUEUE).exclusive().build();
        }
    }

    @Component
    static class AppListener {

        @RabbitListener(queues = QUEUE)
        public void listen(String message) {
            System.out.println("Received message: " + message);
        }
    }
}
