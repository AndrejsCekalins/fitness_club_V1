package fitness_center.console_UI;

import fitness_center.core.requests.AddClientRequest;
import fitness_center.core.responses.AddClientResponse;
import fitness_center.core.services.AddClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AddClientUIAction implements UIAction {

    @Autowired
    private AddClientService addClientService;


    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter client first name: ");
        String clientFirstName = scanner.nextLine();
        clientFirstName = (!clientFirstName.isEmpty()) ? clientFirstName.toLowerCase().substring(0, 1).
                toUpperCase().concat(clientFirstName.substring(1)) : clientFirstName;
        System.out.println("Enter client last name: ");
        String clientLastName = scanner.nextLine();
        clientLastName = (!clientLastName.isEmpty()) ? clientLastName.toLowerCase().substring(0, 1).
                toUpperCase().concat(clientLastName.substring(1)) : clientLastName;
        System.out.println("Enter client personal code: ");
        String clientPersonalCode = scanner.nextLine();

        AddClientRequest request = new AddClientRequest(clientFirstName, clientLastName, clientPersonalCode);
        AddClientResponse response = addClientService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage())
            );
        } else {
            System.out.println("New client id was: " + response.getNewClient().getId());
            System.out.println("Client was added to list.");
        }
    }
}
