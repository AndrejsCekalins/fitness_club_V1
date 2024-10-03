package org.fitness_center.core.database.old;

import org.fitness_center.core.database.ClientRepository;
import org.fitness_center.core.domain.AgeGroup;
import org.fitness_center.core.domain.Client;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InFileClientRepositoryImpl implements ClientRepository {
    private AgeGroup ageGroups;
    private final String filename;
    private final List<Client> clients = new ArrayList<>();

    public InFileClientRepositoryImpl() {
        this.filename = ".\\team_fitness_club\\src\\main\\java\\fitness_club\\core\\database\\ClientsFile.bin";
    }

    public void save(Client client) {
        List<Client> clients = getAllClients();
        client.setId(generateNextId(clients));
        clients.add(client);
        saveClient(clients);
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
        loadClientsFromFile();
        return clients;
    }


    /*@Override
    public boolean clientAgeGroupChangedByPersonalCode(String personalCode, ClientAgeGroups newAgeGroup) {
        loadClientsFromFile();
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
        loadClientsFromFile();
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
        loadClientsFromFile();
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
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(clients);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Long generateNextId(List<Client> clients) {
        if (clients.isEmpty()) {
            return 1L;
        } else {
            long maxId = clients.stream().mapToLong(Client::getId).max().orElse(0L);
            return maxId + 1;
        }
    }

    private void updateClientIds(List<Client> clients) {
        for (int i = 0; i < clients.size(); i++) {
            clients.get(i).setId((long) (i + 1));
        }
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

    @SuppressWarnings("unchecked")
    private void loadClientsFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            List<Client> loadedClients = (List<Client>) ois.readObject();
            clients.clear();
            clients.addAll(loadedClients);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
