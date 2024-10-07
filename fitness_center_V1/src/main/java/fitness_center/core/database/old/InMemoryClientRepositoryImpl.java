package fitness_center.core.database.old;

import fitness_center.core.database.ClientRepository;
import fitness_center.core.domain.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//@Component
public class InMemoryClientRepositoryImpl implements ClientRepository {

    private Long nextId = 1L;
    private List<Client> clients = new ArrayList<>();

    public void save(Client client) {
        client.setId(nextId);
        nextId++;
        clients.add(client);
    }

    @Override
    public Optional<Client> getById(Long id) {
        return clients.stream()
                .filter(client -> client.getId().equals(id))
                .findFirst();
    }



    public boolean deleteByPersonalCode(String personalCode) {
        boolean isClientDeleted = false;
        Optional<Client> clientToDeleteOpt = clients.stream()
                .filter(client -> client.getPersonalCode().equals(personalCode))
                .findFirst();
        if (clientToDeleteOpt.isPresent()) {
            Client clientToRemove = clientToDeleteOpt.get();
            isClientDeleted = clients.remove(clientToRemove);
            updateClientIds(clients);
            saveClient(clients);
        }
        return isClientDeleted;

    }

    @Override
    public boolean deleteById(Long id) {
        boolean isClientDeleted = false;
        Optional<Client> clientToDeleteOpt = clients.stream()
                .filter(client -> client.getId().equals(id))
                .findFirst();
        if (clientToDeleteOpt.isPresent()) {
            Client clientToRemove = clientToDeleteOpt.get();
            isClientDeleted = clients.remove(clientToRemove);
            updateClientIds(clients);
            saveClient(clients);
        }
        return isClientDeleted;

    }

    public List<Client> getAllClients() {
        return clients;
    }


    /*@Override
    public boolean clientAgeGroupChangedByPersonalCode(String personalCode, ClientAgeGroups newAgeGroup) {
        Optional<Client> clientToChangeAgeGroupOpt = clients.stream()
                .filter(client -> client.getPersonalCode().equals(personalCode))
                .findFirst();
        if (clientToChangeAgeGroupOpt.isPresent()) {
            Client clientToChangeAgeGroup = clientToChangeAgeGroupOpt.get();
            clientToChangeAgeGroup.setClientAgeGroup(newAgeGroup);
            updateClientIds(clients);
            saveClient(clients);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean clientWorkoutsChangedByPersonalCode(String personalCode, Workouts newWorkout) {
        Optional<Client> clientToChangeWorkoutOpt = clients.stream()
                .filter(client -> client.getPersonalCode().equals(personalCode))
                .findFirst();
        if (clientToChangeWorkoutOpt.isPresent()) {
            Client clientToChangeWorkout = clientToChangeWorkoutOpt.get();
            clientToChangeWorkout.setWorkouts(newWorkout);
            updateClientIds(clients);
            saveClient(clients);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isClientFitnessCentreChangedByPersonalCode(String personalCode, FitnessCentre newFitnessCentre) {
        Optional<Client> clientToChangeFitnessCentreOpt = clients.stream()
                .filter(client -> client.getPersonalCode().equals(personalCode))
                .findFirst();
        if (clientToChangeFitnessCentreOpt.isPresent()) {
            Client clientToChangeFitnessCentre = clientToChangeFitnessCentreOpt.get();
            clientToChangeFitnessCentre.setFitnessCentre(newFitnessCentre);
            updateClientIds(clients);
            saveClient(clients);
            return true;
        } else {
            return false;
        }
    }

     */


    public void saveClient(List<Client> clients) {
    }

    @Override
    public List<Client> findByFirstName(String firstName) {
        return getAllClients().stream()
                .filter(client -> client.getFirstName().equals(firstName))
                .collect(Collectors.toList());
    }

    @Override
    public List<Client> findByLastName(String lastName) {
        return getAllClients().stream()
                .filter(client -> client.getLastName().equals(lastName))
                .collect(Collectors.toList());
    }

    @Override
    public List<Client> findByFirstNameAndLastName(String firstName, String lastName) {
        return getAllClients().stream()
                .filter(client -> client.getFirstName().equals(firstName))
                .filter(client -> client.getLastName().equals(lastName))
                .collect(Collectors.toList());
    }

    @Override
    public List<Client> findByPersonalCode(String personalCode) {
        return getAllClients().stream()
                .filter(client -> client.getPersonalCode().equals(personalCode))
                .collect(Collectors.toList());
    }

    private void updateClientIds(List<Client> clients) {
        for (int i = 0; i < clients.size(); i++) {
            clients.get(i).setId((long) (i + 1));
        }
    }

}
