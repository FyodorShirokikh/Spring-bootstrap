package ru.kata.spring.boot_security.demo.repository;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class RoleRepositoryImp implements RoleRepository {
    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;

    @Override
    public Role findByName(String name) {
        Query q = entityManager.createNativeQuery("select * from Roles where name = :name", Role.class);
        q.setParameter("name", name);
        return (Role)q.getSingleResult();
    }

    @Override
    public List<Role> findAll(String email) {
        Query q = entityManager.createNativeQuery(
                "select r.id, r.name from roles r " +
                        "INNER JOIN users_roles ur ON ur.role_id = r.id " +
                        "INNER JOIN users u ON u.id = ur.user_id " +
                        "where u.email = :email", Role.class);
        q.setParameter("email", email);
        return (List<Role>) q.getResultList();
    }

    @Override
    public Set<Role> getNewUserRoles(User user) {
        Role roleUser;
        Set<Role> roleSetTemp = new HashSet<>();
        Set<Role> rolesUser = user.getRoles();
        for (Role role : rolesUser) {
            roleUser = findByName(role.getName());
            roleSetTemp.add(roleUser);
        }
        return roleSetTemp;
    }

    @Override
    public List<Role> listOfRoles() {
        Query q = entityManager.createNativeQuery("select * from Roles", Role.class);
        return (List<Role>) q.getResultList();
    }

    @Override
    public void saveNew(Role role) {
        entityManager.persist(role);
    }
}
