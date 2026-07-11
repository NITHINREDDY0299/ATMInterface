package com.bank.servlet;

import com.bank.model.Customer;
import com.bank.service.BankService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private final BankService bankService = BankService.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        try {
            validateRegistration(fullName, email, phone, address, password, confirmPassword);
            Customer customer = bankService.registerCustomer(fullName, email, phone, address, password);

            HttpSession session = request.getSession(true);
            session.setAttribute("role", "customer");
            session.setAttribute("customer", customer);
            response.sendRedirect("customer/dashboard.jsp?success=Registration+successful");
        } catch (IllegalArgumentException e) {
            response.sendRedirect("customer/register.jsp?error=" + e.getMessage().replace(" ", "+"));
        }
    }

    private void validateRegistration(String fullName, String email, String phone,
                                      String address, String password, String confirmPassword) {
        if (isBlank(fullName) || isBlank(email) || isBlank(phone)
                || isBlank(address) || isBlank(password)) {
            throw new IllegalArgumentException("All fields are required.");
        }
        if (!password.equals(confirmPassword)) {
            throw new IllegalArgumentException("Passwords do not match.");
        }
        if (password.length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters.");
        }
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Please enter a valid email address.");
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
