package com.example.api.domain;

import com.example.api.domain.commands.CreateLCApplicationCommand;
import lombok.SneakyThrows;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.Repository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service                                                         // (1)
public class CreateLCApplicationCommandHandler {
    private final Repository<LCApplication> repository;
    private final Set<Country> sanctionedCountries;

    public CreateLCApplicationCommandHandler(Repository<LCApplication> repository,
                                             Set<Country> sanctionedCountries) {
        this.repository = repository;
        this.sanctionedCountries = sanctionedCountries;
    }

    @SneakyThrows
    @CommandHandler
    public void handle(CreateLCApplicationCommand command) {
        // Validations can be performed here as well                (2)
        repository.newInstance(()
                -> new LCApplication(command, sanctionedCountries)); // (3)
    }
}
