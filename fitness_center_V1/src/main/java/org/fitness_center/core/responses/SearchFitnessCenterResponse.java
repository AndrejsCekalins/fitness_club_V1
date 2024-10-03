package org.fitness_center.core.responses;

import org.fitness_center.core.domain.FitnessCenter;
import java.util.List;

public class SearchFitnessCenterResponse extends CoreResponse {
    private List<FitnessCenter> foundFitnessCenters;
    public SearchFitnessCenterResponse(List<FitnessCenter> foundFitnessCenters, List<CoreError> errors) {
        super(errors);
        this.foundFitnessCenters = foundFitnessCenters;
    }

    public List<FitnessCenter> foundFitnessCenters() { return foundFitnessCenters; }
}
