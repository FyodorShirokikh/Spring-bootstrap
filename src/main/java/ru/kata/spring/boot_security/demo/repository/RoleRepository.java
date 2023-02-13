package ru.kata.spring.boot_security.demo.repository;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Set;


public interface RoleRepository {
    Role findByName(String name);

    List<Role> findAll(String username);

    Set<Role> getNewUserRoles(User user);

    List<Role> listOfRoles();

    void saveNew(Role role);

}
