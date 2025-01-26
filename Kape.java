import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Kape {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int menu = 0;
        double totalCost = 0.0;
        String orders = "";

        while (true) {
            System.out.print("""
                    ------KAPE MENU-----
                    1. Espresso - 50.0php
                    2. Latte - 70.0php
                    3. Cappuccino - 65.0php
                    4. Mocha - 80.0php
                    0. Finish order 
                    Choose your coffee (1-4, or 0 to finish):\s  """);

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

                double price = 0.0;
                String kapeName = "";

                switch (menu) {
                    case 1:
                        kapeName = "Espresso";
                        price = 50.0;
                        break;
                    case 2:
                        kapeName = "Latte";
                        price = 70.0;
                        break;
                    case 3:
                        kapeName = "Cappuccino";
                        price = 65.0;
                        break;
                    case 4:
                        kapeName = "Mocha";
                        price = 80.0;
                        break;
                    default:
                        System.out.println("Invalid Number! Try Again");
                        continue;

                }
                totalCost += price * quantity;
                orders += quantity + " x " + kapeName + " - " + String.format("%.2f", price * quantity) + "php\n";
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a valid number.");
                input.nextLine();
            }
            System.out.println();
        }
        printReceipt(totalCost, orders);
        readReceiptFromFile();
    }
    public static void printReceipt(double totalCost, String orders){
        double vat = totalCost * 0.12;
        double grandTotalCost = totalCost + vat;

        System.out.println("------Coffee Order Receipt------");
        System.out.print(orders);
        System.out.println("--------------------------");
        System.out.println("Subtotal: " + String.format("%.2f", totalCost) + "php");
        System.out.println("VAT (12%): " + String.format("%.2f", vat) + "php");
        System.out.println("Grand Total: " + String.format("%.2f", grandTotalCost) + "php");
        System.out.println("--------------------------");
    }

    public static void readReceiptFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("CoffeeReceipt.txt"))) {
            String line;
            System.out.println("\nReceipt has been saved from CoffeeReceipt.txt:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}