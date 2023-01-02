package com.example.api.domain;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class AdvisingBank {

    @NotNull
    private final BankId id;

    @NotNull
    private final Country country;
}
