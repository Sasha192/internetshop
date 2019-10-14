package mate.academy.ishop.dao;

import mate.academy.ishop.model.Role;
import mate.academy.ishop.model.User;

import java.util.Set;

public interface RoleDao {
    Set<Role> getRoles(User user);

    Role getRoleByName(String name);
}
