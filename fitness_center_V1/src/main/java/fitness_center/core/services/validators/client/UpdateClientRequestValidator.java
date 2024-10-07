package fitness_center.core.services.validators.client;

import fitness_center.core.requests.UpdateClientRequest;
import fitness_center.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UpdateClientRequestValidator {


    public List<CoreError> validate(UpdateClientRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateFirstName(request).ifPresent(errors::add);
        validateLastName(request).ifPresent(errors::add);
        validatePersonalCode(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateFirstName(UpdateClientRequest request) {
        return request.getNewFirstName() == null || request.getNewFirstName().isEmpty() || !request.getNewFirstName().matches("[a-zA-Z]+")
                ? Optional.of(new CoreError("newFirstName", "Must not be empty or contain symbols!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateLastName(UpdateClientRequest request) {
        return request.getNewLastName() == null || request.getNewLastName().isEmpty() || !request.getNewLastName().matches("[a-zA-Z]+")
                ? Optional.of(new CoreError("newLastName", "Must not be empty or contain symbols!"))
                : Optional.empty();
    }

    private Optional<CoreError> validatePersonalCode(UpdateClientRequest request) {
        return request.getNewPersonalCode() == null || request.getNewPersonalCode().isEmpty()
                ? Optional.of(new CoreError("newPersonalCode", "Must not be empty!"))
                : Optional.empty();
    }
}
