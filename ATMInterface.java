import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class ATMInterface {
    private static final Scanner scanner = new Scanner(System.in);
    private static Map<String, User> users = new HashMap<>();
    private static User currentUser = null;

    public static void main(String[] args) {
        System.out.println("Welcome to the Java ATM Machine!");

        boolean exitProgram = false;
        while (!exitProgram) {
            System.out.println("\n1. Signup");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Select an option (1-3): ");
            int option = getChoice();

            switch (option) {
                case 1:
                    signup();
                    break;
                case 2:
                    if (login()) {
                        atmMenu();
                    }
                    break;
                case 3:
                    System.out.println("Thank you for using our ATM. Goodbye!");
                    exitProgram = true;
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }

    private static void signup() {
        System.out.print("Enter a username: ");
        String username = scanner.nextLine().trim();
        if (users.containsKey(username)) {
            System.out.println("Username already exists. Please try a different username.");
            return;
        }

        System.out.print("Enter a 4-digit PIN: ");
        String pin = scanner.nextLine().trim();
        if (!isValidPin(pin)) {
            System.out.println("Invalid PIN format. PIN must be 4 digits.");
            return;
        }

        users.put(username, new User(username, pin, 0.0));
        System.out.println("Signup successful. You can now login.");
    }

    private static boolean login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine().trim();

        if (!users.containsKey(username)) {
            System.out.println("Username not found.");
            return false;
        }

        int attempts = 0;
        while (attempts < 3) {
            System.out.print("Enter PIN: ");
            String pin = scanner.nextLine().trim();
            User user = users.get(username);
            if (user.getPin().equals(pin)) {
                currentUser = user;
                System.out.println("Login successful.\n");
                return true;
            } else {
                System.out.println("Incorrect PIN. Please try again.");
                attempts++;
            }
        }
        System.out.println("Too many incorrect attempts.");
        return false;
    }

    private static void atmMenu() {
        boolean exit = false;
        while (!exit) {
            displayMenu();
            int choice = getChoice();

            switch (choice) {
                case 1:
                    deposit();
                    break;
                case 2:
                    withdraw();
                    break;
                case 3:
                    checkBalance();
                    break;
                case 4:
                    changePin();
                    break;
                case 5:
                    System.out.println("Logging out...");
                    currentUser = null;
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\nATM Main Menu:");
        System.out.println("1. Deposit Amount");
        System.out.println("2. Withdraw Amount");
        System.out.println("3. Check Balance");
        System.out.println("4. Change PIN");
        System.out.println("5. Logout");
        System.out.print("Select an option (1-5): ");
    }

    private static int getChoice() {
        String input = scanner.nextLine().trim();
        try {
            int choice = Integer.parseInt(input);
            return choice;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void deposit() {
        System.out.print("Enter amount to deposit: ");
        String input = scanner.nextLine().trim();

        try {
            double amount = Double.parseDouble(input);
            if (amount <= 0) {
                System.out.println("Amount must be positive.");
                return;
            }
            currentUser.deposit(amount);
            System.out.printf("Successfully deposited $%.2f%n", amount);
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount entered.");
        }
    }

    private static void withdraw() {
        System.out.print("Enter amount to withdraw: ");
        String input = scanner.nextLine().trim();

        try {
            double amount = Double.parseDouble(input);
            if (amount <= 0) {
                System.out.println("Amount must be positive.");
                return;
            }
            if (!currentUser.withdraw(amount)) {
                System.out.println("Insufficient balance for this withdrawal.");
                return;
            }
            System.out.printf("Successfully withdrew $%.2f%n", amount);
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount entered.");
        }
    }

    private static void checkBalance() {
        System.out.printf("Your current balance is: $%.2f%n", currentUser.getBalance());
    }

    private static void changePin() {
        System.out.print("Enter current PIN: ");
        String currentPin = scanner.nextLine().trim();

        if (!currentPin.equals(currentUser.getPin())) {
            System.out.println("Incorrect current PIN. PIN change aborted.");
            return;
        }

        System.out.print("Enter new 4-digit PIN: ");
        String newPin = scanner.nextLine().trim();

        if (!isValidPin(newPin)) {
            System.out.println("Invalid PIN format. PIN must be 4 digits.");
            return;
        }

        System.out.print("Confirm new PIN: ");
        String confirmPin = scanner.nextLine().trim();

        if (!newPin.equals(confirmPin)) {
            System.out.println("PIN confirmation does not match. PIN change aborted.");
            return;
        }

        currentUser.setPin(newPin);
        System.out.println("PIN successfully changed.");
    }

    private static boolean isValidPin(String input) {
        if (input.length() != 4) return false;
        for (char c : input.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }
}

