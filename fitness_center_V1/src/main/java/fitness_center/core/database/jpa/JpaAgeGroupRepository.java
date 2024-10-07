package fitness_center.core.database.jpa;

import fitness_center.core.domain.AgeGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface JpaAgeGroupRepository extends JpaRepository<AgeGroup, Long> {

    List<AgeGroup>findByAgeGroup(String ageGroup);

}
