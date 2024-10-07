package fitness_center.core.services.validators.client;

import fitness_center.core.requests.GetClientRequest;
import fitness_center.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class GetClientRequestValidator {
    public List<CoreError> validate(GetClientRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateClientId(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateClientId(GetClientRequest request) {
        return request.getId() == null
                ? Optional.of(new CoreError("Id", "Must not be empty!"))
                : Optional.empty();
    }
}
