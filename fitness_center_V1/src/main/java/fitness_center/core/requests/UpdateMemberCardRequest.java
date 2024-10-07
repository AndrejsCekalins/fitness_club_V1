package fitness_center.core.requests;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UpdateMemberCardRequest {

    private Long id;
    private String newClient;
    private Long newAgeGroup;
    private Long newWorkout;
    private Long newFitnessCenter;
    private String newTermOfContract;
}
