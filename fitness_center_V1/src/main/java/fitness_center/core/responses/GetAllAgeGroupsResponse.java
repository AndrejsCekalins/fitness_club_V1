package fitness_center.core.responses;

import fitness_center.core.domain.AgeGroup;

import java.util.List;

public class GetAllAgeGroupsResponse extends CoreResponse {

    private List<AgeGroup> ageGroups;

    public GetAllAgeGroupsResponse(List<AgeGroup> ageGroups) {
        this.ageGroups = ageGroups;
    }

    public List<AgeGroup> getAgeGroups() {
        return ageGroups;
    }
}
