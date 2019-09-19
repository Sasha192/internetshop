package mate.academy.ishop.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mate.academy.ishop.dao.Storage;
import mate.academy.ishop.lib.Inject;
import mate.academy.ishop.model.Order;
import mate.academy.ishop.model.User;
import mate.academy.ishop.service.OrderService;
import mate.academy.ishop.service.UserService;


public class OrderIdController extends HttpServlet {
    @Inject
    private static OrderService orderService;

    @Inject
    private static UserService userService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        User user = new User("Anonymous");
        List<Order> list = userService.getOrders(Long.valueOf(request.getParameter("order")));
        user.setCompletedOrders(list);
        session.setAttribute("user", user);
        userService.add(user);
        response.sendRedirect(request.getContextPath() + "/user/showOrders");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("max", Integer.valueOf(Storage.orders.size() - 1));
        request.getRequestDispatcher("/WEB-INF/views/orderbyid.jsp").forward(request, response);
    }
}
