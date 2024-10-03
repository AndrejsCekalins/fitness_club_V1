package org.fitness_center.core.services.validators.workout;

import org.fitness_center.core.requests.SearchWorkoutRequest;
import org.fitness_center.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SearchWorkoutRequestValidator {

    public List<CoreError> validate (SearchWorkoutRequest request) {
        List<CoreError> errors = new ArrayList<>();
        if (isEmpty(request.getWorkout())) {
            errors.add(new CoreError("workout", "Must not be empty!"));

        }
        return errors;
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }
}
