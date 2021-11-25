package appl.service;

import appl.models.Role;

import java.util.List;

public interface RoleService {
    void addRole(Role role);

    Role getRole(Long id);

    void updateRole(Long id, Role updatedRole);

    void deleteRole(Long id);

    List<Role> getAllRoles();
}
