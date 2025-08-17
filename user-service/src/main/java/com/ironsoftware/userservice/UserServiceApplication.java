package com.ironsoftware.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;
import com.ironsoftware.common.config.KafkaConfig;
import com.ironsoftware.common.config.KafkaTopicConfig;

@SpringBootApplication
@EnableDiscoveryClient
@Import({KafkaConfig.class, KafkaTopicConfig.class})
public class UserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }
}
