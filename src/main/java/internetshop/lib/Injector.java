package internetshop.lib;

import internetshop.Factory;
import internetshop.Main;
import internetshop.dao.BucketDao;
import internetshop.dao.ItemDao;
import internetshop.dao.OrderDao;
import internetshop.dao.UserDao;
import internetshop.dao.impl.BucketDaoImpl;
import internetshop.dao.impl.ItemDaoImpl;
import internetshop.dao.impl.OrderDaoImpl;
import internetshop.dao.impl.UserDaoImpl;
import internetshop.service.BucketService;
import internetshop.service.ItemService;
import internetshop.service.OrderService;
import internetshop.service.UserService;
import internetshop.service.impl.BucketServiceImpl;
import internetshop.service.impl.ItemServiceImpl;
import internetshop.service.impl.OrderServiceImpl;
import internetshop.service.impl.UserServiceImpl;

import java.lang.reflect.Field;

public class Injector {
    private static Class<UserServiceImpl> userServiceClass = UserServiceImpl.class;
    private static Class<UserDaoImpl> userDaoClass = UserDaoImpl.class;
    private static Class<BucketServiceImpl> bucketServiceClass = BucketServiceImpl.class;
    private static Class<BucketDaoImpl> bucketDaoClass = BucketDaoImpl.class;
    private static Class<ItemDaoImpl> itemDaoClass = ItemDaoImpl.class;
    private static Class<OrderDaoImpl> orderDaoClass = OrderDaoImpl.class;
    private static Class<ItemServiceImpl> itemServiceClass = ItemServiceImpl.class;
    private static Class<OrderServiceImpl> orderServiceClass = OrderServiceImpl.class;

    public static void injectDependencies() throws IllegalAccessException {
        injectToItemService();
        injectToBucketService();
        injectToOrderService();
        injectToUserService();
        injectToMain();
    }

    private static void injectToItemService() throws IllegalAccessException {
        for (Field field : ItemServiceImpl.class.getDeclaredFields()) {
            if (field.getDeclaredAnnotation(Inject.class) != null
                    && field.getType() == ItemDao.class
                    && ItemDao.class.getDeclaredAnnotation(Dao.class) != null) {
                field.setAccessible(true);
                field.set(null, Factory.getItemDao());
            }
        }
    }

    private static void injectToBucketService() throws IllegalAccessException {
        for (Field field : BucketServiceImpl.class.getDeclaredFields()) {
            if (field.getDeclaredAnnotation(Inject.class) != null
                    && field.getType() == BucketDao.class
                    && BucketDao.class.getDeclaredAnnotation(Dao.class) != null) {
                field.setAccessible(true);
                field.set(null, Factory.getBucketDao());
            }
            if (field.getDeclaredAnnotation(Inject.class) != null
                    && field.getType() == ItemDao.class
                    && ItemDao.class.getDeclaredAnnotation(Dao.class) != null) {
                field.setAccessible(true);
                field.set(null, Factory.getItemDao());
            }
        }
    }

    private static void injectToOrderService() throws IllegalAccessException {
        for (Field field : OrderServiceImpl.class.getDeclaredFields()) {
            if (field.getDeclaredAnnotation(Inject.class) != null
                    && field.getType() == UserDao.class
                    && UserDao.class.getDeclaredAnnotation(Dao.class) != null) {
                field.setAccessible(true);
                field.set(null, Factory.getUserDao());
            }
            if (field.getDeclaredAnnotation(Inject.class) != null
                    && field.getType() == OrderDao.class
                    && OrderDao.class.getDeclaredAnnotation(Dao.class) != null) {
                field.setAccessible(true);
                field.set(null, Factory.getOrderDao());
            }
        }
    }

    private static void injectToUserService() throws IllegalAccessException {
        for (Field field : UserServiceImpl.class.getDeclaredFields()) {
            if (field.getDeclaredAnnotation(Inject.class) != null
                    && field.getType() == UserDao.class
                    && UserDao.class.getDeclaredAnnotation(Dao.class) != null) {
                field.setAccessible(true);
                field.set(null, Factory.getItemDao());
            }
        }
    }

    private static void injectToMain() throws IllegalAccessException {
        for (Field field : Main.class.getDeclaredFields()) {
            if (field.getDeclaredAnnotation(Inject.class) != null
                    && field.getType() == UserService.class
                    && UserService.class.getDeclaredAnnotation(Service.class) != null) {
                field.setAccessible(true);
                field.set(null, Factory.getUserService());
            }
            if (field.getDeclaredAnnotation(Inject.class) != null
                    && field.getType() == OrderService.class
                    && OrderService.class.getDeclaredAnnotation(Service.class) != null) {
                field.setAccessible(true);
                field.set(null, Factory.getOrderService());
            }
            if (field.getDeclaredAnnotation(Inject.class) != null
                    && field.getType() == ItemService.class
                    && ItemService.class.getDeclaredAnnotation(Service.class) != null) {
                field.setAccessible(true);
                field.set(null, Factory.getItemService());
            }
            if (field.getDeclaredAnnotation(Inject.class) != null
                    && field.getType() == BucketService.class
                    && BucketService.class.getDeclaredAnnotation(Service.class) != null) {
                field.setAccessible(true);
                field.set(null, Factory.getBucketService());
            }
        }
    }
}
