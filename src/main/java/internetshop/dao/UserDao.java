package internetshop.dao;

import internetshop.model.User;

public interface UserDao {
    // CRUD

    public User add(User item);

    public User get(Long id);

    public User update(User item);

    public void delete(Long id);
}
