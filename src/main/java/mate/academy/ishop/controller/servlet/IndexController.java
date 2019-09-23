package mate.academy.ishop.controller.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.ishop.lib.Inject;
import mate.academy.ishop.lib.Injector;
import mate.academy.ishop.service.BucketService;
import mate.academy.ishop.service.ItemService;
import mate.academy.ishop.service.OrderService;
import mate.academy.ishop.service.UserService;

public class IndexController extends HttpServlet {

    static {
        try {
            Injector.injectDependencies();
        } catch (IllegalAccessException exception) {
            System.out.println("Main");
            exception.printStackTrace();
        }
    }

    @Inject
    private static UserService userService;

    @Inject
    private static OrderService orderService;

    @Inject
    private static ItemService itemService;

    @Inject
    private static BucketService bucketService;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
    }
}
