package com.example.springrest.dao;

import com.example.springrest.model.Role;

import java.util.List;

public interface RoleDao {
    Role findRoleByRoleName(String role);
    List<Role> getAllRoles();
}
