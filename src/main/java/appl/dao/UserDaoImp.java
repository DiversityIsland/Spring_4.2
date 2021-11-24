package appl.dao;

import appl.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public User getUser(Long id) {
        return entityManager
                .createQuery("select distinct u from User u left join fetch u.roles where u.id = :id", User.class)
                .setParameter("id", id).getSingleResult();
    }

    @Override
    public void updateUser(Long id, User updatedUser) {
        updatedUser.setId(id);
        entityManager.merge(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        entityManager
                .createQuery("delete from User u where u.id = :id")
                .setParameter("id", id).executeUpdate();
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager
                .createQuery("select distinct u from User u left join fetch u.roles", User.class)
                .getResultList();
    }

    //

    @Override
    public User getUserByUsername(String username) {
        return entityManager
                .createQuery("select distinct u from User u left join fetch u.roles where u.username = :username", User.class)
                .setParameter("username", username).getSingleResult();
    }
}
