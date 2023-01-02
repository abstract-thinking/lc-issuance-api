package com.example.api.domain.events;


import com.example.api.domain.LCApplicationId;

public class LCApplicationSubmittedEvent {
    private final LCApplicationId applicationId;

    public LCApplicationSubmittedEvent(LCApplicationId applicationId) {
        this.applicationId = applicationId;
    }
}