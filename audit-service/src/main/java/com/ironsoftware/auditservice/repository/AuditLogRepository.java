package com.ironsoftware.auditservice.repository;

import com.ironsoftware.auditservice.model.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, String> {
    
    List<AuditLog> findByUserId(String userId);
    
    List<AuditLog> findByAction(String action);
    
    List<AuditLog> findByResourceType(String resourceType);
    
    @Query("SELECT a FROM AuditLog a WHERE a.userId = :userId AND a.action = :action AND a.timestamp BETWEEN :startTime AND :endTime")
    List<AuditLog> findByUserIdAndActionAndTimestampBetween(
        @Param("userId") String userId, 
        @Param("action") String action, 
        @Param("startTime") Instant startTime, 
        @Param("endTime") Instant endTime
    );
    
    @Query("SELECT a FROM AuditLog a WHERE a.resourceType = :resourceType AND a.timestamp BETWEEN :startTime AND :endTime")
    List<AuditLog> findByResourceTypeAndTimestampBetween(
        @Param("resourceType") String resourceType, 
        @Param("startTime") Instant startTime, 
        @Param("endTime") Instant endTime
    );
    
    @Query("SELECT a FROM AuditLog a WHERE a.timestamp < :cutoffDate")
    List<AuditLog> findOldRecords(@Param("cutoffDate") Instant cutoffDate);
}
