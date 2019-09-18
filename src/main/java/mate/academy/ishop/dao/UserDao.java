package mate.academy.ishop.dao;

import mate.academy.ishop.model.User;

public interface UserDao {
    public User add(User item);

    public User get(Long id);

    public User update(User item);

    public void delete(Long id);
}