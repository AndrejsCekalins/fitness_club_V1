package org.fitness_center.core.services;

import org.fitness_center.core.database.jpa.*;
import org.fitness_center.core.requests.UpdateMemberCardRequest;
import org.fitness_center.core.responses.CoreError;
import org.fitness_center.core.responses.UpdateMemberCardResponse;
import org.fitness_center.core.services.validators.memberCard.UpdateMemberCardRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Component
@Transactional
public class UpdateMemberCardService {
    @Autowired
    private JpaMemberCardRepository memberCardRepository;
    @Autowired
    private JpaClientRepository clientRepository;
    @Autowired
    private JpaAgeGroupRepository ageGroupRepository;
    @Autowired
    private JpaFitnessCentersRepository fitnessCenterRepository;

    @Autowired
    private JpaWorkoutsRepository workoutRepository;
    @Autowired
    private UpdateMemberCardRequestValidator validator;

    public UpdateMemberCardResponse execute(UpdateMemberCardRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new UpdateMemberCardResponse();
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return memberCardRepository.findById(request.getId())
                .map(memberCard -> {
                    memberCard.setClient(clientRepository.findByPersonalCode(request.getNewClient()));
                    memberCard.setAgeGroup(ageGroupRepository.getReferenceById(request.getNewAgeGroup()));
                    memberCard.setFitnessCenter(fitnessCenterRepository.getReferenceById(request.getNewFitnessCenter()));
                    memberCard.setWorkout(workoutRepository.getReferenceById(request.getNewWorkout()));
                    try {
                        memberCard.setTermOfContract(dateFormat.parse(request.getNewTermOfContract()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return new UpdateMemberCardResponse(memberCard);
                })
                .orElseGet(() -> {
                    errors.add(new CoreError("id", "Not found!"));
                    return new UpdateMemberCardResponse(errors);
                });
    }
}
