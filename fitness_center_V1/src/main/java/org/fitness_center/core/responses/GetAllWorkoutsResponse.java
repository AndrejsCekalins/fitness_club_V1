package org.fitness_center.core.responses;


import org.fitness_center.core.domain.Workout;

import java.util.List;

public class GetAllWorkoutsResponse extends CoreResponse {

    private List<Workout> workouts;

    public GetAllWorkoutsResponse(List<Workout> workouts) {
        this.workouts = workouts;
    }

    public List<Workout> getWorkouts() {
        return workouts;
    }
}
