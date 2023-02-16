package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImp implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImp(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> listRoles(String email) {
        return roleRepository.findAll(email);
    }

    @Override
    public Set<Role> getNewUserRoles(User user) {
        return roleRepository.getNewUserRoles(user);
    }

    @Override
    public List<Role> listOfRoles() {
        return roleRepository.listOfRoles();
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

    @Transactional
    public void saveRole(Role role) {
        roleRepository.saveNew(role);
    }

}
