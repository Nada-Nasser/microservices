package com.learning.notifications;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(
        scanBasePackages = {
                "com.learning.notifications",
                "com.learning.messaging",
                "com.learning.commonlib"
        }
)
@EnableEurekaClient
public class NotificationsApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationsApplication.class, args);
    }


//    @Bean
//    CommandLineRunner commandLineRunner(
//            RabbitMQMessageProducer rabbitMQMessageProducer,
//            NotificationConfig notificationConfig
//    ){
//        return args -> {
//            rabbitMQMessageProducer.publish(
//                    new Person("Nada", 20),
//                    notificationConfig.getInternalNotificationExchange(),
//                    notificationConfig.getInternalRoutingKey()
//            );
//        };
//    }
//
//    record Person(String name, int age){}
}
