package fitness_center.core.services.validators;

import fitness_center.core.requests.Ordering;
import fitness_center.core.requests.Paging;
import fitness_center.core.requests.SearchClientsRequest;
import fitness_center.core.responses.CoreError;
import fitness_center.core.services.validators.client.SearchClientsRequestValidator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class SearchClientRequestValidationTest {

    @InjectMocks
    private SearchClientsRequestValidator validator;
    @Mock
    private SearchClientsRequestValidator  fieldValidator;
    @Mock
    private OrderingValidator orderingValidator;
    @Mock
    private PagingValidator pagingValidator;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldNotReturnErrorsWhenFieldValidatorReturnNoErrors() {
        SearchClientsRequest request = new SearchClientsRequest("FirstName", null);
        when(fieldValidator.validate(request)).thenReturn(List.of());
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }
    @Test
    public void shouldReturnErrorsWhenOrderingValidatorReturnErrors() {
        Ordering ordering = new Ordering(null, "ASCENDING");
        SearchClientsRequest request = new SearchClientsRequest("FirstName", "LastName", ordering);
        CoreError error = new CoreError("orderBy", "Must not be empty!");
        when(orderingValidator.validate(ordering)).thenReturn(List.of(error));
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.getFirst().getField(), "orderBy");
        assertEquals(errors.getFirst().getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldNotInvokeOrderingValidatorWhenNoOrderingObjectPresentInRequest() {
        SearchClientsRequest request = new SearchClientsRequest("FirstName", "LastName");
        validator.validate(request);
        verifyNoMoreInteractions(orderingValidator);
    }

    @Test
    public void shouldNotReturnErrorsWhenPagingValidatorReturnNoErrors() {
        Paging paging = new Paging(5, 5);
        SearchClientsRequest request = new SearchClientsRequest("FirstName", "LastName", paging);
        when(pagingValidator.validate(paging)).thenReturn(List.of());
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldReturnErrorsWhenPagingValidatorReturnErrors() {
        Paging paging = new Paging(null, 5);
        SearchClientsRequest request = new SearchClientsRequest("FirstName", "LastName", paging);
        CoreError error = new CoreError("pageNumber", "Must not be empty!");
        when(pagingValidator.validate(paging)).thenReturn(List.of(error));
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.getFirst().getField(), "pageNumber");
        assertEquals(errors.getFirst().getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldNotInvokePagingValidatorWhenNoPagingObjectPresentInRequest() {
        SearchClientsRequest request = new SearchClientsRequest("FirstName", "LastName");
        validator.validate(request);
        verifyNoMoreInteractions(pagingValidator);
    }
}
