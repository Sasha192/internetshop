package mate.academy.ishop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.ishop.lib.Inject;
import mate.academy.ishop.model.User;
import mate.academy.ishop.service.OrderService;

public class DeleteOrderController extends HttpServlet {
    @Inject
    private static OrderService orderService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        orderService.delete(Long.valueOf(request.getParameter("orderid")));
        User user = (User) request.getSession().getAttribute("user");
        request.setAttribute("orders", user.getCompletedOrders());
        request.getRequestDispatcher("/WEB-INF/views/orders.jsp").forward(request, response);
    }
}
