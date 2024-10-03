package org.fitness_center.core.database;

import org.fitness_center.core.domain.AgeGroup;
import org.fitness_center.core.domain.FitnessCenter;
import org.fitness_center.core.domain.MemberCard;
import org.fitness_center.core.domain.Workout;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

//@Component
//@Transactional
public class MemberCardRepositoryImpl implements MemberCardRepository {

    @Autowired
    private SessionFactory sessionFactory;


    public void save (MemberCard memberCard) {
        sessionFactory.getCurrentSession().save(memberCard);
    }

    @Override
    public Optional<MemberCard> getById(Long id) {
        MemberCard memberCard =sessionFactory.getCurrentSession()
                .get(MemberCard.class, id);
        if (memberCard == null) {
            return Optional.empty();
        }
           return Optional.of(memberCard);
    }


    public List<MemberCard> getAllWorkouts(Workout workout) {
        Query<MemberCard> query = sessionFactory.getCurrentSession()
                .createQuery("SELECT mc FROM MemberCard mc WHERE mc.workout_id = :workout ", MemberCard.class);
        query.setParameter("workout", workout);
        return query.getResultList();
    }
    public List<MemberCard> getAllFitnessCenters(FitnessCenter fitnessCenter) {
        Query<MemberCard> query = sessionFactory.getCurrentSession()
                .createQuery("SELECT mc FROM MemberCard mc WHERE mc.fitness_center_id = :fitness_center", MemberCard.class);
        query.setParameter("fitness_center", fitnessCenter);
        return query.getResultList();
    }

    public List<MemberCard> getAllAgeGroups(AgeGroup ageGroup) {
        Query<MemberCard> query = sessionFactory.getCurrentSession()
                .createQuery("SELECT mc FROM MemberCard mc WHERE mc.age_group_id = :age_group", MemberCard.class);
        query.setParameter("age_group", ageGroup);
        return query.getResultList();
    }

    public boolean isClientWorkoutsChangedById(Long clientId, Long newWorkout) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "UPDATE MemberCard set workout_id = :newWorkout where client_id = :clientId");
        query.setParameter("newWorkout", newWorkout);
        query.setParameter("clientId", clientId);
        int result = query.executeUpdate();
        return result == 1;
    }


    public boolean isClientAgeGroupChangedById(Long clientId, Long newAgeGroup) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "update MemberCard set age_group_id = :newAgeGroup where client_id = :clientId");
        query.setParameter("newAgeGroup", newAgeGroup);
        query.setParameter("clientId", clientId);
        int result = query.executeUpdate();
        return result == 1;
    }


    public boolean isClientFitnessCentreChangedById(Long clientId, Long newFitnessCentre) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "update MemberCard set fitness_center_id = :newFitnessCentre where client_id = :clientId");
        query.setParameter("newFitnessCentre", newFitnessCentre);
        query.setParameter("clientId", clientId);
        int result = query.executeUpdate();
        return result == 1;
    }

}
