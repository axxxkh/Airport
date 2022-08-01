package flightMicroService.repository;

import flightMicroService.entity.Role;

public interface RoleRepository extends GenericJPARepository<Role> {
    Role findByRole(String role);
}