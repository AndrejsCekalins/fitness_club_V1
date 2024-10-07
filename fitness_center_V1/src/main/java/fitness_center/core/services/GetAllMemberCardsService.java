package fitness_center.core.services;

import fitness_center.core.database.jpa.JpaMemberCardRepository;
import fitness_center.core.domain.MemberCard;
import fitness_center.core.requests.GetAllMemberCardsRequest;
import fitness_center.core.responses.GetAllMemberCardsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class GetAllMemberCardsService {
    @Autowired
    private JpaMemberCardRepository memberCardRepository;

    public GetAllMemberCardsResponse execute(GetAllMemberCardsRequest request) {
        List<MemberCard> memberCards = memberCardRepository.findAll();
        return new GetAllMemberCardsResponse(memberCards);
    }
}
