package org.fitness_center.core.services;

import org.fitness_center.core.database.jpa.JpaFitnessCentersRepository;
import org.fitness_center.core.domain.FitnessCenter;
import org.fitness_center.core.requests.SearchFitnessCenterRequest;
import org.fitness_center.core.responses.CoreError;
import org.fitness_center.core.responses.SearchFitnessCenterResponse;
import org.fitness_center.core.services.validators.fitnessCenter.SearchFitnessCenterRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class SearchFitnessCenterService {
    @Value("${search.ordering.enabled}")
    private boolean orderingEnabled;

    @Value("${search.paging.enabled}")
    private boolean pagingEnabled;

    @Autowired
    private JpaFitnessCentersRepository fitnessCentersRepository;
    @Autowired
    private SearchFitnessCenterRequestValidator validator;


    public SearchFitnessCenterResponse execute(SearchFitnessCenterRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new SearchFitnessCenterResponse(null, errors);
        }

        List<FitnessCenter> foundFitnessCenter = search(request);

        return new SearchFitnessCenterResponse(foundFitnessCenter, null);
    }

    private List<FitnessCenter> search(SearchFitnessCenterRequest request) {
        List<FitnessCenter> foundFitnessCenters = new ArrayList<>();
        if (request.isFitnessCenterProvided()) {
            foundFitnessCenters = fitnessCentersRepository.findByFitnessCenter(request.getFitnessCenter());
        }
        return foundFitnessCenters;
    }

}

