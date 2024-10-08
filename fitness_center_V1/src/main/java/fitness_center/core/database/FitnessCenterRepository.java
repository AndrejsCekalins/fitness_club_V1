package fitness_center.core.database;

import fitness_center.core.domain.FitnessCenter;

import java.util.List;
import java.util.Optional;

public interface FitnessCenterRepository {
    Optional<FitnessCenter> getById(Long id);

    List<FitnessCenter> getAllFitnessCenters();
}
