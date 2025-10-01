package com.imk.jeedemo.servlet;

import com.imk.jeedemo.dao.UserDAO;
import com.imk.jeedemo.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/user/list")
public class UserListServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            List<User> users = new UserDAO().getAllUsers();
            req.setAttribute("users", users);
            System.out.println("users list"+ users);
            req.getRequestDispatcher("/WEB-INF/views/user-list.jsp").forward(req, res);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


