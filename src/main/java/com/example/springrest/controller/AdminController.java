package com.example.springrest.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import com.example.springrest.model.Role;
import com.example.springrest.model.User;
import com.example.springrest.service.RoleService;
import com.example.springrest.service.UserService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;


    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public List<User> showAllUsers() {
        return userService.getAllUsers();
    }
    @GetMapping("/roles")
    public List<Role> showAllRoles() {
        return roleService.getAllRoles();
    }


@RequestMapping(value = "/addUser", method = RequestMethod.POST)
public void addUser(@RequestBody User user) {

        Set<Role> roles = new HashSet<>();
        for (Role role : user.getRoles()) {
            roles.add(roleService.findRoleByRoleName(role.getRole()));
        }
        user.setRoles(roles);
        userService.add(user);
    }

    @GetMapping("/getUser")
    public User getUser(@RequestParam(value = "id")Long  id) {
    return userService.getUser(id);
    }

    @RequestMapping(value = "/editUser", method = RequestMethod.POST)
    public void editUser(@RequestBody User user) {

        Set<Role> roles = new HashSet<>();
        for (Role role : user.getRoles()) {
            roles.add(roleService.findRoleByRoleName(role.getRole()));
        }
        user.setRoles(roles);
        userService.edit(user);
    }

    @DeleteMapping("/delete")
    public void deleteUser(@RequestParam(value = "id") Long id) {
        userService.delete(userService.getUser(id));
    }

    @GetMapping("/user")
    public User getUserInfo(@AuthenticationPrincipal UserDetails currentUser) {
        return userService.findByUserName(currentUser.getUsername());
    }
}
