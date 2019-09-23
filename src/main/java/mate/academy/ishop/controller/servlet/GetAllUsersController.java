package mate.academy.ishop.controller.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.ishop.lib.Inject;
import mate.academy.ishop.model.User;
import mate.academy.ishop.service.UserService;

public class GetAllUsersController extends HttpServlet {
    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<User> list = userService.getAll();
        req.setAttribute("users", list);
        req.setAttribute("greeting", "Mates");
        req.getRequestDispatcher("/WEB-INF/views/users.jsp").forward(req, resp);
    }
}
