package fitness_center.acceptance_tests;

import fitness_center.config.SpringCoreConfiguration;
import fitness_center.core.DatabaseCleaner;
import fitness_center.core.requests.AddClientRequest;
import fitness_center.core.requests.SearchClientsRequest;
import fitness_center.core.responses.AddClientResponse;
import fitness_center.core.responses.SearchClientsResponse;
import fitness_center.core.services.AddClientService;
import fitness_center.core.services.SearchClientsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringCoreConfiguration.class})
@Sql({"/schema.sql"})
public class AddClientAcceptanceTest {
    @Autowired
    private AddClientService addClientService;
    @Autowired
    private SearchClientsService searchClientService;
    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Before
    public void setup() {
        databaseCleaner.clean();
    }


    @Test
    public void shouldReturnErrorForFirstName() {
        AddClientRequest addClientRequest = new AddClientRequest("", "test", "12345");
        AddClientResponse response = addClientService.execute(addClientRequest);
        assertEquals(response.getErrors().get(0).getField(), "firstName");
        assertEquals(response.getErrors().get(0).getMessage(), "Must not be empty or contain symbols!");
    }

    @Test
    public void shouldReturnErrorForLastName() {
        AddClientRequest addClientRequest = new AddClientRequest("test", "", "12345");
        AddClientResponse response = addClientService.execute(addClientRequest);
        assertEquals(response.getErrors().get(0).getField(), "lastName");
        assertEquals(response.getErrors().get(0).getMessage(), "Must not be empty or contain symbols!");
    }

    @Test
    public void shouldReturnErrorForPersonalCode() {
        AddClientRequest addClientRequest = new AddClientRequest("test", "test2", "");
        AddClientResponse response = addClientService.execute(addClientRequest);
        assertEquals(response.getErrors().size(), 2);
        assertEquals(response.getErrors().get(1).getField(), "personalCode");
        assertEquals(response.getErrors().get(1).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnCorrectClient() {
        AddClientRequest addClientRequest = new AddClientRequest("FirstName", "LastName", "123");
        addClientService.execute(addClientRequest);
        SearchClientsResponse response = searchClientService.execute(new SearchClientsRequest("FirstName", "LastName"));
        assertEquals(response.getFoundClients().get(0).getFirstName(), "FirstName");
        assertEquals(response.getFoundClients().get(0).getLastName(), "LastName");
        assertEquals(response.getFoundClients().get(0).getPersonalCode(), "123");
    }


}
