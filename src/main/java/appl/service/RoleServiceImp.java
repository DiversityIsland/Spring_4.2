package appl.service;

import appl.dao.RoleDao;
import appl.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImp implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Transactional
    @Override
    public void addRole(Role role) {
        roleDao.addRole(role);
    }

    @Transactional(readOnly = true)
    @Override
    public Role getRole(Long id) {
        return roleDao.getRole(id);
    }

    @Transactional
    @Override
    public void updateRole(Long id, Role updatedRole) {
        roleDao.updateRole(id, updatedRole);
    }

    @Transactional
    @Override
    public void deleteRole(Long id) {
        roleDao.deleteRole(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Role> getAllRoles() {
        return roleDao.getAllRoles();
    }
}
