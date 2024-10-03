package org.fitness_center.core.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.fitness_center.core.domain.AgeGroup;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetAgeGroupResponse extends CoreResponse {
    private AgeGroup ageGroup;

    public GetAgeGroupResponse(List<CoreError> errors) {
        super(errors);
    }
}
