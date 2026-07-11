<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bank Management System</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="header">
        <h1>Bank Management System</h1>
        <p>Secure Digital Banking Platform</p>
    </div>

    <div class="container">
        <% if (request.getParameter("error") != null) { %>
            <div class="alert alert-error"><%= request.getParameter("error") %></div>
        <% } %>
        <% if (request.getParameter("success") != null) { %>
            <div class="alert alert-success"><%= request.getParameter("success") %></div>
        <% } %>

        <div class="portal-grid">
            <div class="portal-card">
                <div class="icon">&#128100;</div>
                <h3>Customer Portal</h3>
                <p>Register, login, deposit money, withdraw funds, and view your account balance with monthly interest.</p>
                <a href="customer/login.jsp" class="btn btn-primary">Customer Login</a>
                <br><br>
                <a href="customer/register.jsp" class="btn btn-success">New Registration</a>
            </div>

            <div class="portal-card">
                <div class="icon">&#128188;</div>
                <h3>Employee Portal</h3>
                <p>Bank employees can login using their Employee ID, name, and position to access the employee dashboard.</p>
                <a href="employee/login.jsp" class="btn btn-primary">Employee Login</a>
            </div>

            <div class="portal-card">
                <div class="icon">&#128737;</div>
                <h3>Admin Portal</h3>
                <p>Administrator can view profiles of all registered customers and bank employees in the system.</p>
                <a href="admin/login.jsp" class="btn btn-primary">Admin Login</a>
            </div>
        </div>

        <div class="card" style="margin-top: 30px;">
            <h2>System Features</h2>
            <div class="info-grid">
                <div class="info-item">
                    <div class="label">Max Deposit</div>
                    <div class="value">Rs. 50,000 per transaction</div>
                </div>
                <div class="info-item">
                    <div class="label">Min Deposit</div>
                    <div class="value">Rs. 100</div>
                </div>
                <div class="info-item">
                    <div class="label">Monthly Interest</div>
                    <div class="value">0.5% on account balance</div>
                </div>
                <div class="info-item">
                    <div class="label">Withdrawal Rule</div>
                    <div class="value">Only if balance is sufficient</div>
                </div>
            </div>
        </div>
    </div>

    <div class="footer">
        <p>Bank Management System &copy; 2026 | Java Major Project</p>
    </div>
</body>
</html>
