package com.learn.controller;

import java.io.IOException;

import com.learn.bean.UserBean;
import com.learn.dao.UserDAO;
import com.learn.util.UserUtil;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    UserDAO userDAO = new UserDAO(); // Direct interaction with the DAO

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String error = UserUtil.validateRequest(request); // Validate the request
        HttpSession session = request.getSession();

        if (error == null) {
            // Get username and password from the login form
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            // Create a new UserBean with the submitted credentials
            UserBean userBean = new UserBean();
            userBean.setUsername(username);
            userBean.setPassword(password);

            // Check if the user exists and credentials are correct
            UserBean authenticatedUser = userDAO.getUserBean(userBean);

            if (authenticatedUser != null && authenticatedUser.getFirstName() != null) {
                // Successful login
                session.setAttribute("firstName", authenticatedUser.getFirstName());
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("success.jsp");
                requestDispatcher.forward(request, response);
            } else {
                // Invalid credentials
                error = "Invalid username or password";
                session.setAttribute("error", error);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
                requestDispatcher.forward(request, response);
            }
        } else {
            // Validation error (empty fields)
            session.setAttribute("error", error);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
