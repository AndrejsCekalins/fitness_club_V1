package org.fitness_center.core.services;

import org.fitness_center.core.database.jpa.JpaAgeGroupRepository;
import org.fitness_center.core.domain.AgeGroup;
import org.fitness_center.core.requests.GetAllAgeGroupsRequest;
import org.fitness_center.core.responses.GetAllAgeGroupsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class GetAllAgeGroupsService {

    @Autowired
    private JpaAgeGroupRepository ageGroupsRepository;

    public GetAllAgeGroupsResponse execute(GetAllAgeGroupsRequest request) {
        List<AgeGroup> ageGroups = ageGroupsRepository.findAll();
        return new GetAllAgeGroupsResponse(ageGroups);
    }
}
