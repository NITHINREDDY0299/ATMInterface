<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Login - Bank Management System</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
    <div class="header">
        <h1>Admin Login</h1>
        <p>Administrator Portal</p>
    </div>

    <div class="container auth-container">
        <div class="card">
            <% if (request.getParameter("error") != null) { %>
                <div class="alert alert-error"><%= request.getParameter("error") %></div>
            <% } %>

            <div class="alert alert-info">
                <strong>Demo Credentials:</strong><br>
                Username: admin | Password: admin123
            </div>

            <form action="../login" method="post">
                <input type="hidden" name="role" value="admin">

                <div class="form-group">
                    <label for="username">Username</label>
                    <input type="text" id="username" name="username" required placeholder="Enter admin username">
                </div>

                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" required placeholder="Enter admin password">
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
