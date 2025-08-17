package com.ironsoftware.common.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "app.security.jwt")
public class JwtProperties {
    private String secret;
    private long expirationTime;
    private String tokenPrefix = "Bearer ";
    private String headerString = "Authorization";
    private String authorities = "authorities";
}
