package com.example.api.domain;

import com.example.api.domain.commands.CreateLCApplicationCommand;
import com.example.api.domain.events.LCApplicationCreatedEvent;
import org.axonframework.modelling.command.Repository;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static com.example.api.domain.Country.SOKOVIA;
import static com.example.api.domain.Country.WAKANDA;

public class CreateLCApplicationCommandHandlerTests {

    private FixtureConfiguration<LCApplication> fixture;

    @BeforeEach
    void setUp() {
        final Set<Country> sanctioned = Set.of(SOKOVIA);                            // (1)
        fixture = new AggregateTestFixture<>(LCApplication.class);

        final Repository<LCApplication> repository = fixture.getRepository();

        CreateLCApplicationCommandHandler handler =
                new CreateLCApplicationCommandHandler(repository, sanctioned);  // (2)
        fixture.registerAnnotatedCommandHandler(handler);
    }

    @Test
    void shouldFailIfBeneficiaryCountryIsSanctioned() {
        fixture.given()
                .when(new CreateLCApplicationCommand(LCApplicationId.randomId(), SOKOVIA))      // (3)
                .expectNoEvents()
                .expectException(CannotTradeWithSanctionedCountryException.class);
    }

    @Test
    void shouldCreateIfCountryIsNotSanctioned() {
        final LCApplicationId applicationId = LCApplicationId.randomId();
        fixture.given()
                .when(new CreateLCApplicationCommand(applicationId, WAKANDA))   // (4)
                .expectEvents(new LCApplicationCreatedEvent(applicationId));
    }
}
