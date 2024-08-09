package com.learning.customers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
        (
        scanBasePackages = {
                "com.learning.customers",
                "com.learning.messaging",
                "com.learning.commonlib"
        }
)
@EnableEurekaClient
@EnableFeignClients(
        basePackages = "com.learning.clients"
)
public class CustomersApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomersApplication.class, args);
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
