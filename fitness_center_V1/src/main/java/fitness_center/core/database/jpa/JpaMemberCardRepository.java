package fitness_center.core.database.jpa;

import fitness_center.core.domain.MemberCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaMemberCardRepository extends JpaRepository<MemberCard, Long> {

}
