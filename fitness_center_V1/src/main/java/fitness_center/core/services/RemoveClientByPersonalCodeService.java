package fitness_center.core.services;

import fitness_center.core.database.jpa.JpaClientRepository;
import fitness_center.core.requests.RemoveClientByPersonalCodeRequest;
import fitness_center.core.responses.CoreError;
import fitness_center.core.responses.RemoveClientByPersonalCodeResponse;
import fitness_center.core.services.validators.client.RemoveClientByPersonalCodeRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class RemoveClientByPersonalCodeService {

    @Autowired
    private JpaClientRepository clientRepository;
    @Autowired
    private RemoveClientByPersonalCodeRequestValidator validator;

    public RemoveClientByPersonalCodeResponse execute(RemoveClientByPersonalCodeRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new RemoveClientByPersonalCodeResponse(errors);
        }
        clientRepository.deleteByPersonalCode(request.getPersonalCode());
        return new RemoveClientByPersonalCodeResponse(true);
    }
}


