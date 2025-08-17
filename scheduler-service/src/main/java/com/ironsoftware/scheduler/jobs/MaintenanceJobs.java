package com.ironsoftware.scheduler.jobs;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class MaintenanceJobs {

    @Scheduled(cron = "${scheduler.cleanup.cron}")
    @SchedulerLock(name = "cleanupJob", lockAtLeastFor = "5m", lockAtMostFor = "15m")
    public void cleanupJob() {
        log.info("Starting cleanup job");
        // Implement cleanup logic for expired data, sessions, etc.
    }

    @Scheduled(cron = "${scheduler.metrics.cron}")
    @SchedulerLock(name = "metricsAggregationJob", lockAtLeastFor = "10m", lockAtMostFor = "30m")
    public void aggregateMetrics() {
        log.info("Starting metrics aggregation");
        // Implement metrics aggregation logic
    }

    @Scheduled(cron = "${scheduler.report.cron}")
    @SchedulerLock(name = "reportGenerationJob", lockAtLeastFor = "15m", lockAtMostFor = "1h")
    public void generateReports() {
        log.info("Starting report generation");
        // Implement report generation logic
    }
}
