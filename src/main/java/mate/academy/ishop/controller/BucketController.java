package mate.academy.ishop.controller;

import mate.academy.ishop.lib.Inject;
import mate.academy.ishop.model.Bucket;
import mate.academy.ishop.model.User;
import mate.academy.ishop.service.BucketService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BucketController extends HttpServlet {
    @Inject
    private static BucketService bucketService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute("user");
        request.setAttribute("bucket", user.getCurrentBucket());
        request.getRequestDispatcher("/WEB-INF/views/bucket.jsp").forward(request, response);
    }
}