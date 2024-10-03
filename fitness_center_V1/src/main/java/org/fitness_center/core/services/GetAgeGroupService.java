package org.fitness_center.core.services;

import org.fitness_center.core.database.jpa.JpaAgeGroupRepository;
import org.fitness_center.core.requests.GetAgeGroupRequest;
import org.fitness_center.core.responses.CoreError;
import org.fitness_center.core.responses.GetAgeGroupResponse;
import org.fitness_center.core.services.validators.ageGroup.GetAgeGroupRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class GetAgeGroupService {

    @Autowired
    private JpaAgeGroupRepository ageGroupRepository;

    @Autowired
    private GetAgeGroupRequestValidator validator;

    public GetAgeGroupResponse execute(GetAgeGroupRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new GetAgeGroupResponse();
        }
        return ageGroupRepository.findById(request.getId())
                .map(GetAgeGroupResponse::new)
                .orElseGet(()->{
                    errors.add(new CoreError("id", "Not found!"));
                            return new GetAgeGroupResponse(errors);
                        });
    }
}
