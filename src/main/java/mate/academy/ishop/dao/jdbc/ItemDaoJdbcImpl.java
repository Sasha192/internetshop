package mate.academy.ishop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import mate.academy.ishop.dao.AbstractDao;
import mate.academy.ishop.dao.ItemDao;
import mate.academy.ishop.lib.Dao;
import mate.academy.ishop.model.Item;
import org.apache.log4j.Logger;

@Dao
public class ItemDaoJdbcImpl extends AbstractDao<Item> implements ItemDao {
    private static Logger logger = Logger.getLogger(ItemDaoJdbcImpl.class);

    public ItemDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Item add(Item item) {
        String query = "INSERT INTO " + DB_NAME + ".items (name, price) VALUES (?, ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, item.getName());
            preparedStatement.setDouble(2, item.getPrice());
            preparedStatement.executeUpdate();
            return item;
        } catch (SQLException e) {
            logger.error("Can not add new item", e);
        }
        return item;
    }

    @Override
    public Item get(Long id) {
        String query = "SELECT * FROM " + DB_NAME + ".items WHERE item_id=" + id + ";";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                Double price = resultSet.getDouble("price");
                Item item = new Item(id,name, price);
                return item;
            }
        } catch (SQLException e) {
            logger.error("Can not get item by id " + id, e);
        }
        return null;
    }

    @Override
    public Item update(Item item) {
        String query = "UPDATE " + DB_NAME + ".items SET name = ?, price = ? WHERE (item_id = ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, item.getName());
            preparedStatement.setDouble(2, item.getPrice());
            preparedStatement.setLong(3, item.getIdItem());
            preparedStatement.executeUpdate();
            return item;
        } catch (SQLException e) {
            logger.error("Can not update item", e);
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        String query = "DELETE FROM " + DB_NAME + ".items WHERE (item_id = ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can not delete item", e);
        }
    }

    @Override
    public List<Item> getAll() {
        String query = "SELECT * FROM " + DB_NAME + ".items;";
        List<Item> returnList = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Long id = resultSet.getLong("idItem");
                String name = resultSet.getString("name");
                Double price = resultSet.getDouble("price");
                Item item = new Item(id, name, price);
                returnList.add(item);
            }
        } catch (SQLException e) {
            logger.error("Can not get items list", e);
        }
        return returnList;
    }
}
