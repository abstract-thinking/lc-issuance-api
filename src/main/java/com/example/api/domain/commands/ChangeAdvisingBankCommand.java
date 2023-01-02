package com.example.api.domain.commands;


import com.example.api.domain.AdvisingBank;
import com.example.api.domain.LCApplicationId;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class ChangeAdvisingBankCommand {

    @TargetAggregateIdentifier
    private final LCApplicationId applicationId;

    private final AdvisingBank advisingBank;
}
