package org.fitness_center.core.database;

import org.fitness_center.core.domain.AgeGroup;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;


//@Component
//@Transactional
public class AgeGroupRepositoryImpl implements AgeGroupRepository{

    @Autowired
    private SessionFactory sessionFactory;

    public Optional<AgeGroup> getById(Long id) {
       AgeGroup ageGroup = sessionFactory.getCurrentSession().get(AgeGroup.class, id);
        if (ageGroup == null) {
            return Optional.empty();
        } else {
            return Optional.of(ageGroup);
        }
    }
    public List<AgeGroup> getAllAgeGroups() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT a  FROM Age_groups a", AgeGroup.class)
                .getResultList();
    }


}
