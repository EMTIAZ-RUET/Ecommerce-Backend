package com.ironsoftware.common.events.user;

import com.ironsoftware.common.events.BaseEvent;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserActivityEvent extends BaseEvent {
    private String userId;
    private String activityType;
    private String productId;
    private String categoryId;
    private String sessionId;
    private String deviceType;
    private String location;
    private Double rating;
    private Integer duration;
}
