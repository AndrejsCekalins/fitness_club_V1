package fitness_center.core.services.validators;

import fitness_center.core.requests.SearchClientsRequest;
import fitness_center.core.responses.CoreError;
import fitness_center.core.services.validators.client.SearchClientsRequestValidator;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class SearchClientRequestFieldValidatorTest {


    private SearchClientsRequestValidator validator = new SearchClientsRequestValidator ();

    @Test
    public void shouldNotReturnErrorsWhenFirstNameIsProvided() {
        SearchClientsRequest request = new SearchClientsRequest("FirstName", null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldNotReturnErrorsWhenLastNameIsProvided() {
        SearchClientsRequest request = new SearchClientsRequest(null, "LastName");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }
    @Test
    public void shouldNotReturnErrorsWhenPersonalCodeIsProvided() {
        SearchClientsRequest request = new SearchClientsRequest(null, "PersonalCode");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldNotReturnErrorsWhenFirstNameAndLastNameIsProvided() {
        SearchClientsRequest request = new SearchClientsRequest("FirstName", "LastName");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldNotReturnErrorsWhenFirstNameLastNamePersonalCodeIsProvided() {
        SearchClientsRequest request = new SearchClientsRequest("FirstName", "LastName", "PersonalCode");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

}
