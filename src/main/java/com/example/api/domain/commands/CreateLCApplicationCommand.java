package com.example.api.domain.commands;

import com.example.api.domain.Country;
import com.example.api.domain.LCApplicationId;
import lombok.Data;

@Data
public class CreateLCApplicationCommand {
    private LCApplicationId id;
    private Country beneficiaryCountry;

    public CreateLCApplicationCommand() {
        this.id = LCApplicationId.randomId();
    }

    public CreateLCApplicationCommand(LCApplicationId id, Country beneficiaryCountry) {
        this.id = id;
        this.beneficiaryCountry = beneficiaryCountry;
    }
}

/*
@Data
public class CreateLCApplicationCommand {

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