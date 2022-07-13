package auth.Repository;

import auth.entity.Role;

public interface RoleRepository extends GenericJPARepository<Role>{
    Role findByRole(String role);
}