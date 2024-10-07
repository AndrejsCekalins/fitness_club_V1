package fitness_center.core.services.validators;

import fitness_center.core.database.jpa.JpaClientRepository;
import fitness_center.core.requests.AddClientRequest;
import fitness_center.core.responses.CoreError;
import fitness_center.core.services.AddClientService;
import fitness_center.core.services.validators.client.AddClientRequestValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class AddClientRequestValidatorTest {
    @Mock
    private JpaClientRepository clientRepository;
    @Mock
    private AddClientService service;
    @InjectMocks
    private AddClientRequestValidator validator;

    @Test
    public void shouldReturnErrorWhenFirstNameIsNull() {
        AddClientRequest request = new AddClientRequest(null, "lastName", "personalCode");
        clientRepository = Mockito.mock(JpaClientRepository.class);
        validator = new AddClientRequestValidator(clientRepository);
        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "firstName");
        assertEquals(errors.get(0).getMessage(), "Must not be empty or contain symbols!");
    }

    @Test
    public void shouldReturnErrorWhenFirstNameIsEmpty() {
        clientRepository = Mockito.mock(JpaClientRepository.class);
        validator = new AddClientRequestValidator(clientRepository);
        AddClientRequest request = new AddClientRequest("", "lastName", "personalCode");
        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "firstName");
        assertEquals(errors.get(0).getMessage(), "Must not be empty or contain symbols!");
    }

    @Test
    public void shouldReturnErrorWhenLastNameIsNull() {
        clientRepository = Mockito.mock(JpaClientRepository.class);
        validator = new AddClientRequestValidator(clientRepository);
        AddClientRequest request = new AddClientRequest("firstName", null, "personalCode");
        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "lastName");
        assertEquals(errors.get(0).getMessage(), "Must not be empty or contain symbols!");
    }

    @Test
    public void shouldReturnErrorWhenLastNameIsEmpty() {
        clientRepository = Mockito.mock(JpaClientRepository.class);
        validator = new AddClientRequestValidator(clientRepository);
        AddClientRequest request = new AddClientRequest("firstName", "", "personalCode");
        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "lastName");
        assertEquals(errors.get(0).getMessage(), "Must not be empty or contain symbols!");
    }

    @Test
    public void shouldReturnErrorWhenClientFirstNameIsNumbers() {
        clientRepository = Mockito.mock(JpaClientRepository.class);
        validator = new AddClientRequestValidator(clientRepository);
        AddClientRequest request = new AddClientRequest("5", "lastName", "personalCode");
        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "firstName");
        assertEquals(errors.get(0).getMessage(), "Must not be empty or contain symbols!");
    }

    @Test
    public void shouldReturnErrorWhenClientFirstNameIsSymbol() {
        clientRepository = Mockito.mock(JpaClientRepository.class);
        validator = new AddClientRequestValidator(clientRepository);
        AddClientRequest request = new AddClientRequest("!", "lastName", "personalCode");
        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "firstName");
        assertEquals(errors.get(0).getMessage(), "Must not be empty or contain symbols!");
    }

    @Test
    public void shouldReturnErrorWhenClientLastNameIsNumbers() {
        clientRepository = Mockito.mock(JpaClientRepository.class);
        validator = new AddClientRequestValidator(clientRepository);
        AddClientRequest request = new AddClientRequest("firstName", "5", "personalCode");
        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "lastName");
        assertEquals(errors.get(0).getMessage(), "Must not be empty or contain symbols!");
    }

    @Test
    public void shouldReturnErrorWhenClientLastNameIsSymbol() {
        clientRepository = Mockito.mock(JpaClientRepository.class);
        validator = new AddClientRequestValidator(clientRepository);
        AddClientRequest request = new AddClientRequest("firstName", "!", "personalCode");
        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "lastName");
        assertEquals(errors.get(0).getMessage(), "Must not be empty or contain symbols!");
    }

    @Test
    public void shouldReturnErrorWhenPersonaCodeNull() {
        clientRepository = Mockito.mock(JpaClientRepository.class);
        validator = new AddClientRequestValidator(clientRepository);
        AddClientRequest request = new AddClientRequest("firstName", "lastName", null);
        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "personalCode");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenPersonaCodeIsEmpty() {
        clientRepository = Mockito.mock(JpaClientRepository.class);
        validator = new AddClientRequestValidator(clientRepository);
        AddClientRequest request = new AddClientRequest("firstName", "lastName", "");
        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "personalCode");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldNotReturnErrorWhenAllFieldsArePresent() {
        clientRepository = Mockito.mock(JpaClientRepository.class);
        validator = new AddClientRequestValidator(clientRepository);
        AddClientRequest request = new AddClientRequest("firstName", "lastName", "personalCode");
        List<CoreError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }
}
