package com.expensetracker;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("fullName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (name == null || email == null || password == null ||
            name.trim().isEmpty() || email.trim().isEmpty() || password.length() < 8) {
            response.sendRedirect("register.html?error=invalid");
            return;
        }

        String sql = "INSERT INTO users (full_name, email, password_hash) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name.trim());
            ps.setString(2, email.trim().toLowerCase());
            ps.setString(3, PasswordUtil.hash(password));
            ps.executeUpdate();
            response.sendRedirect("login.html?registered=1");
        } catch (SQLException e) {
            if ("23000".equals(e.getSQLState())) {
                response.sendRedirect("register.html?error=exists");
            } else {
                throw new ServletException("Could not register user. Check database setup.", e);
            }
        } catch (Exception e) {
            throw new ServletException("Could not secure password.", e);
        }
    }
}
