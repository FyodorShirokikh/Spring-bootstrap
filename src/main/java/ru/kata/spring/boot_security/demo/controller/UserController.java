package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/")
public class UserController {
    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/")
    public String homePage() {
        return "redirect:/login";
    }

    @GetMapping("/access-denied")
    public String accessDeniedPage() {
        return "access-denied";
    }

    @GetMapping("/admin")
    public String showUsers(Model model, Principal principal) {
        model.addAttribute("users", userService.showUsers());
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("newuser", new User());
        Set<Integer> newroles = new HashSet<>();
        model.addAttribute("newroles", newroles);
        return "admin-user";
    }

    @GetMapping("/user")
    public String showUserDetails(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "userdetails";
    }

    @RequestMapping("/getOne")
    @ResponseBody
    public User getOne(Long id) {
        return userService.get(id);
    }

    @PostMapping("/admin/process_register")
    public String processRegister(@ModelAttribute("newuser") User newuser) {
        Set<Role> roleSetTemp = new HashSet<>();
        Role roleUser;
        Set<Role> rolesUser = newuser.getRoles();
        for (Role role : rolesUser) {
            roleUser = roleService.findByName(role.getName());
            roleSetTemp.add(roleUser);
        }
        newuser.setRoles(roleSetTemp);
        userService.registerDefaultUser(newuser);
        return "redirect:/admin";
    }

    @PostMapping("/admin/save")
    public String saveUser(User user) {
        Set<Role> roleSetTemp = new HashSet<>();
        Role roleUser;
        Set<Role> rolesUser = user.getRoles();
        for (Role role : rolesUser) {
            roleUser = roleService.findByName(role.getName());
            roleSetTemp.add(roleUser);
        }
        user.setRoles(roleSetTemp);
        userService.save(user);
        return "redirect:/admin";
    }

    @PostMapping("/admin/delete")
    public String deleteUser(User user) {
        userService.delete(user.getId());
        return "redirect:/admin";
    }
}
