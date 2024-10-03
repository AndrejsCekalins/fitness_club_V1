package org.fitness_center.core.responses;

import lombok.Getter;
import org.fitness_center.core.domain.MemberCard;

import java.util.List;

@Getter
public class GetAllMemberCardsResponse extends CoreResponse {

    private List<MemberCard> memberCards;

    public GetAllMemberCardsResponse(List<MemberCard> memberCards) {
        this.memberCards = memberCards;
    }

}