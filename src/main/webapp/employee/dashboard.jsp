<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="com.bank.model.Employee, com.bank.model.Customer, com.bank.service.BankService, java.util.List, java.time.format.DateTimeFormatter" %>
<%
    Employee employee = (Employee) session.getAttribute("employee");
    if (employee == null) {
        response.sendRedirect("login.jsp?error=Please+login+first");
        return;
    }

    BankService bankService = BankService.getInstance();
    List<Customer> customers = bankService.getAllCustomers();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Employee Dashboard - Bank Management System</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
    <div class="container" style="padding-top: 20px;">
        <div class="nav-bar">
            <span>Employee: <%= employee.getName() %> (<%= employee.getPosition() %>)</span>
            <a href="../logout" class="btn btn-secondary">Logout</a>
        </div>

        <div class="card">
            <h2>Employee Profile</h2>
            <div class="info-grid">
                <div class="info-item">
                    <div class="label">Employee ID</div>
                    <div class="value"><%= employee.getEmployeeId() %></div>
                </div>
                <div class="info-item">
                    <div class="label">Name</div>
                    <div class="value"><%= employee.getName() %></div>
                </div>
                <div class="info-item">
                    <div class="label">Position</div>
                    <div class="value"><%= employee.getPosition() %></div>
                </div>
                <div class="info-item">
                    <div class="label">Joined On</div>
                    <div class="value"><%= employee.getJoinedAt().format(formatter) %></div>
                </div>
            </div>
        </div>

        <div class="card">
            <h2>Registered Customers</h2>
            <% if (customers.isEmpty()) { %>
                <p style="color: #718096; text-align: center; padding: 20px;">No customers registered yet.</p>
            <% } else { %>
                <table>
                    <thead>
                        <tr>
                            <th>Customer ID</th>
                            <th>Name</th>
                            <th>Account No.</th>
                            <th>Email</th>
                            <th>Phone</th>
                            <th>Balance (Rs.)</th>
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
                                <td><%= String.format("%.2f", customer.getBalance()) %></td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
            <% } %>
        </div>
    </div>
</body>
</html>
