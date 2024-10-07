package fitness_center.core.services.validators;

import fitness_center.core.requests.Ordering;
import fitness_center.core.responses.CoreError;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class OrderingValidatorTest {


    private OrderingValidator validator = new OrderingValidator();

    @Test
    public void shouldReturnErrorWhenOrderDirectionAreEmpty() {
        Ordering ordering = new Ordering("firstName", null);
        List<CoreError> errors = validator.validate(ordering);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "orderDirection");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenOrderByAreEmpty() {
        Ordering ordering = new Ordering(null, "ASCENDING");
        List<CoreError> errors = validator.validate(ordering);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "orderBy");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenOrderByContainNotValidValue() {
        Ordering ordering = new Ordering("notValidValue", "ASCENDING");
        List<CoreError> errors = validator.validate(ordering);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "orderBy");
        assertEquals(errors.get(0).getMessage(), "Must contain 'firstName' or 'lastName' only!");
    }

    @Test
    public void shouldReturnErrorWhenOrderDirectionContainNotValidValue() {
        Ordering ordering = new Ordering("firstName", "notValidValue");
        List<CoreError> errors = validator.validate(ordering);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "orderDirection");
        assertEquals(errors.get(0).getMessage(), "Must contain 'ASCENDING' or 'DESCENDING' only!");
    }
}
