package fitness_center.core.database.jpa;

import fitness_center.core.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaClientRepository extends JpaRepository<Client, Long> {

    List<Client> findByFirstName(String firstName);

    @Query("SELECT c FROM Client c WHERE c.personalCode LIKE %:personalCode%")
    List<Client> findByPersonalCodeLike(@Param("personalCode") String personalCode);

    @Query("SELECT c FROM Client c WHERE c.firstName LIKE %:firstName%")
    List<Client> findByFirstNameLike(@Param("firstName") String firstName);

    @Query("SELECT c FROM Client c WHERE c.lastName LIKE %:lastName%")
    List<Client> findByLastNameLike(@Param("lastName") String lastName);

    @Query("SELECT c FROM Client c WHERE c.firstName LIKE %:firstName% AND c.lastName LIKE %:lastName%")
    List<Client> findByFirstNameAndLastNameLike(@Param("firstName") String firstName,
                                                @Param("lastName") String lastName);

    @Query("SELECT c FROM Client c WHERE c.personalCode = :personalCode")
    Optional<Client> findByPersonalCodeOpt(@Param("personalCode") String personalCode);

    Optional<Client> findByFirstNameAndLastNameAndPersonalCode(String firstName, String lastName, String personalCode);

    void deleteByPersonalCode(String personalCode);

    Client findByPersonalCode(String personalCode);


}
