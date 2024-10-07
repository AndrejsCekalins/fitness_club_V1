package fitness_center.core.requests;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchFitnessCenterRequest {

    private String fitnessCenter;

    public boolean isFitnessCenterProvided() {
        return this.fitnessCenter != null;
    }

}
