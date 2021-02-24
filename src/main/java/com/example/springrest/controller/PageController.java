package com.example.springrest.controller;
import com.example.springrest.model.Role;
import com.example.springrest.model.User;
import com.example.springrest.service.RoleService;
import com.example.springrest.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class PageController {

    private final UserService userService;
    private final RoleService roleService;

    public PageController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String getAdminPage(Model model, @AuthenticationPrincipal UserDetails currentUser) {
        User user = userService.findByUserName(currentUser.getUsername());
        List<Role> listRoles = roleService.getAllRoles();
        model.addAttribute("listRoles", listRoles);
        model.addAttribute("roles", user.getRoleByUser());
        model.addAttribute("user", user);
        return "admin";
    }
    @GetMapping("/user")
    public String getUserPage(Model model, @AuthenticationPrincipal UserDetails currentUser) {
        User user = userService.findByUserName(currentUser.getUsername());
        List<Role> listRoles = roleService.getAllRoles();
        model.addAttribute("listRoles", listRoles);
        model.addAttribute("roles", user.getRoleByUser());
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping(value = "/login")
    public String getLoginPage() {
        return "login";
    }
}
