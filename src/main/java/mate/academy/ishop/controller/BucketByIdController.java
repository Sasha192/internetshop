package mate.academy.ishop.controller;

import mate.academy.ishop.dao.Storage;
import mate.academy.ishop.lib.Inject;
import mate.academy.ishop.model.User;
import mate.academy.ishop.service.BucketService;
import mate.academy.ishop.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class BucketByIdController extends HttpServlet {
    @Inject
    private static UserService userService;

    @Inject
    private static BucketService bucketService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        User user = new User("Anonymous");
        user.setCurrentBucket(bucketService.get(Long.valueOf(request.getParameter("bucket"))));
        session.setAttribute("user", user);
        userService.add(user);
        response.sendRedirect(request.getContextPath() + "/user/bucket");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("max", Integer.valueOf(Storage.buckets.size() - 1));
        request.getRequestDispatcher("/WEB-INF/views/bucketbyid.jsp").forward(request, response);
    }
}