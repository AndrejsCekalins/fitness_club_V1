package org.fitness_center.core.responses;

import org.fitness_center.core.domain.FitnessCenter;

import java.util.List;

public class GetAllFitnessCentersResponse extends CoreResponse {

    private List<FitnessCenter> fitnessCenters;

    public GetAllFitnessCentersResponse(List<FitnessCenter> fitnessCenters) {
        this.fitnessCenters = fitnessCenters;
    }

    public List<FitnessCenter> getFitnessCenters() {
        return fitnessCenters;
    }
}
