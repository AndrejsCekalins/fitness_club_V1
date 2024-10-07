package fitness_center.core.database.jpa;

import fitness_center.core.domain.FitnessCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaFitnessCentersRepository extends JpaRepository<FitnessCenter, Long> {

    List<FitnessCenter>findByFitnessCenter(String fitnessCenter);

}
