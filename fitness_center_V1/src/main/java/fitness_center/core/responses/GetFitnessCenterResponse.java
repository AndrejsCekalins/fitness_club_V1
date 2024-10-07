package fitness_center.core.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import fitness_center.core.domain.FitnessCenter;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetFitnessCenterResponse extends CoreResponse {
    private FitnessCenter fitnessCenter;

    public GetFitnessCenterResponse(List<CoreError> errors) {
        super(errors);
    }
}
