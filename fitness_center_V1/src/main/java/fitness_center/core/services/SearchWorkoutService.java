package fitness_center.core.services;

import fitness_center.core.database.jpa.JpaWorkoutsRepository;
import fitness_center.core.domain.Workout;
import fitness_center.core.requests.SearchWorkoutRequest;
import fitness_center.core.responses.CoreError;
import fitness_center.core.responses.SearchWorkoutResponse;
import fitness_center.core.services.validators.workout.SearchWorkoutRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class SearchWorkoutService {
    @Value("${search.ordering.enabled}")
    private boolean orderingEnabled;

    @Value("${search.paging.enabled}")
    private boolean pagingEnabled;

    @Autowired
    private JpaWorkoutsRepository workoutsRepository;
    @Autowired
    private SearchWorkoutRequestValidator validator;


    public SearchWorkoutResponse execute(SearchWorkoutRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new SearchWorkoutResponse(null, errors);
        }

        List<Workout> foundWorkout= search(request);

        return new SearchWorkoutResponse(foundWorkout, null);
    }

    private List<Workout> search(SearchWorkoutRequest request) {
        List<Workout> foundWorkouts = new ArrayList<>();
        if (request.isWorkoutProvided()) {
            foundWorkouts = workoutsRepository.findByWorkout(request.getWorkout());
        }
        return foundWorkouts;
    }

}

