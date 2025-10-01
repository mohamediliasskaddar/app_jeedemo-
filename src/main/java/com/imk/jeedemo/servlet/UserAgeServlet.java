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

@WebServlet("/userage")
public class UserAgeServlet extends HttpServlet {
    //doPost
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String ageParam = req.getParameter("age");
        if (ageParam != null && !ageParam.isEmpty()) {
            try {
                int age = Integer.parseInt(ageParam);
                List<User> usersAge = new UserDAO().getUsersWithAge(age);
                req.setAttribute("usersAge", usersAge);
                System.out.println("users list" + usersAge);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            req.getRequestDispatcher("/WEB-INF/views/user-age.jsp").forward(req, res);
        }
    }
    //doGet
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/user-age.jsp").forward(req, res);
    }
}