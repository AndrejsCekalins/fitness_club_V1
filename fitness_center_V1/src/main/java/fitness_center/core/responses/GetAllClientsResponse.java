package fitness_center.core.responses;




import fitness_center.core.domain.Client;

import java.util.List;

public class GetAllClientsResponse extends CoreResponse {

    private List<Client> clients;

    public GetAllClientsResponse(List<Client> clients) {
        this.clients = clients;
    }

    public List<Client> getClients() {
        return clients;
    }
}
