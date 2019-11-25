package testgroup.crud_spring_hibernate.service;

import testgroup.crud_spring_hibernate.model.User;

import java.util.List;

public interface UserService {
    List<User> allUsers();

    Long add(User user);

    void delete(User user);

    void edit(User user);

    User getById(Long id);

    User findByUserName(String name);

}
