package mate.academy.ishop.service.impl;

import mate.academy.ishop.dao.RoleDao;
import mate.academy.ishop.lib.Inject;
import mate.academy.ishop.model.Role;
import mate.academy.ishop.model.User;
import mate.academy.ishop.service.RoleService;

import java.util.Set;

public class RoleServiceImpl implements RoleService {
    @Inject
    private static RoleDao roleDao;

    @Override
    public Set<Role> getRoles(User user) {
        return roleDao.getRoles(user);
    }

    public Role getRoleByName(String name) {
        return roleDao.getRoleByName(name);
    }
}
