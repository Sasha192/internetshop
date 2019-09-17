package mate.academy.ishop.lib;

import mate.academy.ishop.Factory;
import mate.academy.ishop.dao.BucketDao;
import mate.academy.ishop.dao.ItemDao;
import mate.academy.ishop.dao.OrderDao;
import mate.academy.ishop.dao.UserDao;
import mate.academy.ishop.service.BucketService;
import mate.academy.ishop.service.ItemService;
import mate.academy.ishop.service.OrderService;
import mate.academy.ishop.service.UserService;

import java.util.HashMap;

public class AnnotatedClassMap {
    private static final HashMap<Class, Object> annotatedClassMap = new HashMap<>();

    static {
        annotatedClassMap.put(ItemDao.class, Factory.getItemDao());
        annotatedClassMap.put(BucketDao.class, Factory.getBucketDao());
        annotatedClassMap.put(OrderDao.class, Factory.getOrderDao());
        annotatedClassMap.put(UserDao.class, Factory.getUserDao());
        annotatedClassMap.put(ItemService.class, Factory.getItemService());
        annotatedClassMap.put(BucketService.class, Factory.getBucketService());
        annotatedClassMap.put(OrderService.class, Factory.getOrderService());
        annotatedClassMap.put(UserService.class, Factory.getUserService());
    }

    public static Object getImplementation(Class certainClass){
        return annotatedClassMap.get(certainClass);
    }
}
