package com.imk.jeedemo.servlet;

import com.imk.jeedemo.dao.UserDAO;
import com.imk.jeedemo.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/user/add")
public class UserAddServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setAttribute("action", "add");
        req.getRequestDispatcher("/WEB-INF/views/user-form.jsp").forward(req, res);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        User user = new User();
        user.setName(req.getParameter("name"));
        user.setEmail(req.getParameter("email"));
        user.setAge(Integer.parseInt(req.getParameter("age")));
        try {
            new UserDAO().insertUser(user);
            res.sendRedirect("list");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
