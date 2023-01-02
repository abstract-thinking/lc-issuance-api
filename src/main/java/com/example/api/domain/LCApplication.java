package com.example.api.domain;

import com.example.api.domain.commands.ChangeAdvisingBankCommand;
import com.example.api.domain.commands.CreateLCApplicationCommand;
import com.example.api.domain.commands.SubmitLCApplicationCommand;
import com.example.api.domain.events.AdvisingBankChangedEvent;
import com.example.api.domain.events.LCApplicationCreatedEvent;
import com.example.api.domain.events.LCApplicationSubmittedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;

import java.util.Set;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

public class LCApplication {

    @AggregateIdentifier
    private LCApplicationId id;

    private State state;
    private AdvisingBank advisingBank;

    @SuppressWarnings("unused")
    private LCApplication() {
        // Required by the framework (which Axon?)
    }

    @CommandHandler
    public LCApplication(CreateLCApplicationCommand command) {
        // TODO: perform validations here
        apply(new LCApplicationCreatedEvent(command.getId()));
    }

    public LCApplication(CreateLCApplicationCommand command, Set<Country> sanctioned) {
        if (sanctioned.contains(command.getBeneficiaryCountry())) {
            throw new CannotTradeWithSanctionedCountryException();
        }

        apply(new LCApplicationCreatedEvent(command.getId()));
    }

    @EventSourcingHandler
    private void on(LCApplicationCreatedEvent event) {
        this.id = event.getId();
        this.state = State.DRAFT;
        this.advisingBank = event.getAdvisingBank();
    }

    @CommandHandler
    public void submit(SubmitLCApplicationCommand command) {
        if (this.state != State.DRAFT) {
            throw new AlreadySubmittedException("LC is already submitted!");
        }

        apply(new LCApplicationSubmittedEvent(id));
    }

    @EventSourcingHandler
    private void on(LCApplicationSubmittedEvent event) {
        this.state = State.SUBMITTED;
    }

    @CommandHandler
    public void changeAdvisingBank(ChangeAdvisingBankCommand command) {
        if (!command.getAdvisingBank().equals(this.advisingBank)) {

            apply(new AdvisingBankChangedEvent(id, command.getAdvisingBank()));
        }
    }

    @EventSourcingHandler
    private void on(AdvisingBankChangedEvent event) {
        this.advisingBank = event.getAdvisingBank();
    }

    enum State {
        DRAFT, SUBMITTED, ISSUED
    }
}
