package fitness_center.core.services;

import fitness_center.core.database.jpa.JpaWorkoutsRepository;
import fitness_center.core.requests.GetWorkoutRequest;
import fitness_center.core.responses.CoreError;
import fitness_center.core.responses.GetWorkoutResponse;
import fitness_center.core.services.validators.workout.GetWorkoutRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class GetWorkoutService {

    @Autowired
    private JpaWorkoutsRepository workoutsRepository;

    @Autowired
    private GetWorkoutRequestValidator validator;

    public GetWorkoutResponse execute(GetWorkoutRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new GetWorkoutResponse();
        }
        return workoutsRepository.findById(request.getId())
                .map(GetWorkoutResponse::new)
                .orElseGet(()->{
                    errors.add(new CoreError("id", "Not found!"));
                            return new GetWorkoutResponse(errors);
                        });
    }
}
