package testgroup.crud_spring_hibernate.dao;

import testgroup.crud_spring_hibernate.model.User;

import java.util.List;

public interface UserDAO{
    List<User> allUsers();

    Long add(User user);

    void delete(Long id);

    void edit(User user);

    User getById(Long id);

    User findByUserName(String username);

//    Long getId();
}
