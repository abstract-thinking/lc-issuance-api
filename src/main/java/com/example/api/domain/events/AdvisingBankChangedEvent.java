package com.example.api.domain.events;

import com.example.api.domain.AdvisingBank;
import com.example.api.domain.LCApplicationId;
import lombok.Data;

@Data
public class AdvisingBankChangedEvent {
    private final LCApplicationId applicationId;
    private final AdvisingBank advisingBank;

}
