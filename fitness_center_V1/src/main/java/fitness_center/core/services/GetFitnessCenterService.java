package fitness_center.core.services;

import fitness_center.core.database.jpa.JpaFitnessCentersRepository;
import fitness_center.core.requests.GetFitnessCenterRequest;
import fitness_center.core.responses.CoreError;
import fitness_center.core.responses.GetFitnessCenterResponse;
import fitness_center.core.services.validators.fitnessCenter.GetFitnessCenterRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class GetFitnessCenterService {

    @Autowired
    private JpaFitnessCentersRepository fitnessCentersRepository;

    @Autowired
    private GetFitnessCenterRequestValidator validator;

    public GetFitnessCenterResponse execute(GetFitnessCenterRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new GetFitnessCenterResponse();
        }
        return fitnessCentersRepository.findById(request.getId())
                .map(GetFitnessCenterResponse::new)
                .orElseGet(() -> {
                    errors.add(new CoreError("id", "Not found!"));
                    return new GetFitnessCenterResponse(errors);
                });
    }
}
