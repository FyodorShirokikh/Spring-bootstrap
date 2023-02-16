package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.GrantedAuthority;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.Collection;
import java.util.List;
public interface UserService {
    User findByEmail(String email);
    Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles);
    User get(Long id);
    void save(User user);
    void registerDefaultUser(User user);
    void delete(Long id);
    List<User> showUsers();
    void saveNew(User user);
}
