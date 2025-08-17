package com.ironsoftware.common.events.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.cloud.stream.function.StreamBridge;
import com.ironsoftware.common.events.channels.EcommerceChannels;

@Configuration
public class StreamConfig {
    
    @Bean
    public EcommerceChannels ecommerceChannels() {
        return new EcommerceChannels() {};
    }
}
