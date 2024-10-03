package org.fitness_center.core.services.validators.client;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.fitness_center.core.database.jpa.JpaClientRepository;
import org.fitness_center.core.requests.AddClientRequest;
import org.fitness_center.core.responses.CoreError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@NoArgsConstructor
@AllArgsConstructor
@Component
public class AddClientRequestValidator {
   @Autowired
    private JpaClientRepository clientRepository;

    public List<CoreError> validate(AddClientRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateFirstName(request).ifPresent(errors::add);
        validateLastName(request).ifPresent(errors::add);
        validatePersonalCodeNotEmpty(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateFirstName(AddClientRequest request) {
        return request.getFirstName() == null || request.getFirstName().isEmpty() || !request.getFirstName().matches("[a-zA-Z]+")
                ? Optional.of(new CoreError("firstName", "Must not be empty or contain symbols!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateLastName(AddClientRequest request) {
        return request.getLastName() == null || request.getLastName().isEmpty() || !request.getLastName().matches("[a-zA-Z]+")
                ? Optional.of(new CoreError("lastName", "Must not be empty or contain symbols!"))
                : Optional.empty();
    }

    private Optional<CoreError> validatePersonalCodeNotEmpty(AddClientRequest request) {
        return request.getPersonalCode() == null || request.getPersonalCode().isEmpty()
                ? Optional.of(new CoreError("personalCode", "Must not be empty!"))
                : Optional.empty();
    }
}
