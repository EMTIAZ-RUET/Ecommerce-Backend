package com.ironsoftware.common.monitoring;

import io.micrometer.core.instrument.Tag;
import lombok.Getter;

import java.util.List;

@Getter
public class CustomMetricsTags {
    private final List<Tag> tags;
    
    public CustomMetricsTags(List<Tag> tags) {
        this.tags = tags;
    }
}