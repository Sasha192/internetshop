package mate.academy.ishop.service;

import mate.academy.ishop.model.Role;
import mate.academy.ishop.model.User;

import java.util.Optional;
import java.util.Set;

public interface RoleService {
    Set<Role> getRoles(User user);

    Optional<Role> getRoleByName(String name);
}
