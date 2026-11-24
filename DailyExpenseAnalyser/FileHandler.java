
import java.util.ArrayList;
import java.io.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;

public class FileHandler {

    private static final String FILE_NAME = "expenses.txt";

    // Load Expenses from File
    public static ArrayList<Expense> loadExpenses() {

        ArrayList<Expense> expenses = new ArrayList<>();

        Path path = Paths.get(FILE_NAME);

        if (!Files.exists(path)) {
            return expenses;
        }

        try (BufferedReader br = Files.newBufferedReader(
                path, StandardCharsets.UTF_8)) {

            String line;

            while ((line = br.readLine()) != null) {

                String[] data = line.split("\\|", -1);

                if (data.length != 3) {
                    continue;
                }

                try {
                    String id = data[0].trim();
                    String category = data[1].trim();
                    double amount = Double.parseDouble(data[2].trim());

                    if (id.isEmpty() || category.isEmpty()
                            || !Double.isFinite(amount) || amount <= 0) {
                        continue;
                    }

                    expenses.add(new Expense(id, category, amount));

                } catch (NumberFormatException e) {
                    System.out.println("Skipping invalid record: " + line);
                }
            }

        } catch (IOException e) {
            System.out.println("Error loading expenses: " + e.getMessage());
        }

        return expenses;
    }

    // Save Expenses to File
    public static void saveExpenses(ArrayList<Expense> expenses) {

        try (BufferedWriter bw = Files.newBufferedWriter(
                Paths.get(FILE_NAME), StandardCharsets.UTF_8)) {

            for (Expense e : expenses) {

                bw.write(
                    e.getExpenseId() + "|" +
                    e.getCategory() + "|" +
                    e.getAmount()
                );

                bw.newLine();
            }

            System.out.println("Expenses saved successfully!");

        } catch (IOException e) {
            System.out.println("Error saving expenses: " + e.getMessage());
        }
    }
}

