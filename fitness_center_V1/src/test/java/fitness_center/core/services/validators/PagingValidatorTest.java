package fitness_center.core.services.validators;

import fitness_center.core.requests.Paging;
import fitness_center.core.responses.CoreError;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class PagingValidatorTest {
    private PagingValidator validator = new PagingValidator();

    @Test
    public void shouldReturnErrorWhenPageNumberContainNotValidValue() {
        Paging paging = new Paging(0, 1);
        List<CoreError> errors = validator.validate(paging);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "pageNumber");
        assertEquals(errors.get(0).getMessage(), "Must be greater then 0!");
    }

    @Test
    public void shouldReturnErrorWhenPageSizeContainNotValidValue() {
        Paging paging = new Paging(1, 0);
        List<CoreError> errors = validator.validate(paging);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "pageSize");
        assertEquals(errors.get(0).getMessage(), "Must be greater then 0!");
    }

    @Test
    public void shouldReturnErrorWhenPageNumberAreEmpty() {
        Paging paging = new Paging(null, 1);
        List<CoreError> errors = validator.validate(paging);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "pageNumber");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenPageSizeAreEmpty() {
        Paging paging = new Paging(1, null);
        List<CoreError> errors = validator.validate(paging);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "pageSize");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }

}
