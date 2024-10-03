package org.fitness_center.core.services.validators.memberCard;

import org.fitness_center.core.requests.GetMemberCardRequest;
import org.fitness_center.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class GetMemberCardRequestValidator {
    public List<CoreError> validate(GetMemberCardRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateMemberCardId(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateMemberCardId(GetMemberCardRequest request) {
        return request.getId() == null
                ? Optional.of(new CoreError("Id", "Must not be empty!"))
                : Optional.empty();
    }
}
