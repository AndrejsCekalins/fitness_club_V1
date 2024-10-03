package org.fitness_center.core.services;

import org.fitness_center.core.database.jpa.JpaClientRepository;
import org.fitness_center.core.domain.Client;
import org.fitness_center.core.requests.GetAllClientsRequest;
import org.fitness_center.core.responses.GetAllClientsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class GetAllClientsService {

   @Autowired
   private JpaClientRepository clientRepository;

    public GetAllClientsResponse execute(GetAllClientsRequest request) {
        List<Client> clients = clientRepository.findAll();
        return new GetAllClientsResponse(clients);
    }
}
