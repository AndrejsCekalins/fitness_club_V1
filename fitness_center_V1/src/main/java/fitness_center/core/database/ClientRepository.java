package fitness_center.core.database;

import fitness_center.core.domain.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {
    void save(Client client);

    Optional<Client> getById(Long id);

    boolean deleteByPersonalCode(String personalCode);

    boolean deleteById(Long id);

    List<Client> getAllClients();

    List<Client> findByFirstName(String firsName);

    List<Client> findByLastName(String lastName);

    List<Client> findByFirstNameAndLastName(String firstName, String lastName);

    List<Client> findByPersonalCode(String personalCode);

}
