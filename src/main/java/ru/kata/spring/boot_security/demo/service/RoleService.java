package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Set;

public interface RoleService {
    List<Role> listRoles(String username);

    List<Role> listOfRoles();

    Set<Role> getNewUserRoles(User user);

    Role findByName(String name);

    void saveRole(Role role);

}
