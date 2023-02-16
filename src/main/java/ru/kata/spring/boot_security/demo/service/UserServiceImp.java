package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    public UserServiceImp(BCryptPasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
    }

    public User get(Long id) {
        return userRepository.findById(id);
    }

    @Transactional
    public void save(User user) {
        User tempUser = findByEmail(user.getEmail());
        tempUser.setId(user.getId());
        tempUser.setUsername(user.getUsername());
        tempUser.setLastname(user.getLastname());
        tempUser.setEmail(user.getEmail());
        tempUser.setAge(user.getAge());
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        tempUser.setPassword(encodedPassword);
        tempUser.setRoles(user.getRoles());
        userRepository.save(tempUser);
    }

    private void encodePassword(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }

    @Transactional
    public void registerDefaultUser(User user) {
        User userFromDB = userRepository.findByEmail(user.getEmail());
        if (userFromDB == null) {
            encodePassword(user);
            userRepository.saveNew(user);
        }
    }

    @Transactional
    public void saveNew(User user) {
        encodePassword(user);
        userRepository.saveNew(user);
    }

    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> showUsers() {
        return userRepository.findAll();
    }

}
