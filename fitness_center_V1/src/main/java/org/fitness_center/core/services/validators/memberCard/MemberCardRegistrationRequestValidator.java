package org.fitness_center.core.services.validators.memberCard;



import org.fitness_center.core.database.jpa.JpaAgeGroupRepository;
import org.fitness_center.core.database.jpa.JpaClientRepository;
import org.fitness_center.core.database.jpa.JpaFitnessCentersRepository;
import org.fitness_center.core.database.jpa.JpaWorkoutsRepository;
import org.fitness_center.core.domain.AgeGroup;
import org.fitness_center.core.domain.Client;
import org.fitness_center.core.domain.FitnessCenter;
import org.fitness_center.core.domain.Workout;
import org.fitness_center.core.requests.MemberCardRegistrationRequest;
import org.fitness_center.core.responses.CoreError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
public class MemberCardRegistrationRequestValidator {

    @Autowired
    private JpaClientRepository clientRepository;

    @Autowired
    private JpaAgeGroupRepository ageGroupRepository;

    @Autowired
    private JpaFitnessCentersRepository fitnessCenterRepository;

    @Autowired
    private JpaWorkoutsRepository workoutRepository;

    public List<CoreError> validate(MemberCardRegistrationRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateClientNullOrEmpty(request).ifPresent(errors::add);
        validateAgeGroupNullOrEmpty(request).ifPresent(errors::add);
        validateWorkoutNullOrEmpty(request).ifPresent(errors::add);
        validateFitnessCentreNullOrEmpty(request).ifPresent(errors::add);
        validateTermOfContractNotEmpty(request).ifPresent(errors::add);
        validateClientPersonalCodeExist(request).ifPresent(errors::add);
        validateAgeGroupIdExistIdInId(request).ifPresent(errors::add);
        validateFitnessCenterIdExistIdInId(request).ifPresent(errors::add);
        validateWorkoutIdExistIdInId(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateClientNullOrEmpty(MemberCardRegistrationRequest request) {
        return request.getClient() == null || request.getClient().isBlank()
                ? Optional.of(new CoreError("personalCode", "Field client personal code must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateAgeGroupNullOrEmpty(MemberCardRegistrationRequest request) {
        return request.getAgeGroup() == null
                ? Optional.of(new CoreError("ageGroupId", "Field age group ID must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateWorkoutNullOrEmpty(MemberCardRegistrationRequest request) {
        return request.getWorkout() == null
                ? Optional.of(new CoreError("workoutId", "Field workout ID must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateFitnessCentreNullOrEmpty(MemberCardRegistrationRequest request) {
        return request.getFitnessCenter() == null
                ? Optional.of(new CoreError("fitnessCenterId", "Field fitness center ID must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateTermOfContractNotEmpty(MemberCardRegistrationRequest request) {
        return request.getTermOfContract() == null
                ? Optional.of(new CoreError("termOfContract", "Field term of contract must not be empty!"))
                : Optional.empty();
    }

   private Optional<CoreError> validateClientPersonalCodeExist(MemberCardRegistrationRequest request) {
        if (request.getClient() != null) {
            Optional<Client> clientOpt = clientRepository.findByPersonalCodeOpt(request.getClient());
            return (clientOpt.isEmpty())
                    ? Optional.of(new CoreError("personalCode", "Not exist!"))
                    : Optional.empty();
        } else {
            return Optional.empty();
        }
   }
    private Optional<CoreError> validateAgeGroupIdExistIdInId(MemberCardRegistrationRequest request) {
        if (request.getAgeGroup() != null) {
            Optional<AgeGroup> ageGroupOpt = ageGroupRepository.findById(request.getAgeGroup());
            return (ageGroupOpt.isEmpty())
                    ? Optional.of(new CoreError("ageGroupId", "Not exist!"))
                    : Optional.empty();
        } else {
            return Optional.empty();
        }
    }

    private Optional<CoreError> validateFitnessCenterIdExistIdInId(MemberCardRegistrationRequest request) {
        if (request.getFitnessCenter() != null) {
            Optional<FitnessCenter> fitnessCenterOpt = fitnessCenterRepository.findById(request.getFitnessCenter());
            return (fitnessCenterOpt.isEmpty())
                    ? Optional.of(new CoreError("fitnessCenterId", "Not exist!"))
                    : Optional.empty();
        } else {
            return Optional.empty();
        }
    }
    private Optional<CoreError> validateWorkoutIdExistIdInId(MemberCardRegistrationRequest request) {
        if (request.getWorkout() != null) {
            Optional<Workout> workoutOpt = workoutRepository.findById(request.getWorkout());
            return (workoutOpt.isEmpty())
                    ? Optional.of(new CoreError("workoutId", "Not exist!"))
                    : Optional.empty();
        } else {
            return Optional.empty();
        }
    }


}
