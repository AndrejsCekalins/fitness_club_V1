package fitness_center.core.database;

import fitness_center.core.domain.Client;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

//@Component
//@Transactional
public class OrmClientRepositoryImpl implements ClientRepository {

    @Autowired private SessionFactory sessionFactory;

    @Override
    public void save(Client client) {
        sessionFactory.getCurrentSession().save(client);
    }

    @Override
    public Optional<Client> getById(Long id) {
        Client client = sessionFactory.getCurrentSession()
                .get(Client.class, id);
        if(client == null) {
            return Optional.empty();
        } else {
            return Optional.of(client);
        }
    }


    @Override
    public boolean deleteByPersonalCode(String personalCode) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("delete Client where personal_code = :personalCode");
        query.setParameter("personalCode", personalCode);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public boolean deleteById(Long id) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("delete Client where id = :id");
        query.setParameter("id", id);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public List<Client> getAllClients() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT * FROM Client ORDER BY last_name, id LIMIT 10 OFFSET 10", Client.class)
                .getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Client> findByFirstName(String firsName) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "select c FROM Client c where first_name = :firstName");
        query.setParameter("firstName", firsName);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Client> findByLastName(String lastName) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "select c FROM Client c where last_name = :lastName");
        query.setParameter("lastName", lastName);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Client> findByPersonalCode(String personalCode) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "select c FROM Client c where personal_code = :personalCode");
        query.setParameter("personalCode", personalCode);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Client> findByFirstNameAndLastName(String firstName, String lastName) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "select c FROM Client c where first_name = : firstName AND lastName = :lastName");
        query.setParameter("firstName", firstName);
        query.setParameter("lastName", lastName);
        return query.getResultList();
    }
}
