package com.ironsoftware.reportingservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.Map;

@Entity
@Table(name = "reports")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    private String name;
    private String description;
    
    @Enumerated(EnumType.STRING)
    private ReportType type;
    
    @Enumerated(EnumType.STRING)
    private ReportStatus status;
    
    private String createdBy;
    private String parameters; // JSON string
    private String filePath;
    private String fileName;
    private Long fileSize;
    
    @Enumerated(EnumType.STRING)
    private ReportFormat format;
    
    private Instant scheduledAt;
    private Instant startedAt;
    private Instant completedAt;
    
    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;
    
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;
    
    private String errorMessage;
    private Integer retryCount;
}
