package mate.academy.ishop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.ishop.lib.Inject;
import mate.academy.ishop.model.Bucket;
import mate.academy.ishop.model.Order;
import mate.academy.ishop.model.User;
import mate.academy.ishop.service.BucketService;
import mate.academy.ishop.service.OrderService;

public class CompleteOrderController extends HttpServlet {
    @Inject
    private static OrderService orderService;

    @Inject
    private static BucketService bucketService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute("user");
        Bucket bucket = bucketService.get(Long.valueOf(request.getParameter("bid")));
        Order order = orderService.completeOrder(bucket.getItemsList(),user.getUserId());
        response.sendRedirect(request.getContextPath() + "/items");
    }
}
