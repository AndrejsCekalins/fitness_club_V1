package fitness_center.core.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import fitness_center.core.domain.MemberCard;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetMemberCardResponse extends CoreResponse {

    private MemberCard memberCard;

    public GetMemberCardResponse(List<CoreError> errors) {
        super(errors);
    }
}
