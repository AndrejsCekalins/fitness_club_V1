package org.fitness_center.core.services;

import org.fitness_center.core.database.jpa.JpaWorkoutsRepository;
import org.fitness_center.core.domain.Workout;
import org.fitness_center.core.requests.GetAllWorkoutsRequest;
import org.fitness_center.core.responses.GetAllWorkoutsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class GetAllWorkoutsService {

    @Autowired
    private JpaWorkoutsRepository workoutsRepository;

    public GetAllWorkoutsResponse execute(GetAllWorkoutsRequest request) {
        List<Workout> workouts = workoutsRepository.findAll();
        return new GetAllWorkoutsResponse(workouts);
    }
}
