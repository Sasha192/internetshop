package mate.academy.ishop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import mate.academy.ishop.dao.BucketDao;
import mate.academy.ishop.dao.ItemDao;
import mate.academy.ishop.dao.OrderDao;
import mate.academy.ishop.dao.UserDao;
import mate.academy.ishop.dao.hibernate.ItemDaoHibernateImpl;
import mate.academy.ishop.dao.jdbc.BucketDaoJdbcImpl;
import mate.academy.ishop.dao.jdbc.OrderDaoJdbcImpl;
import mate.academy.ishop.dao.jdbc.UserDaoJdbcImpl;
import mate.academy.ishop.service.BucketService;
import mate.academy.ishop.service.ItemService;
import mate.academy.ishop.service.OrderService;
import mate.academy.ishop.service.UserService;
import mate.academy.ishop.service.impl.BucketServiceImpl;
import mate.academy.ishop.service.impl.ItemServiceImpl;
import mate.academy.ishop.service.impl.OrderServiceImpl;
import mate.academy.ishop.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

public class Factory {
    private static BucketDao bucketDao;
    private static ItemDao itemDao;
    private static OrderDao orderDao;
    private static UserDao userDao;
    private static BucketService bucketService;
    private static ItemService itemService;
    private static OrderService orderService;
    private static UserService userService;
    private static Connection connection;

    private static Logger logger = Logger.getLogger(Factory.class);

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ishop?"
                    + "user=root&password=01123581321Sasha&serverTimezone=UTC");
        } catch (SQLException | ClassNotFoundException e) {
            logger.error("Can not connect to DB", e);
        }
    }

    public static BucketDao getBucketDao() {
        if (bucketDao == null) {
            bucketDao = new BucketDaoJdbcImpl(connection);
        }
        return bucketDao;
    }

    public static ItemDao getItemDao() {
        if (itemDao == null) {
            itemDao = new ItemDaoHibernateImpl();
        }
        return itemDao;
    }

    public static OrderDao getOrderDao() {
        if (orderDao == null) {
            orderDao = new OrderDaoJdbcImpl(connection);
        }
        return orderDao;
    }

    public static UserDao getUserDao() {
        if (userDao == null) {
            userDao = new UserDaoJdbcImpl(connection);
        }
        return userDao;
    }

    public static ItemService getItemService() {
        if (itemService == null) {
            itemService = new ItemServiceImpl();
        }
        return itemService;
    }

    public static BucketService getBucketService() {
        if (bucketService == null) {
            bucketService = new BucketServiceImpl();
        }
        return bucketService;
    }

    public static OrderService getOrderService() {
        if (orderService == null) {
            orderService = new OrderServiceImpl();
        }
        return orderService;
    }

    public static UserService getUserService() {
        if (userService == null) {
            userService = new UserServiceImpl();
        }
        return userService;
    }
}
