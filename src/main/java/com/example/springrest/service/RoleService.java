package com.example.springrest.service;

import com.example.springrest.model.Role;

import java.util.List;

public interface RoleService {
    Role findRoleByRoleName(String role);
    List<Role> getAllRoles();
}
