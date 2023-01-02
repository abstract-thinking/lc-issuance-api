package com.example.api.domain.commands;

import com.example.api.domain.LCApplicationId;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class SubmitLCApplicationCommand {
    @TargetAggregateIdentifier
    private final LCApplicationId applicationId;

    public SubmitLCApplicationCommand(LCApplicationId applicationId) {
        this.applicationId = applicationId;
    }
}
