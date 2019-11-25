package testgroup.crud_spring_hibernate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import testgroup.crud_spring_hibernate.dao.RolesDAO;
import testgroup.crud_spring_hibernate.model.Role;
import testgroup.crud_spring_hibernate.service.RoleService;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private RolesDAO  rolesDAO;

    @Autowired
    public RoleServiceImpl(RolesDAO rolesDAO) {
        this.rolesDAO = rolesDAO;
    }

    @Override
    public Role getRoleById(Long id) {
        return rolesDAO.getById(id);
    }

    @Override
    public void updateRole(Role role) {
        rolesDAO.edit(role);
    }

    @Override
    public void addRole(Role role) {
        rolesDAO.add(role);
    }

    @Override
    public void deleteRole(Long id) {
        rolesDAO.delete(id);
    }

    @Override
    public List<Role> getRoles() {
        return rolesDAO.allRoles();
    }
}
