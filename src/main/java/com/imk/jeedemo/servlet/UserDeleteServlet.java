package com.imk.jeedemo.servlet;

import com.imk.jeedemo.dao.UserDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet("/user/delete")
public class UserDeleteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        try {
            new UserDAO().deleteUser(id);
            res.sendRedirect("list");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
