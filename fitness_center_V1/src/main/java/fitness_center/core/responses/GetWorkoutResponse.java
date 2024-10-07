package fitness_center.core.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import fitness_center.core.domain.Workout;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetWorkoutResponse extends CoreResponse {
    private Workout workout;

    public GetWorkoutResponse(List<CoreError> errors) {
        super(errors);
    }
}
