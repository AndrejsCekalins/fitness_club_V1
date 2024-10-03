package org.fitness_center.core.database;


import org.fitness_center.core.domain.AgeGroup;

import java.util.List;
import java.util.Optional;

public interface AgeGroupRepository {
    Optional<AgeGroup> getById(Long id);

    List<AgeGroup> getAllAgeGroups();
}
