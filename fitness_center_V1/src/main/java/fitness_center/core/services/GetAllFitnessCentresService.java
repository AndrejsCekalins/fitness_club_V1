package fitness_center.core.services;

import fitness_center.core.database.jpa.JpaFitnessCentersRepository;
import fitness_center.core.domain.FitnessCenter;
import fitness_center.core.requests.GetAllFitnessCentersRequest;
import fitness_center.core.responses.GetAllFitnessCentersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class GetAllFitnessCentresService {

    @Autowired
    private JpaFitnessCentersRepository fitnessCentersRepository;

    public GetAllFitnessCentersResponse execute(GetAllFitnessCentersRequest request) {
        List<FitnessCenter> fitnessCenters = fitnessCentersRepository.findAll();
        return new GetAllFitnessCentersResponse(fitnessCenters);
    }
}
