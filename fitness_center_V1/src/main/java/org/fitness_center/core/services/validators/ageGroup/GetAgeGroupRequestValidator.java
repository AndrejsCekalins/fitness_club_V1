package org.fitness_center.core.services.validators.ageGroup;

import org.fitness_center.core.requests.GetAgeGroupRequest;
import org.fitness_center.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class GetAgeGroupRequestValidator {
    public List<CoreError> validate(GetAgeGroupRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateAgeGroupId(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateAgeGroupId(GetAgeGroupRequest request) {
        return request.getId() == null
                ? Optional.of(new CoreError("Id", "Must not be empty!"))
                : Optional.empty();
    }
}
