package fitness_center.core.services.validators;

import fitness_center.core.requests.Ordering;
import fitness_center.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class OrderingValidator {
    public List<CoreError> validate(Ordering ordering) {
        List<CoreError> errors = new ArrayList<>();
        validateOrderBy(ordering).ifPresent(errors::add);
        validateOrderDirection(ordering).ifPresent(errors::add);
        validateMandatoryOrderBy(ordering).ifPresent(errors::add);
        validateMandatoryOrderDirection(ordering).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateOrderBy(Ordering ordering) {
        return (ordering.getOrderBy() != null
                && !(ordering.getOrderBy().equals("firstName") || ordering.getOrderBy().equals("lastName")))
                ? Optional.of(new CoreError("orderBy", "Must contain 'firstName' or 'lastName' only!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateOrderDirection(Ordering ordering) {
        return (ordering.getOrderDirection() != null
                && !(ordering.getOrderDirection().equals("ASCENDING") || ordering.getOrderDirection().equals("DESCENDING")))
                ? Optional.of(new CoreError("orderDirection", "Must contain 'ASCENDING' or 'DESCENDING' only!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateMandatoryOrderBy(Ordering ordering) {
        return (ordering.getOrderDirection() != null && ordering.getOrderBy() == null)
                ? Optional.of(new CoreError("orderBy", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateMandatoryOrderDirection(Ordering ordering) {
        return (ordering.getOrderBy() != null && ordering.getOrderDirection() == null)
                ? Optional.of(new CoreError("orderDirection", "Must not be empty!"))
                : Optional.empty();
    }

}
