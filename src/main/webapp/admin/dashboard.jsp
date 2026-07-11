<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="com.bank.model.Customer, com.bank.model.Employee, com.bank.service.BankService, java.util.List, java.time.format.DateTimeFormatter" %>
<%
    String adminUsername = (String) session.getAttribute("adminUsername");
    if (adminUsername == null) {
        response.sendRedirect("login.jsp?error=Please+login+first");
        return;
    }

    BankService bankService = BankService.getInstance();
    List<Customer> customers = bankService.getAllCustomers();
    List<Employee> employees = bankService.getAllEmployees();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard - Bank Management System</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
    <div class="container" style="padding-top: 20px;">
        <div class="nav-bar">
            <span>Administrator: <%= adminUsername %></span>
            <a href="../logout" class="btn btn-secondary">Logout</a>
        </div>

        <div class="card">
            <h2>System Overview</h2>
            <div class="info-grid">
                <div class="info-item">
                    <div class="label">Total Customers</div>
                    <div class="value"><%= customers.size() %></div>
                </div>
                <div class="info-item">
                    <div class="label">Total Employees</div>
                    <div class="value"><%= employees.size() %></div>
                </div>
                <div class="info-item">
                    <div class="label">Monthly Interest Rate</div>
                    <div class="value">0.5%</div>
                </div>
                <div class="info-item">
                    <div class="label">Max Deposit Limit</div>
                    <div class="value">Rs. 50,000</div>
                </div>
            </div>
        </div>

        <div class="card">
            <h2>Customer Profiles</h2>
            <% if (customers.isEmpty()) { %>
                <p style="color: #718096; text-align: center; padding: 20px;">No customers registered yet.</p>
            <% } else { %>
                <table>
                    <thead>
                        <tr>
                            <th>Customer ID</th>
                            <th>Full Name</th>
                            <th>Account No.</th>
                            <th>Email</th>
                            <th>Phone</th>
                            <th>Address</th>
                            <th>Balance (Rs.)</th>
                            <th>Registered On</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (Customer customer : customers) { %>
                            <tr>
                                <td><%= customer.getCustomerId() %></td>
                                <td><%= customer.getFullName() %></td>
                                <td><%= customer.getAccountNumber() %></td>
                                <td><%= customer.getEmail() %></td>
                                <td><%= customer.getPhone() %></td>
                                <td><%= customer.getAddress() %></td>
                                <td><%= String.format("%.2f", customer.getBalance()) %></td>
                                <td><%= customer.getRegisteredAt().format(formatter) %></td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
            <% } %>
        </div>

        <div class="card">
            <h2>Employee Profiles</h2>
            <table>
                <thead>
                    <tr>
                        <th>Employee ID</th>
                        <th>Name</th>
                        <th>Position</th>
                        <th>Joined On</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Employee employee : employees) { %>
                        <tr>
                            <td><%= employee.getEmployeeId() %></td>
                            <td><%= employee.getName() %></td>
                            <td><%= employee.getPosition() %></td>
                            <td><%= employee.getJoinedAt().format(formatter) %></td>
                        </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
