package fitness_center.core.database;

import fitness_center.core.domain.AgeGroup;
import fitness_center.core.domain.FitnessCenter;
import fitness_center.core.domain.MemberCard;
import fitness_center.core.domain.Workout;

import java.util.List;
import java.util.Optional;

public interface MemberCardRepository {

    void save(MemberCard memberCard);

    Optional<MemberCard> getById(Long id);

    List<MemberCard> getAllWorkouts(Workout workout);

    List<MemberCard> getAllFitnessCenters(FitnessCenter fitnessCenter);

    List<MemberCard> getAllAgeGroups(AgeGroup ageGroup);

    boolean isClientWorkoutsChangedById(Long clientId, Long newWorkout);

    boolean isClientAgeGroupChangedById(Long clientId, Long newAgeGroup);

    boolean isClientFitnessCentreChangedById(Long clientId, Long newFitnessCentre);
}
