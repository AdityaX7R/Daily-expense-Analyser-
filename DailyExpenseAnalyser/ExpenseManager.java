
import java.util.ArrayList;

public class ExpenseManager {

    private ArrayList<Expense> expenses;
    private int nextId = 101;

    public ExpenseManager(ArrayList<Expense> expenses) {
        this.expenses = expenses;

        // Generate the next available ID
        for (Expense e : expenses) {
            try {
                int id = Integer.parseInt(
                    e.getExpenseId().substring(1)
                );

                if (id >= nextId) {
                    nextId = id + 1;
                }
            } catch (NumberFormatException | StringIndexOutOfBoundsException ex) {
                // Ignore malformed IDs
            }
        }
    }

    // Add Expense
    public void addExpense(String category, double amount) {

        String id = "X" + nextId++;

        Expense expense = new Expense(id, category, amount);

        expenses.add(expense);

        System.out.println("Expense added successfully!");
        System.out.println("Generated Expense ID: " + id);
    }

    // Display All Expenses
    public void displayAll() {

        if (expenses.isEmpty()) {
            System.out.println("No expenses available.");
            return;
        }

        System.out.printf("%-10s %-20s %s%n",
                "ID", "Category", "Amount");

        System.out.println("------------------------------------------");

        for (Expense e : expenses) {
            e.displayExpense();
        }
    }

    // Bubble Sort: High to Low
    public void sortByAmount() {

        int n = expenses.size();

        for (int i = 0; i < n - 1; i++) {

            for (int j = 0; j < n - i - 1; j++) {

                if (expenses.get(j).getAmount()
                        < expenses.get(j + 1).getAmount()) {

                    Expense temp = expenses.get(j);

                    expenses.set(j, expenses.get(j + 1));

                    expenses.set(j + 1, temp);
                }
            }
        }

        System.out.println("Expenses sorted from highest to lowest.");
        displayAll();
    }

    // Linear Search by Category
    public void searchByCategory(String category) {

        boolean found = false;

        for (Expense e : expenses) {

            if (e.getCategory().equalsIgnoreCase(category)) {

                if (!found) {
                    System.out.println("Matching Expenses:");
                    System.out.printf("%-10s %-20s %s%n",
                            "ID", "Category", "Amount");
                }

                e.displayExpense();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No expenses found in this category.");
        }
    }

    // Calculate Total Expense
    public void calculateTotal() {

        if (expenses.isEmpty()) {
            System.out.println("No expenses available.");
            return;
        }

        double total = 0;

        Expense highest = expenses.get(0);

        for (Expense e : expenses) {

            total += e.getAmount();

            if (e.getAmount() > highest.getAmount()) {
                highest = e;
            }
        }

        System.out.printf("Total Expense: %.2f%n", total);

        System.out.println("Highest Expense:");

        highest.displayExpense();
    }

    public ArrayList<Expense> getExpenses() {
        return expenses;
    }
}

