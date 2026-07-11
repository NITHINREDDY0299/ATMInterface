<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Registration - Bank Management System</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
    <div class="header">
        <h1>Customer Registration</h1>
        <p>Create your bank account</p>
    </div>

    <div class="container auth-container">
        <div class="card">
            <% if (request.getParameter("error") != null) { %>
                <div class="alert alert-error"><%= request.getParameter("error") %></div>
            <% } %>

            <form action="../register" method="post">
                <div class="form-group">
                    <label for="fullName">Full Name</label>
                    <input type="text" id="fullName" name="fullName" required placeholder="Enter your full name">
                </div>

                <div class="form-group">
                    <label for="email">Email Address</label>
                    <input type="email" id="email" name="email" required placeholder="Enter your email">
                </div>

                <div class="form-group">
                    <label for="phone">Phone Number</label>
                    <input type="tel" id="phone" name="phone" required placeholder="Enter your phone number">
                </div>

                <div class="form-group">
                    <label for="address">Address</label>
                    <textarea id="address" name="address" required placeholder="Enter your full address"></textarea>
                </div>

                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" required placeholder="Minimum 6 characters">
                </div>

                <div class="form-group">
                    <label for="confirmPassword">Confirm Password</label>
                    <input type="password" id="confirmPassword" name="confirmPassword" required placeholder="Re-enter password">
                </div>

                <button type="submit" class="btn btn-success" style="width: 100%;">Register</button>
            </form>

            <div class="auth-links">
                <p>Already have an account? <a href="login.jsp">Login here</a></p>
                <p><a href="../index.jsp">&larr; Back to Home</a></p>
            </div>
        </div>
    </div>
</body>
</html>
