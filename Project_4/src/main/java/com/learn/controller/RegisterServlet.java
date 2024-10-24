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
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    UserDAO userDAO = new UserDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String error = UserUtil.validateRequest(request); // Validate request
        HttpSession session = request.getSession();

        if (error == null) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");

            // Check if the username already exists
            if (userDAO.checkUsernameExists(username)) {
                error = "Username is already taken. Please choose another one.";
                session.setAttribute("error", error); // Store error in session
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("register.jsp");
                requestDispatcher.forward(request, response);
            } else {
                // Register the new user
                UserBean userBean = new UserBean();
                userBean.setUsername(username);
                userBean.setPassword(password);
                userBean.setFirstName(firstName);
                userBean.setLastName(lastName);

                boolean isUserRegistered = userDAO.insertUser(userBean);
                if (isUserRegistered) {
                    // Registration successful, clear the error attribute
                    session.removeAttribute("error");  // Clear the error from the session
                    session.setAttribute("firstName", userBean.getFirstName());
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
                    requestDispatcher.forward(request, response);
                }
            }
        }

        if (error != null) {
            // Validation error (e.g., empty fields), or username already taken
            session.setAttribute("error", error);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("register.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
