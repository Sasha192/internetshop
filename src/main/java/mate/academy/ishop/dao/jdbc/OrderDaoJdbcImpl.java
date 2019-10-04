package mate.academy.ishop.dao.jdbc;

import mate.academy.ishop.dao.AbstractDao;
import mate.academy.ishop.dao.OrderDao;
import mate.academy.ishop.dao.UserDao;
import mate.academy.ishop.lib.Inject;
import mate.academy.ishop.model.Item;
import mate.academy.ishop.model.Order;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoJdbcImpl extends AbstractDao<Order> implements OrderDao {
    private static Logger logger = Logger.getLogger(OrderDaoJdbcImpl.class);

    @Inject
    private static UserDao userDao;

    public OrderDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Order add(Order order) {
        Long orderId = null;
        String query = "INSERT INTO ishop.orders (userId) VALUES (?);";
        try (PreparedStatement statement =
                     connection.prepareStatement(query,
                             Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, order.getUserId());
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    orderId = generatedKeys.getLong(1);
                } else {
                    throw new SQLException("Creating order failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            logger.error("Can’t create order with id = " + order.getOrderId(), e);
        }
        String insertOrderItemQuery =
                "INSERT INTO ishop.order_items (orderId, itemId) VALUES(?, ?);";
        try (PreparedStatement statement = connection.prepareStatement(insertOrderItemQuery)) {
            for (Item item: order.getItems()) {
                statement.setLong(1, orderId);
                statement.setLong(2, item.getIdItem());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            logger.error("Can’t get item to order", e);
        }
        return new Order(orderId, order.getUserId(), order.getItems());
    }

    @Override
    public Order get(Long id) {
        Order order = null;
        String getOrderQuery = "SELECT ishop.orders.orderId, ishop.orders.userId, "
                .concat("ishop.items.idItem, ishop.items.name, ishop.items.price ")
                .concat("FROM ishop.orders INNER JOIN ishop.order_items ")
                .concat("ON ishop.orders.orderId=ishop.order_items.orderId ")
                .concat("INNER JOIN ishop.items ")
                .concat("ON ishop.order_items.itemId=ishop.items.idItem")
                .concat("WHERE ishop.orders.orderId=?;");
        try (PreparedStatement statement = connection.prepareStatement(getOrderQuery)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            List<Item> items = new ArrayList<>();
            Long orderId = null;
            Long userId = null;
            while (resultSet.next()) {
                orderId = resultSet.getLong("order_id");
                userId = resultSet.getLong("user_id");
                Item item = new Item(resultSet.getLong("item_id")
                        , resultSet.getString("name")
                        , resultSet.getDouble("price"));
                items.add(item);
            }
            order = new Order(orderId, userId,items);
        } catch (SQLException e) {
            logger.error("Can’t get order with id=" + id, e);
        }
        return order;
    }

    @Override
    public Order get(Order order) {
        return get(order.getOrderId());
    }

    @Override
    public Order update(Order order) {
        String query = "UPDATE orders SET userId = ? WHERE orderId = ?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, order.getUserId());
            statement.setLong(2, order.getOrderId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't update order", e);
        }
        return order;
    }

    @Override
    public void delete(Order order) {
        delete(order.getOrderId());
    }

    @Override
    public void delete(Long orderId) {
        Order order = get(orderId);
        String query = "DELETE FROM ishop.orders WHERE orderId = ?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, orderId);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't delete order", e);
        }
    }

    @Override
    public List<Order> getOrders(Long userId) {
        List<Order> list = new ArrayList<>();
        String queryOrders = "SELECT * FROM ishop.orders WHERE userId = ?;";
        try (PreparedStatement statementOrder = connection.prepareStatement(queryOrders)) {
            statementOrder.setLong(1, userId);
            ResultSet resultSet = statementOrder.executeQuery();
            while (resultSet.next()) {
                Long orderId = resultSet.getLong("order_id");
                List<Item> listItem = getItems(orderId, userId);
                Order order = new Order(orderId, userId, listItem);
                list.add(order);
            }
        } catch (SQLException e) {
            logger.error("Can't get orders", e);
        }
        return list;
    }

    @Override
    public List<Item> getItems(Long orderId, Long userId) {
        List<Item> listItem = new ArrayList<>();
        String queryItems =
                "SELECT * FROM ishop.items INNER JOIN ishop.order_items "
                        + "ON ishop.items.idItem = ishop.order_items.itemId "
                        + "INNER JOIN ishop.orders ON ishop.order_items.orderId = ishop.orders.orderId "
                        + "WHERE ishop.orders.userId = ? and ishop.orders.orderId = ?;";
        try (PreparedStatement statementItems = connection.prepareStatement(queryItems)) {
            statementItems.setLong(1, userId);
            statementItems.setLong(2, orderId);
            ResultSet resultSetItems = statementItems.executeQuery();
            while (resultSetItems.next()) {
                Long itemId = resultSetItems.getLong("item_id");
                String name = resultSetItems.getString("name");
                Double price = resultSetItems.getDouble("price");
                Item item = new Item(itemId, name, price);
                listItem.add(item);
            }
        } catch (SQLException e) {
            logger.error("Can't get list items by order and user", e);
        }
        return listItem;
    }
}
