package mate.academy.ishop.dao;

import java.util.Optional;

import mate.academy.ishop.exceptions.AuthenticationException;
import mate.academy.ishop.model.User;

public interface UserDao {
    public User add(User item);

    public User get(Long id);

    public User update(User item);

    public void delete(Long id);

    User login(String login, String password) throws AuthenticationException;

    Optional<User> getByToken(String token);
}
