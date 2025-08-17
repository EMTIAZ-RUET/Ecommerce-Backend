package com.ironsoftware.loggingservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.Instant;
import java.util.Map;

@Document(indexName = "logs")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogEntry {
    @Id
    private String id;
    
    @Field(type = FieldType.Keyword)
    private String serviceName;
    
    @Field(type = FieldType.Keyword)
    private String environment;
    
    @Enumerated(EnumType.STRING)
    @Field(type = FieldType.Keyword)
    private LogLevel level;
    
    @Field(type = FieldType.Text, analyzer = "standard")
    private String message;
    
    @Field(type = FieldType.Keyword)
    private String logger;
    
    @Field(type = FieldType.Keyword)
    private String thread;
    
    @Field(type = FieldType.Text)
    private String stackTrace;
    
    @Field(type = FieldType.Object)
    private Map<String, Object> context;
    
    @Field(type = FieldType.Keyword)
    private String traceId;
    
    @Field(type = FieldType.Keyword)
    private String spanId;
    
    @CreatedDate
    @Field(type = FieldType.Date)
    private Instant timestamp;
    
    @Field(type = FieldType.Keyword)
    private String host;
    
    @Field(type = FieldType.Keyword)
    private String version;
}
