package fitness_center.core.requests;

import lombok.*;
import fitness_center.core.domain.MemberCard;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class MemberCardRegistrationRequest {

    private MemberCard memberCard;
    private String client;
    private Long ageGroup;
    private Long workout;
    private Long fitnessCenter;
    private String termOfContract;

    public MemberCardRegistrationRequest(String client, Long ageGroup, Long workout, Long fitnessCenter, String termOfContract) {
    }

    public MemberCardRegistrationRequest(MemberCard memberCard) {
    }
}
