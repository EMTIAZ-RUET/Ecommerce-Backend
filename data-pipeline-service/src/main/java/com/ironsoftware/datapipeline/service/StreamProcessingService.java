package com.ironsoftware.datapipeline.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.TimeWindows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@Slf4j
@RequiredArgsConstructor
public class StreamProcessingService {

    private static final String ORDERS_TOPIC = "orders";
    private static final String PRODUCTS_TOPIC = "products";
    private static final String ANALYTICS_TOPIC = "analytics";

    @Autowired
    public void buildPipeline(StreamsBuilder streamsBuilder) {
        // Process order events
        KStream<String, String> orderStream = streamsBuilder.stream(ORDERS_TOPIC);
        KStream<String, String> productStream = streamsBuilder.stream(PRODUCTS_TOPIC);

        // Aggregate orders by time window
        orderStream
            .groupByKey()
            .windowedBy(TimeWindows.of(Duration.ofMinutes(5)))
            .count()
            .toStream()
            .mapValues(value -> String.valueOf(value))
            .to("order-analytics");

        // Join orders with products with time window
        orderStream
            .join(productStream,
                (order, product) -> enrichOrderWithProduct(order, product),
                org.apache.kafka.streams.kstream.JoinWindows.of(Duration.ofMinutes(5)))
            .to(ANALYTICS_TOPIC);

        log.info("Streams pipeline built successfully");
    }

    private String enrichOrderWithProduct(String order, String product) {
        // Implement order enrichment logic
        return order + "-" + product;
    }
}
