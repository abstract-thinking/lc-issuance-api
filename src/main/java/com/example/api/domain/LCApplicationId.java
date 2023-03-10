package com.example.api.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class LCApplicationId {
    private final UUID id;

    public static LCApplicationId randomId() {
        return new LCApplicationId(UUID.randomUUID());
    }
}
