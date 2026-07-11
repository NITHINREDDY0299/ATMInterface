<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="com.bank.model.Customer, com.bank.model.Transaction, com.bank.service.BankService, java.util.List, java.time.format.DateTimeFormatter" %>
<%
    Customer customer = (Customer) session.getAttribute("customer");
    if (customer == null) {
        response.sendRedirect("login.jsp?error=Please+login+first");
        return;
    }

    BankService bankService = BankService.getInstance();
    customer = bankService.getCustomerByAccount(customer.getAccountNumber());
    session.setAttribute("customer", customer);

    List<Transaction> transactions = bankService.getTransactionsByAccount(customer.getAccountNumber());
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Dashboard - Bank Management System</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
    <div class="container" style="padding-top: 20px;">
        <div class="nav-bar">
            <span>Welcome, <%= customer.getFullName() %></span>
            <a href="../logout" class="btn btn-secondary">Logout</a>
        </div>

        <% if (session.getAttribute("error") != null) { %>
            <div class="alert alert-error"><%= session.getAttribute("error") %></div>
            <% session.removeAttribute("error"); %>
        <% } %>
        <% if (session.getAttribute("message") != null) { %>
            <div class="alert alert-success"><%= session.getAttribute("message") %></div>
            <% session.removeAttribute("message"); %>
        <% } %>
        <% if (request.getParameter("success") != null) { %>
            <div class="alert alert-success"><%= request.getParameter("success") %></div>
        <% } %>

        <div class="balance-card">
            <div class="label">Current Account Balance</div>
            <div class="amount">Rs. <%= String.format("%.2f", customer.getBalance()) %></div>
            <div class="label">Account: <%= customer.getAccountNumber() %></div>
        </div>

        <div class="card">
            <h2>Account Details</h2>
            <div class="info-grid">
                <div class="info-item">
                    <div class="label">Customer ID</div>
                    <div class="value"><%= customer.getCustomerId() %></div>
                </div>
                <div class="info-item">
                    <div class="label">Email</div>
                    <div class="value"><%= customer.getEmail() %></div>
                </div>
                <div class="info-item">
                    <div class="label">Phone</div>
                    <div class="value"><%= customer.getPhone() %></div>
                </div>
                <div class="info-item">
                    <div class="label">Address</div>
                    <div class="value"><%= customer.getAddress() %></div>
                </div>
            </div>
        </div>

        <div class="card">
            <h2>Transactions</h2>
            <div class="alert alert-info">
                Deposit limit: Rs. 100 - Rs. 50,000 per transaction. Withdrawals allowed only with sufficient balance.
                Monthly interest of 0.5% is applied automatically.
            </div>

            <div class="transaction-forms">
                <div class="transaction-form">
                    <h3>Deposit Money</h3>
                    <form action="transaction" method="post">
                        <input type="hidden" name="action" value="deposit">
                        <div class="form-group">
                            <label for="depositAmount">Amount (Rs.)</label>
                            <input type="number" id="depositAmount" name="amount" min="100" max="50000" step="0.01" required placeholder="Enter deposit amount">
                        </div>
                        <button type="submit" class="btn btn-success" style="width: 100%;">Deposit</button>
                    </form>
                </div>

                <div class="transaction-form">
                    <h3>Withdraw Money</h3>
                    <form action="transaction" method="post">
                        <input type="hidden" name="action" value="withdraw">
                        <div class="form-group">
                            <label for="withdrawAmount">Amount (Rs.)</label>
                            <input type="number" id="withdrawAmount" name="amount" min="100" step="0.01" required placeholder="Enter withdrawal amount">
                        </div>
                        <button type="submit" class="btn btn-danger" style="width: 100%;">Withdraw</button>
                    </form>
                </div>
            </div>
        </div>

        <div class="card">
            <h2>Transaction History</h2>
            <% if (transactions.isEmpty()) { %>
                <p style="color: #718096; text-align: center; padding: 20px;">No transactions yet.</p>
            <% } else { %>
                <table>
                    <thead>
                        <tr>
                            <th>Transaction ID</th>
                            <th>Type</th>
                            <th>Amount (Rs.)</th>
                            <th>Balance After</th>
                            <th>Date & Time</th>
                            <th>Description</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (Transaction txn : transactions) { %>
                            <tr>
                                <td><%= txn.getTransactionId() %></td>
                                <td>
                                    <span class="badge badge-<%= txn.getType().name().toLowerCase() %>">
                                        <%= txn.getType().name() %>
                                    </span>
                                </td>
                                <td><%= String.format("%.2f", txn.getAmount()) %></td>
                                <td><%= String.format("%.2f", txn.getBalanceAfter()) %></td>
                                <td><%= txn.getTimestamp().format(formatter) %></td>
                                <td><%= txn.getDescription() %></td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
            <% } %>
        </div>
    </div>
</body>
</html>
