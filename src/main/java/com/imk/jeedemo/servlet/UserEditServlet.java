package com.imk.jeedemo.servlet;

import com.imk.jeedemo.dao.UserDAO;
import com.imk.jeedemo.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/user/edit")
public class UserEditServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        try {
            User user = new UserDAO().getUserById(id);
            req.setAttribute("user", user);
            req.setAttribute("action", "edit");
            req.getRequestDispatcher("/WEB-INF/views/user-form.jsp").forward(req, res);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        User user = new User();
        user.setId(Integer.parseInt(req.getParameter("id")));
        user.setName(req.getParameter("name"));
        user.setEmail(req.getParameter("email"));
        user.setAge(Integer.parseInt(req.getParameter("age")));

        try {
            new UserDAO().updateUser(user);
            res.sendRedirect("list");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
