package testgroup.crud_spring_hibernate.dao;

import testgroup.crud_spring_hibernate.model.Role;

import java.util.List;

public interface RolesDAO {

    Role getById(Long id);
    void add(Role role);
    void delete(Long id);
    void edit(Role role);
    List<Role> allRoles();


}
