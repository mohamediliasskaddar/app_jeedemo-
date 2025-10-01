package com.imk.jeedemo.dao;

import com.imk.jeedemo.utils.DBConnection;
import com.imk.jeedemo.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDAO {
    // Insert a new user into the database
    public void insertUser(User user) throws Exception {
        String sql = "INSERT INTO users (name, email, age) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setInt(3, user.getAge());
            stmt.executeUpdate();
        }
    }

    // Retrieve all users from the database
    public List<User> getAllUsers() throws Exception {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                User user = new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getInt("age")
                );
                users.add(user);
            }
        }
        return users;
    }

    //getUsers with age
    public List<User> getUsersWithAge(int age) throws Exception {
        List<User> usersAges = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE age = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, age); // ici on remplace le ? par la valeur de l’âge

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    User user = new User(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getInt("age")
                    );
                    usersAges.add(user);
                }
            }
        }
        return usersAges;
    }



    // Get a single user by ID
    public User getUserById(int id) throws Exception {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getInt("age")
                    );
                }
            }
        }
        return null; // If user is not found
    }

    // Delete a user by ID
    public void deleteUser(int id) throws Exception {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // Update a user's data
    public void updateUser(User user) throws Exception {
        String sql = "UPDATE users SET name = ?, email = ?, age = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setInt(3, user.getAge());
            stmt.setInt(4, user.getId());
            stmt.executeUpdate();
        }
    }
}
