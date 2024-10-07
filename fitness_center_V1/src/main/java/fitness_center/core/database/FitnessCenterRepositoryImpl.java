package fitness_center.core.database;

import fitness_center.core.domain.FitnessCenter;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;


//@Component
//@Transactional
public class FitnessCenterRepositoryImpl {

    @Autowired
    private SessionFactory sessionFactory;

    public List<FitnessCenter> getAllFitnessCenters() {
        return  sessionFactory.getCurrentSession()
                .createQuery("SELECT fc FROM Fitness_centres fc", FitnessCenter.class)
                .getResultList();
    }

    public Optional<FitnessCenter> getById(Long id) {
        FitnessCenter fitnessCenter = sessionFactory.getCurrentSession().get(FitnessCenter.class, id);
        if (fitnessCenter == null) {
            return Optional.empty();
        } else {
            return Optional.of(fitnessCenter);
        }
    }
}
