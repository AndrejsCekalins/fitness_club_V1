package fitness_center.core.services.validators;

import fitness_center.core.database.jpa.JpaClientRepository;
import fitness_center.core.domain.Client;
import fitness_center.core.requests.RemoveClientByPersonalCodeRequest;
import fitness_center.core.responses.CoreError;
import fitness_center.core.services.validators.client.RemoveClientByPersonalCodeRequestValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class RemoveClientByPersonalCodeValidationTest {


    @Mock
    private JpaClientRepository clientRepository;

    @InjectMocks
    private RemoveClientByPersonalCodeRequestValidator validator;

    @Test
    public void shouldReturnErrorWhenClientPersonalCodeIsNull() {
        RemoveClientByPersonalCodeRequest request = new RemoveClientByPersonalCodeRequest(null);
        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 2);
        assertEquals(errors.get(0).getField(), "personalCode");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
        assertEquals(errors.get(1).getField(), "personalCode");
        assertEquals(errors.get(1).getMessage(), "Personal code not exist!");
    }

    @Test
    public void shouldReturnErrorWhenClientPersonaCodeIsEmpty() {
        RemoveClientByPersonalCodeRequest request = new RemoveClientByPersonalCodeRequest("");
        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 2);
        assertEquals(errors.get(0).getField(), "personalCode");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
        assertEquals(errors.get(1).getField(), "personalCode");
        assertEquals(errors.get(1).getMessage(), "Personal code not exist!");
    }

    @Test
    public void shouldNotReturnErrorWhenPersonalCodeIsPresent() {
        clientRepository = Mockito.mock(JpaClientRepository.class);
        validator= new RemoveClientByPersonalCodeRequestValidator(clientRepository);
        Client client = new Client("Andrey", "Pupkin",
                "12-12");
        Mockito.when(clientRepository.findByPersonalCodeLike("12-12")).thenReturn(List.of(client));
        RemoveClientByPersonalCodeRequest request = new RemoveClientByPersonalCodeRequest("12-12");
        List<CoreError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }
}