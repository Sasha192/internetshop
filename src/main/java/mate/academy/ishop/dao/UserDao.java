package mate.academy.ishop.dao;

import java.util.List;
import java.util.Optional;

import mate.academy.ishop.exceptions.AuthenticationException;
import mate.academy.ishop.model.User;

public interface UserDao {
    public Optional<User> add(User item);

    public Optional<User> get(Long id);

    public Optional<User> update(User item);

    public void delete(Long id);

    Optional<User> login(String login, String password) throws AuthenticationException;

    Optional<User> getByToken(String token);

    public List<User> getAll();
}
