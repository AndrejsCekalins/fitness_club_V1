package fitness_center.core.services;

import fitness_center.core.database.jpa.JpaClientRepository;
import fitness_center.core.requests.AddClientRequest;
import fitness_center.core.responses.AddClientResponse;
import fitness_center.core.responses.CoreError;
import fitness_center.core.services.validators.client.AddClientRequestValidator;
import fitness_center.matchers.ClientMatcher;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@RunWith(MockitoJUnitRunner.class)
public class AddClientServiceTest {

    @Mock
    private AddClientRequestValidator validator;
    @Mock
    private JpaClientRepository clientRepository;
    @InjectMocks
    private AddClientService service;

    @Test
    public void shouldReturnResponseWithErrorsWhenValidationFails() {
        AddClientRequest notValidRequest = new AddClientRequest(null, "Pupkin",
                "12-12");
        when(validator.validate(notValidRequest)).thenReturn(List.of(new CoreError("firstName",
                "Field first name must not be empty or contain symbols or numbers!")));
        AddClientResponse response = service.execute(notValidRequest);
        assertTrue(response.hasErrors());
    }

    @Test
    public void shouldReturnResponseWithErrorsReceivedFromValidator() {
        AddClientRequest notValidRequest = new AddClientRequest(null, "Pupkin",
                "12-12");
        when(validator.validate(notValidRequest)).thenReturn(List.of(new CoreError("firstName",
                "Field first name must not be empty or contain symbols or numbers!")));
        AddClientResponse response = service.execute(notValidRequest);
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "firstName");
        assertEquals(response.getErrors().get(0).getMessage(), "Field first name must not be empty or contain symbols or numbers!");
    }

    @Test
    public void addClientShouldSuccess_Mockito_style() {
        AddClientRequest request = new AddClientRequest("Andrey", "Pupkin",
                "12-12");
        when(validator.validate(request)).thenReturn(List.of());
        AddClientResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        verify(clientRepository).save(any());
    }

    @Test
    public void addClientShouldFail_Mockito_style() {
        AddClientRequest request = new AddClientRequest("Andrey", "Pupkin",
                "12-12");
        when(validator.validate(request)).thenReturn(List.of(new CoreError("error", "Warning")));
        AddClientResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        Assert.assertEquals(response.getErrors().size(), 1);
    }

    @Test
    public void shouldNotInvokeDatabaseWhenRequestValidationFails() {
        AddClientRequest notValidRequest = new AddClientRequest(null, "Pupkin",
                "12-12");
        when(validator.validate(notValidRequest)).thenReturn(List.of(new CoreError("firstName",
                "Field first name must not be empty or contain symbols or numbers!")));
        service.execute(notValidRequest);
        verifyNoInteractions(clientRepository);
    }

    @Test
    public void shouldAddClientToDatabaseWhenRequestIsValid() {
        AddClientRequest validRequest = new AddClientRequest("Andrey", "Pupkin", "1212");
        when(validator.validate(validRequest)).thenReturn(List.of());
        service.execute(validRequest);
        verify(clientRepository).save(argThat(new ClientMatcher("Andrey", "Pupkin", "1212")));
    }

    @Test
    public void shouldReturnResponseWithoutErrorsWhenRequestIsValid() {
        AddClientRequest validRequest = new AddClientRequest("Andrey", "Pupkin", "1212");
        when(validator.validate(validRequest)).thenReturn(List.of());
        AddClientResponse response = service.execute(validRequest);
        assertFalse(response.hasErrors());
    }

    @Test
    public void shouldReturnResponseWithClientWhenRequestIsValid() {
       AddClientRequest validRequest = new AddClientRequest("Andrey", "Pupkin", "1212");
        when(validator.validate(validRequest)).thenReturn(List.of());
        AddClientResponse response = service.execute(validRequest);
        assertNotNull(response.getNewClient());
        assertEquals(response.getNewClient().getFirstName(), validRequest.getFirstName());
        assertEquals(response.getNewClient().getLastName(), validRequest.getLastName());
        assertEquals(response.getNewClient().getPersonalCode(), validRequest.getPersonalCode());
    }
}



