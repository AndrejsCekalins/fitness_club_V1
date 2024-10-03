package org.fitness_center.core.services;

import org.fitness_center.core.database.jpa.JpaClientRepository;
import org.fitness_center.core.domain.Client;
import org.fitness_center.core.requests.Ordering;
import org.fitness_center.core.requests.Paging;
import org.fitness_center.core.requests.SearchClientsRequest;
import org.fitness_center.core.responses.CoreError;
import org.fitness_center.core.responses.SearchClientsResponse;
import org.fitness_center.core.services.validators.client.SearchClientsRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional
public class SearchClientsService {
    @Value("${search.ordering.enabled}")
    private boolean orderingEnabled;

    @Value("${search.paging.enabled}")
    private boolean pagingEnabled;

    @Autowired
    private JpaClientRepository clientRepository;
    @Autowired
    private SearchClientsRequestValidator validator;

    public SearchClientsResponse execute(SearchClientsRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new SearchClientsResponse(null, errors);
        }

        List<Client> foundClients = search(request);
        foundClients = order(foundClients, request.getOrdering());
        foundClients = paging(foundClients, request.getPaging());

        return new SearchClientsResponse(foundClients, null);
    }

    private List<Client> search(SearchClientsRequest request) {
        List<Client> foundClients = new ArrayList<>();

        if (request.isFirstNameProvided() && !request.isLastNameProvided() && !request.isPersonalCodeProvided()) {
            foundClients = clientRepository.findByFirstNameLike(request.getFirstName());
        }
        if (!request.isFirstNameProvided() && request.isLastNameProvided() && !request.isPersonalCodeProvided()) {
            foundClients = clientRepository.findByLastNameLike(request.getLastName());
        }
        if (request.isFirstNameProvided() && request.isLastNameProvided() && !request.isPersonalCodeProvided()) {
            foundClients = clientRepository.findByFirstNameAndLastNameLike(request.getFirstName(), request.getLastName());
        }
        if (!request.isFirstNameProvided() && !request.isLastNameProvided() && request.isPersonalCodeProvided()) {
            foundClients = clientRepository.findByPersonalCodeLike(request.getPersonalCode());
        }
        return foundClients;
    }

    private List<Client> order(List<Client> foundClients, Ordering ordering) {
        if (orderingEnabled && (ordering != null)) {
            Comparator<Client> comparator = ordering.getOrderBy().equals("lastName")
                    ? Comparator.comparing(Client::getLastName)
                    : Comparator.comparing(Client::getFirstName);
            if (ordering.getOrderDirection().equals("DESCENDING")) {
                comparator = comparator.reversed();
            }
            return foundClients.stream().sorted(comparator).collect(Collectors.toList());
        } else {
            return foundClients;
        }
    }


    private List<Client> paging(List<Client> clients, Paging paging) {
        if (pagingEnabled && (paging != null)) {
            int skip = (paging.getPageNumber() - 1) * paging.getPageSize();
            return clients.stream()
                    .skip(skip)
                    .limit(paging.getPageSize())
                    .collect(Collectors.toList());
        } else {
            return clients;
        }
    }
}

