package ru.kata.spring.boot_security.demo.model;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", length = 255)
    private String username;

    @Column(name = "lastname", length = 255)
    private String lastname;

    @Column(name = "email", unique = true, length = 255)
    private String email;

    @Column(name = "age")
    private int age;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(String username, String lastname, String email, int age, String password) {
        this.username = username;
        this.lastname = lastname;
        this.email = email;
        this.age = age;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public String getRolesStr() {
        String resultRoles = "";
        for (Role role : getRoles()) {
            if (role.getName().equals("ROLE_USER")) {
                if (resultRoles != "") {
                    resultRoles = resultRoles + " USER";
                } else {
                    resultRoles = "USER";
                }
            }
            if (role.getName().equals("ROLE_ADMIN")) {
                if (resultRoles != "") {
                    resultRoles = "ADMIN " + resultRoles;
                } else {
                    resultRoles = "ADMIN";
                }
            }
        }
        return resultRoles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void addRole (Role role) {
        this.roles.add(role);
    }

    public void deleteRole (Role role) {
        this.roles.remove(role);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age && Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(lastname, user.lastname) && email.equals(user.email) && Objects.equals(password, user.password) && Objects.equals(roles, user.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, lastname, email, age, password, roles);
    }

}
