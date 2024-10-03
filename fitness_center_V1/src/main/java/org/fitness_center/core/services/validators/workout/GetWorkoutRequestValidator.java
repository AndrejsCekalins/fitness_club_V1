package org.fitness_center.core.services.validators.workout;

import org.fitness_center.core.requests.GetWorkoutRequest;
import org.fitness_center.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class GetWorkoutRequestValidator {
    public List<CoreError> validate(GetWorkoutRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateWorkoutId(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateWorkoutId(GetWorkoutRequest request) {
        return request.getId() == null
                ? Optional.of(new CoreError("Id", "Must not be empty!"))
                : Optional.empty();
    }
}
