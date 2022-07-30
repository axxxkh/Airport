package com.flightService.repository;


import com.flightService.entity.Role;

public interface RoleRepository extends GenericJPARepository<Role> {
    Role findByRole(String role);
}