package fitness_center.console_UI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Component
public class ProgramMenu {

    private Map<Integer, UIAction> menuNumberToUIActionMap;

    @Autowired
    public ProgramMenu(List<UIAction> uiActions) {
        menuNumberToUIActionMap = new HashMap<>();
        menuNumberToUIActionMap.put(1, findUIAction(uiActions, AddClientUIAction.class));
        menuNumberToUIActionMap.put(2, findUIAction(uiActions, RemoveClientUIAction.class));
        menuNumberToUIActionMap.put(3, findUIAction(uiActions, GetAllClientsUIAction.class));
        menuNumberToUIActionMap.put(4, findUIAction(uiActions, CreateMemberCardUIAction.class));
        menuNumberToUIActionMap.put(5, findUIAction(uiActions, SearchClientUIAction.class));
        menuNumberToUIActionMap.put(6, findUIAction(uiActions, ExitUIAction.class));
    }


    private UIAction findUIAction(List<UIAction> uiActions, Class uiActionClass) {
        return uiActions.stream()
                .filter(uiAction -> uiAction.getClass().equals(uiActionClass))
                .findFirst()
                .get();
    }

    public void printProgramMenu() {
        System.out.println("");
        System.out.println("Program menu:");
        System.out.println("1. Add client to list");
        System.out.println("2. Remove client from list");
        System.out.println("3. Show all clients in the list");
        System.out.println("4. Create client member card");
        System.out.println("5. Search clients in database");
        System.out.println("6. Exit");
        System.out.println("");
    }

    public int getMenuNumberFromUser() {
        System.out.println("Enter menu item number to execute: ");
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine());
    }

    public void executeSelectedMenuItem(int selectedMenu) throws ParseException {
        menuNumberToUIActionMap.get(selectedMenu).execute();
    }
}
