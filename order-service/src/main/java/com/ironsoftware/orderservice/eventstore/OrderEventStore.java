package com.ironsoftware.orderservice.eventstore;

import com.ironsoftware.orderservice.event.OrderEvent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderEventStore extends MongoRepository<OrderEvent, String> {
    List<OrderEvent> findByOrderIdOrderByVersionAsc(String orderId);
}
