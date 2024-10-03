package org.fitness_center.core.services;

import org.fitness_center.core.database.jpa.JpaClientRepository;
import org.fitness_center.core.requests.UpdateClientRequest;
import org.fitness_center.core.responses.CoreError;
import org.fitness_center.core.responses.UpdateClientResponse;
import org.fitness_center.core.services.validators.client.UpdateClientRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class UpdateClientService {
    @Autowired
    private JpaClientRepository clientRepository;

    @Autowired
    private UpdateClientRequestValidator validator;

    public UpdateClientResponse execute(UpdateClientRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new UpdateClientResponse(errors);
        }
        return clientRepository.findById(request.getId())
                .map(client -> {
                    client.setFirstName(request.getNewFirstName());
                    client.setLastName(request.getNewLastName());
                    client.setPersonalCode(request.getNewPersonalCode());
                    return new UpdateClientResponse(client);
                })
                .orElseGet(() -> {
                    errors.add(new CoreError("id", "Not found!"));
                    return new UpdateClientResponse(errors);
                });
    }
}
