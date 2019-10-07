package mate.academy.ishop.dao.jdbc;

import mate.academy.ishop.dao.AbstractDao;
import mate.academy.ishop.dao.BucketDao;
import mate.academy.ishop.dao.UserDao;
import mate.academy.ishop.exceptions.AuthenticationException;
import mate.academy.ishop.lib.Inject;
import mate.academy.ishop.model.Bucket;
import mate.academy.ishop.model.Role;
import mate.academy.ishop.model.User;
import mate.academy.ishop.utils.HashingUtil;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class UserDaoJdbcImpl extends AbstractDao<User> implements UserDao {
    private static Logger logger = Logger.getLogger(UserDaoJdbcImpl.class);

    @Inject
    private static BucketDao bucketDao;

    public UserDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public User add(User user) {
        String query = "INSERT INTO ishop.users (login, token, password, seed) "
                + "VALUES (?, ?, ?, ?);";
        try (PreparedStatement statementUsers = connection.prepareStatement(
                query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            byte[] seed = HashingUtil.getSeed();
            String hashedPswd = HashingUtil.hashPassword(user.getPassword(), seed);
            statementUsers.setString(1, user.getLogin());
            statementUsers.setString(2, user.getToken());
            statementUsers.setString(3, hashedPswd);
            statementUsers.setBytes(4, seed);
            statementUsers.executeUpdate();
            ResultSet generatedKeys = statementUsers.getGeneratedKeys();
            generatedKeys.next();
            Long userId = generatedKeys.getLong(1);
            user.setUserId(userId);
            Bucket bucket = new Bucket(userId);
            bucketDao.add(bucket);
        } catch (SQLException e) {
            logger.error("Can't create user", e);
        }
        return user;
    }

    @Override
    public User get(Long id) {
        String query =
                "SELECT ishop.users.userId, ishop.users.login, ishop.users.password, "
                + "ishop.users.token, ishop.roles.name FROM ishop.users INNER JOIN ishop.roles_users "
                + "ON ishop.users.userId = ishop.roles_users.userId INNER JOIN ishop.roles "
                + "ON ishop.roles_users.roleId = ishop.roles.roleId WHERE ishop.users.userId = ?;";
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Set<Role> set = new HashSet<>();
            user = new User();
            while (resultSet.next()) {
                Long userId = resultSet.getLong("userId");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                String token = resultSet.getString("token");
                byte[] seed = resultSet.getBytes("seed");
                password = HashingUtil.hashPassword(password, seed);
                user = setUserFields(user, userId, login, password, token, seed);
                Role role = Role.of(resultSet.getString("name"));
                set.add(role);
            }
            user.setRoles(set);
        } catch (SQLException e) {
            logger.error("Can't get user", e);
        }
        return user;
    }

    @Override
    public User update(User user) {
        String query =
                "UPDATE ishop.users SET login = ?, password = ? "
                        + "WHERE userId = ?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setLong(3, user.getUserId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't update user", e);
        }
        return user;
    }

    @Override
    public void delete(Long id) {
        User user = get(id);
        String query = "DELETE FROM ishop.users WHERE userId = ?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't update user", e);
        }
    }

    @Override
    public User login(String login, String password) throws AuthenticationException {
        User user = null;
        String query =
                "SELECT ishop.users.userId, ishop.users.login, ishop.users.token, ishop.roles.name "
                + "FROM ishop.users INNER JOIN ishop.roles_users "
                + "ON ishop.users.userId = ishop.roles_users.userId "
                + "INNER JOIN ishop.roles ON ishop.roles_users.roleId = ishop.roles.roleId "
                + "WHERE login = ?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            Set<Role> set = new HashSet<>();
            user = new User();
            while (resultSet.next()) {
                byte [] seed = resultSet.getBytes("seed");
                String hashedPswd = HashingUtil.hashPassword(password, seed);
                if (!login.equals(resultSet.getString("login"))
                        || !hashedPswd.equals(
                                resultSet.getString("password"))) {
                    throw new AuthenticationException("Incorrect login or password!");
                }
                Long id = resultSet.getLong("userId");
                String name = resultSet.getString("login");
                String token = resultSet.getString("token");
                user = setUserFields(user, id, login, hashedPswd, token, seed);
                Role role = Role.of(resultSet.getString("name"));
                set.add(role);
            }
            if (user == null) {
                throw new AuthenticationException("incorrect username or password");
            }
            user.setRoles(set);
        } catch (SQLException e) {
            logger.error("Can't get users by login and password ", e);
        }
        return user;
    }

    @Override
    public Optional<User> getByToken(String token) {
        Optional<User> optionalUser = Optional.empty();
        String query =
                "SELECT ishop.users.userId, ishop.users.login, ishop.users.password, ishop.roles.name "
                        + "FROM ishop.users INNER JOIN ishop.roles_users "
                        + "ON ishop.users.userId = ishop.roles_users.userId "
                        + "INNER JOIN ishop.roles "
                        + "ON ishop.roles_users.roleId = ishop.roles.roleId "
                        + "WHERE token = ?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, token);
            ResultSet resultSet = statement.executeQuery();
            Set<Role> set = new HashSet<>();
            User user = new User();
            while (resultSet.next()) {
                Long id = resultSet.getLong("userId");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                byte[] seed = resultSet.getBytes("seed");
                password = HashingUtil.hashPassword(password, seed);
                user = setUserFields(user, id, login, password, token, seed);
                Role role = Role.of(resultSet.getString("name"));
                set.add(role);
            }
            user.setRoles(set);
            optionalUser = Optional.of(user);
        } catch (SQLException e) {
            logger.error("Can't get users by token ", e);
        }
        return optionalUser;
    }

    @Override
    public List<User> getAll() {
        String queryUsers = "SELECT * FROM ishop.users";
        List<User> list = new ArrayList<>();
        User user = null;
        try (PreparedStatement statementUsers = connection.prepareStatement(queryUsers);
             ResultSet resultSetUsers = statementUsers.executeQuery()) {
            user = new User();
            while (resultSetUsers.next()) {
                Long userId = resultSetUsers.getLong("userId");
                String login = resultSetUsers.getString("login");
                String password = resultSetUsers.getString("token");
                String token = resultSetUsers.getString("password");
                byte[] seed = resultSetUsers.getBytes("seed");
                password = HashingUtil.hashPassword(password, seed);
                user = setUserFields(user, userId, login, password, token, seed);
                String query =
                        "SELECT name FROM ishop.roles INNER JOIN ishop.roles_users "
                                + "ON ishop.roles.roleId = ishop.roles_users.roleId "
                                + "where ishop.roles_users.userId = ?;";
                try (PreparedStatement statementRoles = connection.prepareStatement(query)) {
                    statementRoles.setLong(1,  userId);
                    ResultSet resultSetRoles = statementRoles.executeQuery();
                    while (resultSetRoles.next()) {
                        Role role = Role.of(resultSetRoles.getString("role_name"));
                        user.getRoles().add(role);
                    }
                }
                list.add(user);
            }
        } catch (SQLException e) {
            logger.error("Can't get users", e);
        }
        return list;
    }

    private User setUserFields(User user, Long userId
            , String login, String password, String token, byte[] seed){
        user.setLogin(login);
        user.setPassword(password);
        user.setUserId(userId);
        user.setToken(token);
        user.setSeed(seed);
        return user;
    }
}
