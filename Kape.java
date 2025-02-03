import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Kape {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int menu = 0;
        double totalCost = 0.0;
        int[] quantities = new int[4];


        String[][] coffeeMenu = {
                {"Espresso", "50.0"},
                {"Latte", "70.0"},
                {"Cappuccino", "65.0"},
                {"Mocha", "80.0"}
        };

        while (true) {
            System.out.print("------KAPE MENU-----\n");
            for (int i = 0; i < coffeeMenu.length; i++) {
                System.out.println((i + 1) + ". " + coffeeMenu[i][0] + " - " + coffeeMenu[i][1] + "php");
            }
            System.out.println("0. Finish order");
            System.out.print("Choose your coffee (1-4, or 0 to finish): ");

            try {
                menu = input.nextInt();
                if (menu < 0 || menu > 4) {
                    System.out.println("Invalid Number! Try Again\n");
                    continue;
                }
                if (menu == 0) {
                    System.out.println("Order is Finished\n");
                    break;
                }

                System.out.print("Enter quantity: ");
                int quantity = input.nextInt();

                quantities[menu - 1] += quantity;
                totalCost += Double.parseDouble(coffeeMenu[menu - 1][1]) * quantity;
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a valid number.");
                input.nextLine();
            }
            System.out.println();
        }

        printReceipt(totalCost, quantities, coffeeMenu);
        writeReceiptToFile(totalCost, quantities, coffeeMenu);
        readReceiptFromFile();
    }

    public static void printReceipt(double totalCost, int[] quantities, String[][] coffeeMenu) {
        double vat = totalCost * 0.12;
        double grandTotalCost = totalCost + vat;

        System.out.println("------Coffee Order Receipt------");
        for (int i = 0; i < coffeeMenu.length; i++) {
            if (quantities[i] > 0) {
                double itemTotal = quantities[i] * Double.parseDouble(coffeeMenu[i][1]);
                System.out.println(quantities[i] + "x " + coffeeMenu[i][0] + " - " +String.format("%.2f", itemTotal) + "php");
            }
        }
        System.out.println("--------------------------");
        System.out.println("Subtotal: " + String.format("%.2f", totalCost) + "php");
        System.out.println("VAT (12%): " + String.format("%.2f", vat) + "php");
        System.out.println("Grand Total: " + String.format("%.2f", grandTotalCost) + "php");
        System.out.println("--------------------------");
    }

    public static void writeReceiptToFile(double totalCost, int[] quantities, String[][] coffeeMenu) {
        double vat = totalCost * 0.12;
        double grandTotalCost = totalCost + vat;
        String receipt = "------Coffee Order Receipt------\n";

        for (int i = 0; i < coffeeMenu.length; i++) {
            if (quantities[i] > 0) {
                double itemTotal = quantities[i] * Double.parseDouble(coffeeMenu[i][1]);
                receipt += String.format("%dx %s - %.2fphp%n", quantities[i], coffeeMenu[i][0], itemTotal);
            }
        }
        receipt += "--------------------------\n" +
                "Subtotal: " + String.format("%.2f", totalCost) + "php\n" +
                "VAT (12%): " + String.format("%.2f", vat) + "php\n" +
                "Grand Total: " + String.format("%.2f", grandTotalCost) + "php\n" +
                "--------------------------\n";

        try (FileWriter writer = new FileWriter("CoffeeReceipt.txt")) {
            writer.write(receipt);
            System.out.println("Receipt has been written to CoffeeReceipt.txt");
        } catch (IOException e) {
            System.out.println("An error occurred while writing the receipt to the file.");
            e.printStackTrace();
        }
    }
    public static void readReceiptFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("CoffeeReceipt.txt"))) {
            String line;
            System.out.println("\nReceipt has been read from CoffeeReceipt.txt:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
    }
}