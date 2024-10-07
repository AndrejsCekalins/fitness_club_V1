package fitness_center.core.database;

import fitness_center.core.domain.Workout;

import java.util.List;
import java.util.Optional;

public interface WorkoutRepository {
    Optional<Workout> getById(Long id);

    List<Workout> getAllWorkouts();
}
