package com.example.api.domain.events;

import com.example.api.domain.AdvisingBank;
import com.example.api.domain.LCApplicationId;
import lombok.Data;

@Data
public class LCApplicationCreatedEvent {
    private final LCApplicationId id;
    private AdvisingBank advisingBank;

    public LCApplicationCreatedEvent(LCApplicationId id) {
        this.id = id;
    }

    public LCApplicationCreatedEvent(LCApplicationId id, AdvisingBank advisingBank) {
        this.id = id;
        this.advisingBank = advisingBank;
    }
}

/*
@Data
public class LCApplicationCreatedEvent {

    private LCApplicationId id;
    private ClientId clientId;
    private Party applicant;
    private Party beneficiary;
    private AdvisionBank advisionBank;
    private LocalDate issueDate;
    private MonetaryAmount amount;
    private String merchandiseDescription;

}
*/