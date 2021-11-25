package appl.dao;

import appl.models.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDaoImp implements RoleDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addRole(Role role) {
        entityManager.persist(role);
    }

    @Override
    public Role getRole(Long id) {
        return entityManager
                .createQuery("select r from Role r where r.id = :id", Role.class)
                .setParameter("id", id).getSingleResult();
    }

    @Override
    public void updateRole(Long id, Role updatedRole) {
        updatedRole.setId(id);
        entityManager.merge(updatedRole);
    }

    @Override
    public void deleteRole(Long id) {
        entityManager
                .createQuery("delete from Role r where r.id = :id")
                .setParameter("id", id).executeUpdate();
    }

    @Override
    public List<Role> getAllRoles() {
        return entityManager
                .createQuery("select r from Role r", Role.class)
                .getResultList();
    }
}
