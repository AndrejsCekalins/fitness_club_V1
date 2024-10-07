package fitness_center.console_UI;

import fitness_center.core.requests.GetAllClientsRequest;
import fitness_center.core.responses.GetAllClientsResponse;
import fitness_center.core.services.GetAllClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetAllClientsUIAction implements UIAction {
    @Autowired
    private GetAllClientsService getAllClientsService;


    @Override
    public void execute() {
        System.out.println("Clients list: ");
        GetAllClientsRequest request = new GetAllClientsRequest();
        GetAllClientsResponse response = getAllClientsService.execute(request);
        response.getClients().forEach(System.out::println);
        System.out.println("Client list end.");
    }
}
