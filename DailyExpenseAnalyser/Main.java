
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Load previously saved expenses
        ArrayList<Expense> expenseList = FileHandler.loadExpenses();

        ExpenseManager manager = new ExpenseManager(expenseList);

        System.out.println("==================================");
        System.out.println("      DAILY EXPENSE ANALYZER");
        System.out.println("==================================");

        boolean running = true;

        while (running) {

            System.out.println("\n---------- MENU ----------");
            System.out.println("1. Add Expense");
            System.out.println("2. Display All Expenses");
            System.out.println("3. Sort by Amount (High to Low)");
            System.out.println("4. Search by Category");
            System.out.println("5. Total Expense");
            System.out.println("6. Exit");
            System.out.println("--------------------------");

            System.out.print("Enter your choice: ");

            String input = sc.nextLine().trim();

            int choice;

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Enter a number from 1 to 6.");
                continue;
            }

            switch (choice) {

                case 1:

                    System.out.print("Enter category: ");
                    String category = sc.nextLine().trim();

                    if (category.isEmpty() || category.contains("|")) {
                        System.out.println("Invalid category.");
                        break;
                    }

                    double amount;

                    while (true) {

                        System.out.print("Enter amount: ");
                        String amountInput = sc.nextLine().trim();

                        try {
                            amount = Double.parseDouble(amountInput);

                            if (!Double.isFinite(amount) || amount <= 0) {
                                System.out.println(
                                    "Amount must be greater than zero."
                                );
                                continue;
                            }

                            break;

                        } catch (NumberFormatException e) {
                            System.out.println(
                                "Invalid amount. Please enter a number."
                            );
                        }
                    }

                    manager.addExpense(category, amount);
                    break;

                case 2:

                    manager.displayAll();
                    break;

                case 3:

                    manager.sortByAmount();
                    break;

                case 4:

                    System.out.print("Enter category to search: ");

                    String searchCategory = sc.nextLine().trim();

                    if (searchCategory.isEmpty()) {
                        System.out.println("Category cannot be empty.");
                    } else {
                        manager.searchByCategory(searchCategory);
                    }

                    break;

                case 5:

                    manager.calculateTotal();
                    break;

                case 6:

                    FileHandler.saveExpenses(manager.getExpenses());

                    System.out.println("Thank you for using Daily Expense Analyzer!");

                    running = false;
                    break;

                default:

                    System.out.println("Invalid choice. Please select 1 to 6.");
            }
        }

        sc.close();
    }
}

