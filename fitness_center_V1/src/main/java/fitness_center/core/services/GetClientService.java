package fitness_center.core.services;

import fitness_center.core.database.jpa.JpaClientRepository;
import fitness_center.core.requests.GetClientRequest;
import fitness_center.core.responses.CoreError;
import fitness_center.core.responses.GetClientResponse;
import fitness_center.core.services.validators.client.GetClientRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class GetClientService {

    @Autowired
    private JpaClientRepository clientRepository;

    @Autowired
    private GetClientRequestValidator validator;

    public GetClientResponse execute(GetClientRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new GetClientResponse();
        }
        return clientRepository.findById(request.getId())
                .map(GetClientResponse::new)
                .orElseGet(()->{
                    errors.add(new CoreError("id", "Not found!"));
                            return new GetClientResponse(errors);
                        });
    }
}
