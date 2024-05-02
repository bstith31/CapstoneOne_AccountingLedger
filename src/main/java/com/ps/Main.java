package com.ps;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    //Globally declaring scanner
    public static Scanner scanner = new Scanner(System.in);
    public static ArrayList<String> transactions = new ArrayList<String>();


    public static void main(String[] args) {

        loadTransactions();
        //Formatting within console to make it more visually appealing and interesting
        System.out.print
                ("""
                        =======================================================================================================
                        ==================================Brian's Accounting Ledger============================================
                        ======================Why manage your finances when I can do it for you================================
                                         
                        Welcome!!! Thank you for taking the time to use Brian's Accounting Ledger as your first choice!
                        Please enter some of the following information to begin tracking your ongoing financial transactions!
                        Firstly, Please enter a name to refer to you as:\s""");

        String name;
        name = scanner.nextLine();

        System.out.println
                ("_______________________________________________________________________________________________________");
        System.out.print
                ("Hello!! " + name + ", What is your purpose for using us today, please state your intended purpose for today. \n" +
                        "Is it for (B) Business or is it for (P) Personal, please press the corresponding letter to begin:\s");

        String serviceChoice;
        boolean validInput;
        do {
            //Converts input to uppercase to handle any misinputs of B or P
            serviceChoice = scanner.next().toUpperCase();
            System.out.println
                    ("_______________________________________________________________________________________________________");
            scanner.nextLine();

            switch (serviceChoice) {
                case "B":
                    System.out.println
                            (name + "," + """
                                     Thank you for choosing our service for business! Hopefully we are able to accommodate all of your needs
                                    Please select from the following options and let us know how we can help with your business needs today:
                                    _________________________________________________________________________________________________________
                                                                    
                                    1. Add Deposit
                                    2. Make a Payment
                                    3. Access your Ledger
                                    4. Exit
                                                                    
                                    _____________________________________________________________________________________________________________
                                    """);
                    System.out.print("Please enter your choice: ");
                    int businessChoice = scanner.nextInt();
                    scanner.nextLine();
                    switch (businessChoice) {
                        case 1:
                            System.out.println("1");
                            makeDeposit();
                            break;
                        case 2:
                            System.out.println("2");
                            makePayment();
                            break;
                        case 3:
                            System.out.println("3");
                            ledgerMenu();
                            break;
                        case 4:
                            System.out.println("4");
                            //exit
                            break;
                        default:
                            System.out.println("Invalid input");
                            validInput = false;
                            break;
                    }
                    validInput = true;
                    break;
                case "P":
                    System.out.println
                            (name + "," + """
                                     Thank you for choosing our service for personal! Hopefully we are able to accommodate all of your needs
                                    Please select from the following options and let us know how we can help you with your personal needs today:
                                    ____________________________________________________________________________________________________________
                                                                    
                                    1. Add Deposit
                                    2. Make a Payment
                                    3. Access your Ledger
                                    4. Exit
                                                                    
                                    _____________________________________________________________________________________________________________
                                    """);
                    System.out.print("Please enter your choice: ");
                    int personalChoice = scanner.nextInt();
                    scanner.nextLine();
                    switch (personalChoice) {
                        case 1:
                            System.out.println("1");
                            makeDeposit();
                            break;
                        case 2:
                            System.out.println("2");
                            makePayment();
                            break;
                        case 3:
                            System.out.println("3");
                            ledgerMenu();
                            break;
                        case 4:
                            System.out.println("4");
                            //exit
                            break;
                        default:
                            System.out.println("Invalid input");
                            validInput = false;
                            break;
                    }
                    validInput = true;
                    break;
                default:
                    System.out.print("That is not a valid option, please select a valid option (B) for Business or (P) for Personal: ");
                    validInput = false;
                    break;
            }
        } while (!validInput);
    }

    public static void makeDeposit() {

        try (BufferedWriter bufwriter = new BufferedWriter(new FileWriter("transactions.txt", true))) {
            boolean addAnotherTransaction = true;
            do {
                System.out.println("============================Welcome to the Deposits Menu============================");
                System.out.println("Please enter the following information to accurately log your deposit");

                String dateTime;
                boolean validInput;
                do {
                    validInput = true; // Assumption of a valid input
                    System.out.print("Will you be using the current date to log your transaction (Y/N): ");
                    String depositChoice = scanner.nextLine().toUpperCase();

                    if (depositChoice.equals("Y")) {
                        // Allows the user to use the exact date and time of the input
                        LocalDateTime now = LocalDateTime.now();
                        dateTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    } else if (depositChoice.equals("N")) {
                        // Allows the user to use their date and time currently does not have error handling
                        System.out.print("Please enter the date and time (yyyy-MM-dd HH:mm:ss): ");
                        dateTime = scanner.nextLine();
                    } else {
                        // Handles a missed input and continues the loop when it occurs
                        System.out.println("Invalid input. Please enter 'Y' or 'N'.");
                        validInput = false;
                        continue;
                    }

                    String[] dateTimeParts = dateTime.split(" "); // Split dateTime into date and time parts
                    String date = dateTimeParts[0]; // Extract date
                    String time = dateTimeParts[1]; // Extract time

                    System.out.print("What is a description of the item: ");
                    String description = scanner.nextLine();

                    System.out.print("Who is the vendor of the item: ");
                    String vendor = scanner.nextLine();

                    System.out.print("What was the deposit amount: $");
                    if (!scanner.hasNextDouble()) {
                        System.out.println("Invalid input. Please enter a valid number.");
                        scanner.nextLine();
                        validInput = false;
                        continue;
                    }
                    double price = scanner.nextDouble();
                    scanner.nextLine();

                    // Format the deposit information
                    String formattedDeposit = String.format("%s|%s|%s|%s|%.2f%n", date, time, description, vendor,  price);

                    // Add formatted deposit to the ArrayList
                    transactions.add(formattedDeposit);

                    // Write the formatted deposit information to the file
                    bufwriter.write(formattedDeposit + "\n");
                    bufwriter.flush(); // Flush to ensure data is written immediately

                    System.out.println("Your deposit has been successfully logged.");
                    System.out.println("_____________________________________________________________________________________");
                } while (!validInput); // Repeat the loop if input is invalid

                System.out.print("Do you want to add another deposit? (Y/N): ");
                String addAnother = scanner.nextLine().toUpperCase();
                addAnotherTransaction = addAnother.equals("Y");
            } while (addAnotherTransaction); // Repeats the loop to allow for further inputs

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void makePayment() {

        try (BufferedWriter bufwriter = new BufferedWriter(new FileWriter("transactions.txt", true))) {
            boolean addAnotherTransaction = true;
            do {
                System.out.println("============================Welcome to the Payments Menu============================");
                System.out.println("Please enter the following information to accurately log your payment");

                String dateTime;
                boolean validInput;
                do {
                    validInput = true; // Assumption of a valid input
                    System.out.print("Would you like to log your item with the current date and time (Y/N): ");
                    String paymentChoice = scanner.nextLine().toUpperCase();

                    if (paymentChoice.equals("Y")) {
                        // Allows the user to use the exact date and time of the input
                        LocalDateTime now = LocalDateTime.now();
                        dateTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    } else if (paymentChoice.equals("N")) {
                        // Allows the user to use their date and time currently does not have error handling
                        System.out.print("Please enter the date and time (yyyy-MM-dd HH:mm:ss): ");
                        dateTime = scanner.nextLine();
                    } else {
                        // Handles a missed input and continues the loop when it occurs
                        System.out.println("Invalid input. Please enter 'Y' or 'N'.");
                        validInput = false;
                        continue;
                    }

                    String[] dateTimeParts = dateTime.split(" "); // Split dateTime into date and time parts
                    String date = dateTimeParts[0]; // Extract date
                    String time = dateTimeParts[1]; // Extract time

                    System.out.print("What is a description of the item: ");
                    String description = scanner.nextLine();

                    System.out.print("Who is the vendor of the item: ");
                    String vendor = scanner.nextLine();

                    System.out.print("How much did the item cost: $");
                    if (!scanner.hasNextDouble()) {
                        System.out.println("Invalid input. Please enter a valid number.");
                        scanner.nextLine();
                        validInput = false;
                        continue;
                    }
                    double price = scanner.nextDouble();
                    scanner.nextLine();

                    // Format the payment information
                    String formattedPayment = String.format("%s|%s|%s|%s|%.2f%n", date, time, description, vendor, (-price));

                    transactions.add(formattedPayment);

                    // Write the formatted payment information to the file
                    bufwriter.write(formattedPayment);
                    bufwriter.flush();

                    System.out.println("Your payment has been successfully logged.");
                    System.out.println("_________________________________________________________________________________________");
                } while (!validInput); // Repeat the loop if input is invalid

                System.out.print("Do you want to add another payment? (Y/N): ");
                String addAnother = scanner.nextLine().toUpperCase();
                addAnotherTransaction = addAnother.equals("Y");
            } while (addAnotherTransaction); // Repeat the loop if the user wants to add another payment

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean returnToMainMenu() {
        System.out.print("Do you want to return to the main menu? (Y/N): ");
        String returnToMenu = scanner.nextLine().toUpperCase();
        return returnToMenu.equals("Y");
    }

    private static void ledgerMenu() {

        int ledgerChoice;

        do {

            System.out.println("============================Welcome to the Ledger Menu============================");
            System.out.println("Use the options below to access any reports you may want to see       ");
            System.out.print("""
                                        
                    1. Print out all.
                    2. Print out deposits.
                    3. Print out payments
                    4. Access Reports
                    5. Home
                                        
                    ________________________________________________________________________________________________
                    """);
            System.out.print("What would you like to do? Enter a choice: ");

            ledgerChoice = scanner.nextInt();

            switch (ledgerChoice) {
                case 1:
                    System.out.println("\nWill display all transactions\n");
                    displayAllTransactions();
                    break;
                case 2:
                    System.out.println("\nWill display all deposits\n");
                    displayAllDeposits();
                    break;
                case 3:
                    System.out.println("\nWill display all payments\n");
                    displayAllPayments();
                    break;
                case 4:
                    System.out.println("\nWill access the reports menu\n");
                    reportsMenu();
                    break;
                case 5:
                    System.out.println("returning");
                   // returnToMainMenu();
                    break;
                default:
                    System.out.println("\nInvalid choice. Please select again\n");
                    break;
            }
        } while (ledgerChoice != 5);
    }

    private static void reportsMenu() {

        int reportsChoice;

        do {

            System.out.println("============================Welcome to your Reports Menu============================");
            System.out.println("Use the options below to access any reports you may want to see       ");
            System.out.print("""
                                        
                    1. Month to Date:
                    2. Previous Month:
                    3. Year to Date:
                    4. Previous Year:
                    5. Custom Search:
                    6. Return to Ledger Menu
                                        
                    ________________________________________________________________________________________________
                    """);
            System.out.print("What would you like to do? Enter a choice: ");

            reportsChoice = scanner.nextInt();

            switch (reportsChoice) {

                case 1:
                    System.out.println("\nMonth to Date:\n");
                    break;
                case 2:
                    System.out.println("\nPrevious Month:\n");
                    break;
                case 3:
                    System.out.println("\nYear to Date:\n");
                    break;
                case 4:
                    System.out.println("\nPrevious Year:\n");
                    break;
                case 5:
                    System.out.println("\nCustom search:\n");
                    break;
                case 6:
                    ledgerMenu();
                    break;
                default:
                    System.out.println("\nInvalid choice. Please select again\n");
                    break;
            }

        } while (reportsChoice != 6);


    }

    private static void displayAllTransactions() {
        try (BufferedReader bufreader = new BufferedReader(new FileReader("transactions.txt"))) {
            System.out.println("\n=====================Start of all the current transactions===========================");

            String line;
            bufreader.readLine();
            while ((line = bufreader.readLine()) != null) {
                System.out.println(line);
            }

            System.out.println("\n=======================End of all the current transactions===========================");
        } catch (IOException e) {
            System.out.println("Error reading transactions file: " + e.getMessage());
        }

    }

    private static void displayAllDeposits() {
        try (BufferedReader bufreader = new BufferedReader(new FileReader("transactions.txt"))) {
            System.out.println("\n=====================Start of all the deposits===========================");

            String line;
            bufreader.readLine();

            while ((line = bufreader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 5) {
                    try {
                        double amount = Double.parseDouble(parts[4]);
                        if ((amount > 0)) {
                            System.out.println(line);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error parsing amount in line: " + line);
                    }
                }
            }

            System.out.println("\n=======================End of all the deposits===========================");
        } catch (IOException e) {
            System.out.println("Error reading transactions file: " + e.getMessage());
        }
    }

    private static void displayAllPayments() {
        try (BufferedReader bufreader = new BufferedReader(new FileReader("transactions.txt"))) {
            System.out.println("\n=====================Start of all the payments===========================");

            String line;
            bufreader.readLine();

            while ((line = bufreader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 5) {
                    try {
                        double amount = Double.parseDouble(parts[4]);
                        if ((amount < 0)) {
                            System.out.println(line);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error parsing amount in line: " + line);
                    }
                }
            }

            System.out.println("\n=======================End of all the payments===========================");
        } catch (IOException e) {
            System.out.println("Error reading transactions file: " + e.getMessage());
        }

    }
//
//    private static void monthToDate () {
//
//        // Get current date
//        LocalDate currentDate = LocalDate.now();
//        // Get the first day of the current month
//        LocalDate firstDayOfMonth = currentDate.with(TemporalAdjusters.firstDayOfMonth());
//        // Call method to display transactions for the specified period
//        displayTransactionsForPeriod(firstDayOfMonth, currentDate);
//    }

    private static void loadTransactions() {
        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                transactions.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error loading inventory: " + e.getMessage());
        }
    }


}