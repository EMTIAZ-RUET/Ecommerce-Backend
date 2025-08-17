package com.ironsoftware.backupservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Table(name = "backup_jobs")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BackupJob {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    private String name;
    private String description;
    
    @Enumerated(EnumType.STRING)
    private BackupType type;
    
    @Enumerated(EnumType.STRING)
    private BackupStatus status;
    
    private String sourceLocation;
    private String destinationLocation;
    private String cronExpression;
    
    private Long totalSize;
    private Long compressedSize;
    private Integer fileCount;
    
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
    private String checksum;
}
