package com.bank.servlet;

import com.bank.model.Customer;
import com.bank.model.Employee;
import com.bank.service.BankService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final BankService bankService = BankService.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String role = request.getParameter("role");
        HttpSession session = request.getSession(true);

        try {
            if ("customer".equals(role)) {
                handleCustomerLogin(request, response, session);
            } else if ("employee".equals(role)) {
                handleEmployeeLogin(request, response, session);
            } else if ("admin".equals(role)) {
                handleAdminLogin(request, response, session);
            } else {
                response.sendRedirect("index.jsp?error=Invalid+login+portal");
            }
        } catch (Exception e) {
            redirectWithError(response, role, e.getMessage());
        }
    }

    private void handleCustomerLogin(HttpServletRequest request, HttpServletResponse response,
                                     HttpSession session) throws IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Optional<Customer> customer = bankService.loginCustomer(email, password);
        if (customer.isPresent()) {
            session.setAttribute("role", "customer");
            session.setAttribute("customer", customer.get());
            response.sendRedirect("customer/dashboard.jsp");
        } else {
            response.sendRedirect("customer/login.jsp?error=Invalid+customer+credentials");
        }
    }

    private void handleEmployeeLogin(HttpServletRequest request, HttpServletResponse response,
                                       HttpSession session) throws IOException {
        String employeeId = request.getParameter("employeeId");
        String name = request.getParameter("name");
        String position = request.getParameter("position");
        String password = request.getParameter("password");

        Optional<Employee> employee = bankService.loginEmployee(employeeId, name, position, password);
        if (employee.isPresent()) {
            session.setAttribute("role", "employee");
            session.setAttribute("employee", employee.get());
            response.sendRedirect("employee/dashboard.jsp");
        } else {
            response.sendRedirect("employee/login.jsp?error=Invalid+employee+credentials");
        }
    }

    private void handleAdminLogin(HttpServletRequest request, HttpServletResponse response,
                                  HttpSession session) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (bankService.loginAdmin(username, password)) {
            session.setAttribute("role", "admin");
            session.setAttribute("adminUsername", username);
            response.sendRedirect("admin/dashboard.jsp");
        } else {
            response.sendRedirect("admin/login.jsp?error=Invalid+admin+credentials");
        }
    }

    private void redirectWithError(HttpServletResponse response, String role, String message)
            throws IOException {
        String encodedMessage = message.replace(" ", "+");
        if ("customer".equals(role)) {
            response.sendRedirect("customer/login.jsp?error=" + encodedMessage);
        } else if ("employee".equals(role)) {
            response.sendRedirect("employee/login.jsp?error=" + encodedMessage);
        } else if ("admin".equals(role)) {
            response.sendRedirect("admin/login.jsp?error=" + encodedMessage);
        } else {
            response.sendRedirect("index.jsp?error=" + encodedMessage);
        }
    }
}
