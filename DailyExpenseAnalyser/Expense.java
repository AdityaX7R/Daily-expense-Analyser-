
public class Expense {

    private String expenseId;
    private String category;
    private double amount;

    public Expense(String expenseId, String category, double amount) {
        this.expenseId = expenseId;
        this.category = category;
        this.amount = amount;
    }

    public String getExpenseId() {
        return expenseId;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    public void displayExpense() {
        System.out.printf("%-10s %-20s %.2f%n",
                expenseId, category, amount);
    }
}

