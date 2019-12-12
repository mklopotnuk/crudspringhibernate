package testgroup.crud_spring_hibernate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import testgroup.crud_spring_hibernate.dao.RoleDAO;
import testgroup.crud_spring_hibernate.model.Role;
import testgroup.crud_spring_hibernate.service.RoleService;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleDAO roleDAO;

    @Autowired
    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    public Role getRoleById(Long id) {
        return roleDAO.getById(id);
    }

    @Override
    public void updateRole(Role role) {
        roleDAO.edit(role);
    }

    @Override
    public void addRole(Role role) {
        roleDAO.add(role);
    }

    @Override
    public void deleteRole(Long id) {
        roleDAO.delete(id);
    }

    @Override
    public List<Role> getRoles() {
        return roleDAO.allRoles();
    }
}
