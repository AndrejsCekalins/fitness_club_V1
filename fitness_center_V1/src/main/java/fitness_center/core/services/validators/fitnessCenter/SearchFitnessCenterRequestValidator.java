package fitness_center.core.services.validators.fitnessCenter;

import fitness_center.core.requests.SearchFitnessCenterRequest;
import fitness_center.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SearchFitnessCenterRequestValidator {

    public List<CoreError> validate (SearchFitnessCenterRequest request) {
        List<CoreError> errors = new ArrayList<>();
        if (isEmpty(request.getFitnessCenter())) {
            errors.add(new CoreError("fitnessCenter", "Must not be empty!"));

        }
        return errors;
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }
}
