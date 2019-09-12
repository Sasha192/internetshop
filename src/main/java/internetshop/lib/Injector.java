package internetshop.lib;

import internetshop.dao.impl.BucketDaoImpl;
import internetshop.dao.impl.ItemDaoImpl;
import internetshop.dao.impl.OrderDaoImpl;
import internetshop.dao.impl.UserDaoImpl;
import internetshop.service.impl.BucketServiceImpl;
import internetshop.service.impl.ItemServiceImpl;
import internetshop.service.impl.OrderServiceImpl;
import internetshop.service.impl.UserServiceImpl;

public class Injector {
    private static Class<UserServiceImpl> userServiceClass = UserServiceImpl.class;
    private static Class<UserDaoImpl> userDaoClass = UserDaoImpl.class;
    private static Class<BucketServiceImpl> bucketServiceClass = BucketServiceImpl.class;
    private static Class<BucketDaoImpl> bucketDaoClass = BucketDaoImpl.class;
    private static Class<ItemDaoImpl> itemDaoClass = ItemDaoImpl.class;
    private static Class<OrderDaoImpl> orderDaoClass = OrderDaoImpl.class;
    private static Class<ItemServiceImpl> itemServiceClass = ItemServiceImpl.class;
    private static Class<OrderServiceImpl> orderServiceClass = OrderServiceImpl.class;

    public static void injectDependencies(){

    }
}
