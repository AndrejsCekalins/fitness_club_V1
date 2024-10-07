package fitness_center.core.services.validators.memberCard;

import fitness_center.core.database.jpa.JpaClientRepository;
import fitness_center.core.requests.UpdateMemberCardRequest;
import fitness_center.core.responses.CoreError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
public class UpdateMemberCardRequestValidator {

    @Autowired
    private JpaClientRepository clientRepository;

    public List<CoreError> validate(UpdateMemberCardRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateClientId(request).ifPresent(errors::add);
        validateAgeGroupId(request).ifPresent(errors::add);
        validateWorkoutId(request).ifPresent(errors::add);
        validateFitnessCentreId(request).ifPresent(errors::add);
        validateTermOfContract(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateClientId(UpdateMemberCardRequest request) {
        return request.getNewClient() == null
                ? Optional.of(new CoreError("Id", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateAgeGroupId(UpdateMemberCardRequest request) {
        return request.getNewAgeGroup() == null
                ? Optional.of(new CoreError("Id", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateWorkoutId(UpdateMemberCardRequest request) {
        return request.getNewFitnessCenter() == null
                ? Optional.of(new CoreError("Id", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateFitnessCentreId(UpdateMemberCardRequest request) {
        return request.getNewWorkout() == null
                ? Optional.of(new CoreError("Id", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateTermOfContract(UpdateMemberCardRequest request) {
        return request.getNewTermOfContract() == null
                ? Optional.of(new CoreError("termOfContract", "Must not be empty!"))
                : Optional.empty();
    }
}
