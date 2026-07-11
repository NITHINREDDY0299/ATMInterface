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

@WebServlet("/customer/transaction")
public class CustomerTransactionServlet extends HttpServlet {
    private final BankService bankService = BankService.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("customer") == null) {
            response.sendRedirect("../customer/login.jsp?error=Please+login+first");
            return;
        }

        Customer customer = (Customer) session.getAttribute("customer");
        String action = request.getParameter("action");

        try {
            double amount = Double.parseDouble(request.getParameter("amount"));

            if ("deposit".equals(action)) {
                bankService.deposit(customer.getAccountNumber(), amount);
                session.setAttribute("message", "Deposit of Rs. " + amount + " successful.");
            } else if ("withdraw".equals(action)) {
                bankService.withdraw(customer.getAccountNumber(), amount);
                session.setAttribute("message", "Withdrawal of Rs. " + amount + " successful.");
            } else {
                session.setAttribute("error", "Invalid transaction type.");
            }

            Customer updatedCustomer = bankService.getCustomerByAccount(customer.getAccountNumber());
            session.setAttribute("customer", updatedCustomer);
        } catch (NumberFormatException e) {
            session.setAttribute("error", "Please enter a valid amount.");
        } catch (IllegalArgumentException e) {
            session.setAttribute("error", e.getMessage());
        }

        response.sendRedirect("dashboard.jsp");
    }
}
