package com.ironsoftware.common.event;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProcessedEventRepository extends MongoRepository<ProcessedEvent, String> {
    boolean existsByEventId(String eventId);
}
