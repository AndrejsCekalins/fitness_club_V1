package org.fitness_center.core.database.jpa;

import org.fitness_center.core.domain.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaWorkoutsRepository extends JpaRepository<Workout, Long> {

    List<Workout>findByWorkout(String workouts);

}
