package com.bank.service;

import com.bank.model.Customer;
import com.bank.model.Employee;
import com.bank.model.Transaction;
import com.bank.util.BankConstants;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class BankService {
    private static final BankService INSTANCE = new BankService();

    private final Map<String, Customer> customersByAccount = new LinkedHashMap<>();
    private final Map<String, Customer> customersByEmail = new LinkedHashMap<>();
    private final Map<String, Employee> employees = new LinkedHashMap<>();
    private final List<Transaction> transactions = new ArrayList<>();

    private final AtomicInteger customerCounter = new AtomicInteger(1000);
    private final AtomicInteger employeeCounter = new AtomicInteger(100);
    private final AtomicInteger transactionCounter = new AtomicInteger(1);

    private BankService() {
        initializeDefaultData();
    }

    public static BankService getInstance() {
        return INSTANCE;
    }

    private void initializeDefaultData() {
        Employee emp1 = new Employee("EMP101", "John Smith", "Branch Manager", "emp123");
        Employee emp2 = new Employee("EMP102", "Sarah Johnson", "Cashier", "emp123");
        employees.put(emp1.getEmployeeId(), emp1);
        employees.put(emp2.getEmployeeId(), emp2);
        employeeCounter.set(103);
    }

    public Customer registerCustomer(String fullName, String email, String phone,
                                     String address, String password) {
        if (customersByEmail.containsKey(email.toLowerCase())) {
            throw new IllegalArgumentException("Email is already registered.");
        }

        String customerId = "CUST" + customerCounter.getAndIncrement();
        String accountNumber = "ACC" + System.currentTimeMillis() % 1000000000L;

        Customer customer = new Customer(customerId, fullName, email, phone, address,
                password, accountNumber);

        customersByAccount.put(accountNumber, customer);
        customersByEmail.put(email.toLowerCase(), customer);
        return customer;
    }

    public Optional<Customer> loginCustomer(String email, String password) {
        Customer customer = customersByEmail.get(email.toLowerCase());
        if (customer != null && customer.getPassword().equals(password)) {
            applyMonthlyInterest(customer);
            return Optional.of(customer);
        }
        return Optional.empty();
    }

    public Optional<Employee> loginEmployee(String employeeId, String name, String position, String password) {
        Employee employee = employees.get(employeeId);
        if (employee != null
                && employee.getName().equalsIgnoreCase(name)
                && employee.getPosition().equalsIgnoreCase(position)
                && employee.getPassword().equals(password)) {
            return Optional.of(employee);
        }
        return Optional.empty();
    }

    public boolean loginAdmin(String username, String password) {
        return BankConstants.ADMIN_USERNAME.equals(username)
                && BankConstants.ADMIN_PASSWORD.equals(password);
    }

    public void deposit(String accountNumber, double amount) {
        validateDepositAmount(amount);

        Customer customer = getCustomerByAccount(accountNumber);
        customer.setBalance(customer.getBalance() + amount);

        addTransaction(accountNumber, Transaction.Type.DEPOSIT, amount,
                customer.getBalance(), "Cash deposit");
    }

    public void withdraw(String accountNumber, double amount) {
        validateWithdrawalAmount(amount);

        Customer customer = getCustomerByAccount(accountNumber);
        if (customer.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient balance. Available: Rs. "
                    + String.format("%.2f", customer.getBalance()));
        }

        customer.setBalance(customer.getBalance() - amount);
        addTransaction(accountNumber, Transaction.Type.WITHDRAWAL, amount,
                customer.getBalance(), "Cash withdrawal");
    }

    public void applyMonthlyInterest(Customer customer) {
        LocalDateTime lastApplied = customer.getLastInterestApplied();
        long monthsPassed = ChronoUnit.MONTHS.between(lastApplied, LocalDateTime.now());

        if (monthsPassed <= 0 || customer.getBalance() <= 0) {
            return;
        }

        double interest = customer.getBalance() * BankConstants.MONTHLY_INTEREST_RATE * monthsPassed;
        customer.setBalance(customer.getBalance() + interest);
        customer.setLastInterestApplied(LocalDateTime.now());

        addTransaction(customer.getAccountNumber(), Transaction.Type.INTEREST, interest,
                customer.getBalance(),
                "Monthly interest (" + monthsPassed + " month(s) @ "
                        + (BankConstants.MONTHLY_INTEREST_RATE * 100) + "%)");
    }

    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customersByAccount.values());
    }

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees.values());
    }

    public List<Transaction> getTransactionsByAccount(String accountNumber) {
        List<Transaction> accountTransactions = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getAccountNumber().equals(accountNumber)) {
                accountTransactions.add(transaction);
            }
        }
        Collections.reverse(accountTransactions);
        return accountTransactions;
    }

    public Customer getCustomerByAccount(String accountNumber) {
        Customer customer = customersByAccount.get(accountNumber);
        if (customer == null) {
            throw new IllegalArgumentException("Account not found.");
        }
        return customer;
    }

    private void validateDepositAmount(double amount) {
        if (amount < BankConstants.MIN_DEPOSIT_AMOUNT) {
            throw new IllegalArgumentException("Minimum deposit amount is Rs. "
                    + BankConstants.MIN_DEPOSIT_AMOUNT);
        }
        if (amount > BankConstants.MAX_DEPOSIT_AMOUNT) {
            throw new IllegalArgumentException("Maximum deposit allowed per transaction is Rs. "
                    + BankConstants.MAX_DEPOSIT_AMOUNT);
        }
    }

    private void validateWithdrawalAmount(double amount) {
        if (amount < BankConstants.MIN_WITHDRAWAL_AMOUNT) {
            throw new IllegalArgumentException("Minimum withdrawal amount is Rs. "
                    + BankConstants.MIN_WITHDRAWAL_AMOUNT);
        }
    }

    private void addTransaction(String accountNumber, Transaction.Type type,
                                double amount, double balanceAfter, String description) {
        String transactionId = "TXN" + transactionCounter.getAndIncrement();
        transactions.add(new Transaction(transactionId, accountNumber, type, amount,
                balanceAfter, description));
    }
}
