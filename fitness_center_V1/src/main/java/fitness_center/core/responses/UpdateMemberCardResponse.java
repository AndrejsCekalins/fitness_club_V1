package fitness_center.core.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import fitness_center.core.domain.MemberCard;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMemberCardResponse extends CoreResponse {

    private MemberCard updateMemberCard;

    public UpdateMemberCardResponse(List<CoreError> errors) {
        super(errors);
    }

}
