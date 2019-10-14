package mate.academy.ishop.service;

import mate.academy.ishop.model.Role;
import mate.academy.ishop.model.User;

import java.util.Set;

public interface RoleService {
    Set<Role> getRoles(User user);

    Role getRoleByName(String name);
}
