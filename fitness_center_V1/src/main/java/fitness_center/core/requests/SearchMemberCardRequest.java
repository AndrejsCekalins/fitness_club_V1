package fitness_center.core.requests;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchMemberCardRequest {

    private String clientFirstName;
    private String clientLastName;
    private String clientPersonalCode;

    private String ageGroupTitle;

    private String fitnessCenterTitle;

    private String workoutTitle;

}
