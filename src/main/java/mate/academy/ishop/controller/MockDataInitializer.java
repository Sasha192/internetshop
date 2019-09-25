package mate.academy.ishop.controller;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import mate.academy.ishop.lib.Inject;
import mate.academy.ishop.model.Bucket;
import mate.academy.ishop.model.Item;
import mate.academy.ishop.model.Order;
import mate.academy.ishop.model.Role;
import mate.academy.ishop.model.User;
import mate.academy.ishop.service.BucketService;
import mate.academy.ishop.service.ItemService;
import mate.academy.ishop.service.OrderService;
import mate.academy.ishop.service.UserService;
import org.apache.log4j.Logger;

public class MockDataInitializer implements ServletContextListener {
    @Inject
    private static ItemService itemService;

    @Inject
    private static UserService userService;

    @Inject
    private static OrderService orderService;

    @Inject
    private static BucketService bucketService;

    private static final Logger logger = Logger.getLogger(InjectorInitializer.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        logger.info("MockDataInitializer started ...");
        for (int i = 0; i < 10; i++) {
            Item item = new Item(String.valueOf(i), Double.valueOf(i));
            User user = new User(String.valueOf(i));
            if(i == 0){
                user.addRole(Role.of("ADMIN"));
                user.setPassword("0");
            }
            Bucket bucket = new Bucket(user);
            Order order = new Order(bucket.getItemsList(), user);
            user.setCurrentBucket(bucket);
            user.getCompletedOrders().add(order);
            userService.add(user);
            bucketService.add(bucket);
            orderService.add(order);
            itemService.add(item);
        }
        logger.info("MockDataInitializer ended ...");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
