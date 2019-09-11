package internetshop.dao;

import internetshop.model.User;

public interface UserDao {
    // CRUD

    public User create(User item);

    public User get(Long id);

    public User update(User item);

    public User delete(Long id);

    public User deleteByItem(User item);
}
