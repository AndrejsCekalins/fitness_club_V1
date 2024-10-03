package org.fitness_center.core.responses;


import org.fitness_center.core.domain.AgeGroup;

import java.util.List;

public class SearchAgeGroupResponse extends CoreResponse {
    private List<AgeGroup> foundAgeGroups;
    public SearchAgeGroupResponse(List<AgeGroup> foundAgeGroups, List<CoreError> errors) {
        super(errors);
        this.foundAgeGroups = foundAgeGroups;
    }

    public List<AgeGroup> foundAgeGroups() { return foundAgeGroups; }
}
