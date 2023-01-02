package com.example.api.domain;

public class CannotTradeWithSanctionedCountryException extends DomainException {
    public CannotTradeWithSanctionedCountryException() {
        super("Import from this country is currently prohibited!");
    }
}
