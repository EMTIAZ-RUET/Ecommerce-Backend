package com.ironsoftware.monitoringservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.Map;

@Entity
@Table(name = "metric_data")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MetricData {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    private String serviceName;
    private String metricName;
    private String metricType;
    private Double value;
    private String unit;
    
    @ElementCollection
    @CollectionTable(name = "metric_tags", joinColumns = @JoinColumn(name = "metric_id"))
    @MapKeyColumn(name = "tag_key")
    @Column(name = "tag_value")
    private Map<String, String> tags;
    
    @CreationTimestamp
    @Column(name = "timestamp")
    private Instant timestamp;
    
    private String source;
    private String environment;
}
