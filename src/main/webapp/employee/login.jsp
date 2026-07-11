<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Employee Login - Bank Management System</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
    <div class="header">
        <h1>Employee Login</h1>
        <p>Bank Employee Portal</p>
    </div>

    <div class="container auth-container">
        <div class="card">
            <% if (request.getParameter("error") != null) { %>
                <div class="alert alert-error"><%= request.getParameter("error") %></div>
            <% } %>

            <div class="alert alert-info">
                <strong>Demo Credentials:</strong><br>
                Employee ID: EMP101 | Name: John Smith | Position: Branch Manager | Password: emp123<br>
                Employee ID: EMP102 | Name: Sarah Johnson | Position: Cashier | Password: emp123
            </div>

            <form action="../login" method="post">
                <input type="hidden" name="role" value="employee">

                <div class="form-group">
                    <label for="employeeId">Employee ID</label>
                    <input type="text" id="employeeId" name="employeeId" required placeholder="Enter employee ID">
                </div>

                <div class="form-group">
                    <label for="name">Name</label>
                    <input type="text" id="name" name="name" required placeholder="Enter your name">
                </div>

                <div class="form-group">
                    <label for="position">Position</label>
                    <input type="text" id="position" name="position" required placeholder="Enter your position">
                </div>

                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" required placeholder="Enter your password">
                </div>

                <button type="submit" class="btn btn-primary" style="width: 100%;">Login</button>
            </form>

            <div class="auth-links">
                <p><a href="../index.jsp">&larr; Back to Home</a></p>
            </div>
        </div>
    </div>
</body>
</html>
