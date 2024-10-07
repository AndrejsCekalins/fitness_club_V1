package fitness_center.core.services;

import fitness_center.core.database.jpa.JpaClientRepository;
import fitness_center.core.domain.Client;
import fitness_center.core.requests.Ordering;
import fitness_center.core.requests.Paging;
import fitness_center.core.requests.SearchClientsRequest;
import fitness_center.core.responses.CoreError;
import fitness_center.core.responses.SearchClientsResponse;
import fitness_center.core.services.validators.client.SearchClientsRequestValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;


@RunWith(MockitoJUnitRunner.class)
public class SearchClientServiceTest {
    @Mock
    private JpaClientRepository clientRepository;

    @Mock
    private SearchClientsRequestValidator validator;

    @InjectMocks
    private SearchClientsService service;

    @Before
    public void setup() {
        ReflectionTestUtils.setField(service, "orderingEnabled", true);
        ReflectionTestUtils.setField(service, "pagingEnabled", true);
    }

    @Test
    public void shouldReturnResponseWithErrorsWhenValidatorFails() {
        SearchClientsRequest request = new SearchClientsRequest(null, null, "");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("firstName", "Must not be empty!"));
        errors.add(new CoreError("lastName", "Must not be empty!"));
        errors.add(new CoreError("personalCode", "Must not be empty!"));

        Mockito.when(validator.validate(request)).thenReturn(errors);

        SearchClientsResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 3);

        Mockito.verify(validator).validate(request);
        Mockito.verify(validator).validate(any());
        Mockito.verifyNoInteractions(clientRepository);
    }
    @Test
    public void shouldSearchByFirstName() {

        SearchClientsRequest request = new SearchClientsRequest("Dmitry", null,"");
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Client> clients = new ArrayList<>();
        clients.add(new Client("Dmitry", "Petrov", "1234"));

        Mockito.when(clientRepository.findByFirstNameLike("Dmitry")).thenReturn(clients);

        SearchClientsResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getFoundClients().size(), 1);
        assertEquals(response.getFoundClients().get(0).getFirstName(), "Dmitry");
        assertEquals(response.getFoundClients().get(0).getLastName(), "Petrov");
        assertEquals(response.getFoundClients().get(0).getPersonalCode(), "1234");
    }

    @Test
    public void shouldSearchByLastName() {

        SearchClientsRequest request = new SearchClientsRequest(null, "Petrov", "");

        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Client> clients = new ArrayList<>();
        clients.add(new Client("Ivan", "Petrov", "1234"));

        Mockito.when(clientRepository.findByLastNameLike("Petrov")).thenReturn(clients);

        SearchClientsResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getFoundClients().size(), 1);
        assertEquals(response.getFoundClients().get(0).getFirstName(), "Ivan");
        assertEquals(response.getFoundClients().get(0).getLastName(), "Petrov");
        assertEquals(response.getFoundClients().get(0).getPersonalCode(), "1234");
    }

    @Test
    public void shouldSearchByPersonalCode() {

        SearchClientsRequest request = new SearchClientsRequest(null, null, "1234");

        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Client> clients = new ArrayList<>();
        clients.add(new Client("Ivan", "Petrov", "1234"));

        Mockito.when(clientRepository.findByPersonalCodeLike("1234")).thenReturn(clients);

        SearchClientsResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getFoundClients().size(), 1);
        assertEquals(response.getFoundClients().get(0).getFirstName(), "Ivan");
        assertEquals(response.getFoundClients().get(0).getLastName(), "Petrov");
        assertEquals(response.getFoundClients().get(0).getPersonalCode(), "1234");
    }

    @Test
    public void shouldSearchByFirstNameAndLastName() {

        SearchClientsRequest request = new SearchClientsRequest("Ivan", "Petrov", "");

        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Client> clients = new ArrayList<>();
        clients.add(new Client("Ivan", "Petrov", "1234"));

        Mockito.when(clientRepository.findByFirstNameAndLastNameLike("Ivan", "Petrov")).thenReturn(clients);

        SearchClientsResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getFoundClients().size(), 1);
        assertEquals(response.getFoundClients().get(0).getFirstName(), "Ivan");
        assertEquals(response.getFoundClients().get(0).getLastName(), "Petrov");
        assertEquals(response.getFoundClients().get(0).getPersonalCode(), "1234");
    }

    @Test
    public void shouldSearchByFirstNameWithOrderingAscending() {
        Ordering ordering = new Ordering("lastName", "ASCENDING");
        SearchClientsRequest request = new SearchClientsRequest("Dmitry", null, ordering);

        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Client> clients = new ArrayList<>();
        clients.add(new Client("Dmitry", "Arbuzov", "111"));
        clients.add(new Client("Dmitry", "Bananov", "112"));

        Mockito.when(clientRepository.findByFirstNameLike("Dmitry")).thenReturn(clients);

        SearchClientsResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getFoundClients().size(), 2);
        assertEquals(response.getFoundClients().get(0).getLastName(), "Arbuzov");
        assertEquals(response.getFoundClients().get(1).getLastName(), "Bananov");

    }

    @Test
    public void shouldSearchByFirstNameWithOrderingDescending() {
        Ordering ordering = new Ordering("lastName", "DESCENDING");
        SearchClientsRequest request = new SearchClientsRequest("Dmitry", null, ordering);

        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Client> clients = new ArrayList<>();
        clients.add(new Client("Dmitry", "Arbuzov", "111"));
        clients.add(new Client("Dmitry", "Bananov", "112"));

        Mockito.when(clientRepository.findByFirstNameLike("Dmitry")).thenReturn(clients);

        SearchClientsResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getFoundClients().size(), 2);
        assertEquals(response.getFoundClients().get(0).getLastName(), "Bananov");
        assertEquals(response.getFoundClients().get(1).getLastName(), "Arbuzov");
    }

    @Test
    public void shouldSearchByFirstNameWithPagingFirstPage() {
        Paging paging = new Paging(1, 1);
        SearchClientsRequest request = new SearchClientsRequest("Dmitry", null, paging);

        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Client> clients = new ArrayList<>();
        clients.add(new Client("Dmitry", "Arbuzov", "111"));
        clients.add(new Client("Dmitry", "Bananov", "112"));

        Mockito.when(clientRepository.findByFirstNameLike("Dmitry")).thenReturn(clients);

        SearchClientsResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getFoundClients().size(), 1);
        assertEquals(response.getFoundClients().get(0).getFirstName(), "Dmitry");
        assertEquals(response.getFoundClients().get(0).getLastName(), "Arbuzov");
    }

    @Test
    public void shouldSearchByFirstNameWithPagingSecondPage() {
        Paging paging = new Paging(2, 1);
        SearchClientsRequest request = new SearchClientsRequest("Dmitry", null, paging);

        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Client> clients = new ArrayList<>();
        clients.add(new Client("Dmitry", "Arbuzov", "111"));
        clients.add(new Client("Dmitry", "Bananov", "112"));

        Mockito.when(clientRepository.findByFirstNameLike("Dmitry")).thenReturn(clients);

        SearchClientsResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getFoundClients().size(), 1);
        assertEquals(response.getFoundClients().get(0).getFirstName(), "Dmitry");
        assertEquals(response.getFoundClients().get(0).getLastName(), "Bananov");
    }


}