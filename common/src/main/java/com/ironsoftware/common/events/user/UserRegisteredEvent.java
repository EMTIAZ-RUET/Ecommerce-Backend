package com.ironsoftware.common.events.user;

import com.ironsoftware.common.events.BaseEvent;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserRegisteredEvent extends BaseEvent {
    private String userId;
    private String email;
    private String username;
    private boolean emailVerified;
}
