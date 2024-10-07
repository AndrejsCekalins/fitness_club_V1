package fitness_center.core.services.validators.ageGroup;

import fitness_center.core.requests.SearchAgeGroupRequest;
import fitness_center.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SearchAgeGroupRequestValidator {

    public List<CoreError> validate (SearchAgeGroupRequest request) {
        List<CoreError> errors = new ArrayList<>();
        if (isEmpty(request.getAgeGroup())) {
            errors.add(new CoreError("ageGroup", "Must not be empty!"));

        }
        return errors;
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }
}
