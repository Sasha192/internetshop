package internetshop;

import internetshop.lib.Inject;
import internetshop.lib.Injector;
import internetshop.service.BucketService;
import internetshop.service.ItemService;
import internetshop.service.OrderService;
import internetshop.service.UserService;

public class Main {

    @Inject
    private static UserService userService;

    @Inject
    private static OrderService orderService;

    @Inject
    private static ItemService itemService;

    @Inject
    private static BucketService bucketService;

    static {
        try {
            Injector.injectDependencies();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }
}
