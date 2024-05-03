package com.ps;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    /*
    Publicly declaring Scanner and Array List to eliminate the need to pass arguments to them. Simplifies the process
    when dealing with the multiple inputs that I am going to need to take. Looking back at this now considering the
    extensive use of BufferedReader and BufferedWriter I went with, both could have been declared as well.
     */
    public static Scanner scanner = new Scanner(System.in);
    public static ArrayList<String> transactions = new ArrayList<String>();

    public static void main(String[] args) {

        //Loads transaction from the very start so that any that are already pre-inputted into the file we'll be reading from.
        loadTransactions();

        /*
        A welcome screen and message that allows for a user to input their names. It allows for a more welcoming screen, and
        for the experience to be more personable. This is something I want to improve upon in the future and to work more with
        the idea of splitting transaction between created and written to.
         */
        System.out.print
                ("""
                        ==============================================================================================================
                        ========================================Brian's Accounting Ledger=============================================
                        ==============================Why track your finances when I can do it for you================================
                                         
                        Welcome!!! Thank you for taking the time to use Brian's Accounting Ledger as your first choice!
                        Please enter some of the following information to begin tracking your ongoing financial transactions!
                        Firstly, Please enter a name to refer to you as:\s""");

        String name;
        name = scanner.nextLine();

        System.out.println
                ("______________________________________________________________________________________________________________\n");

        System.out.print
                ("Hello!! " + name + ", What is your purpose for using us today, please state your intended purpose for today. \n" +
                        "Is it for (B) Business or is it for (P) Personal, please press the corresponding letter to begin:\s");

        String serviceChoice = scanner.next().toUpperCase();
        System.out.println
                ("\n______________________________________________________________________________________________________________\n");

        scanner.nextLine();
//        boolean validInput;
//        do {
//
//            serviceChoice = scanner.next().toUpperCase();
//            scanner.nextLine();

        switch (serviceChoice) {
            //Business-side Ledger
            case "B":
                do {
                    System.out.println
                            (name + "," + """
                                     Thank you for choosing our service for business! Hopefully we are able to accommodate all of your needs
                                    Please select from the following options and let us know how we can help with your business needs today:
                                    ______________________________________________________________________________________________________________
                                                                    
                                    1. Add Deposit
                                    2. Make a Payment
                                    3. Access your Ledger
                                    4. Exit
                                                                    
                                    ______________________________________________________________________________________________________________
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
                            System.out.println("Invalid choice. Please select again");
//                               validInput = false;
                            break;
                    }
                } while (true);
//                    validInput = true;
//                    break;
            //Personal-side Ledger
            case "P":
                do {
                    System.out.println
                            (name + "," + """
                                     Thank you for choosing our service for personal! Hopefully we are able to accommodate all of your needs
                                    Please select from the following options and let us know how we can help you with your personal needs today:
                                    ______________________________________________________________________________________________________________
                                                                    
                                    1. Add Deposit
                                    2. Make a Payment
                                    3. Access your Ledger
                                    4. Exit
                                                                    
                                    ______________________________________________________________________________________________________________
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
                            System.out.println("Exiting the program...");
                            return;
                        default:
                            System.out.println("\nInvalid choice. Please select again\n");
//                            validInput = false;
                            break;
                    }
                } while (true);

//                    validInput = true;
//                    break;
            default:
                System.out.print("That is not a valid option, please select a valid option (B) for Business or (P) for Personal: ");
//                    validInput = false;
                break;
        }
//        } while (!validInput);
    }

    /*
    Adding Deposits and Making Payments: When an input is placed it these two methods which are redundant for simplicity’s sake process
    information in the same way and write to the file. A future optimization would be to allow for the user to designate which they want
    to use and place it all in one method or better a separate class to call from.
     */
    public static void makeDeposit() {

        try (BufferedWriter bufwriter = new BufferedWriter(new FileWriter("transactions.txt", true))) {
            boolean addAnotherTransaction = true;
            do {
                System.out.println("\n============================================Welcome to Deposits===============================================\n");
                System.out.println("Please enter the following information to accurately log your deposit");

                String dateTime;
                boolean validInput;
                do {
                    validInput = true;
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
                    String formattedDeposit = String.format("%s|%s|%s|%s|%.2f%n", date, time, description, vendor, price);

                    // Add formatted deposit to the ArrayList
                    transactions.add(formattedDeposit);

                    // Write the formatted deposit information to the file
                    bufwriter.write(formattedDeposit + "\n");
                    bufwriter.flush(); // Flush to ensure data is written immediately

                    System.out.println("Your deposit has been successfully logged.");
                    System.out.println("\n_____________________________________________________________________________________________________________\n");

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
                System.out.println("\n============================================Welcome to Payments================================================\n");
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
                    System.out.println("\n______________________________________________________________________________________________________________\n");
                } while (!validInput); // Repeat the loop if input is invalid

                System.out.print("Do you want to add another payment? (Y/N): ");
                String addAnother = scanner.nextLine().toUpperCase();
                addAnotherTransaction = addAnother.equals("Y");
            } while (addAnotherTransaction); // Repeat the loop if the user wants to add another payment

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //A method for confirmation that the user wanted to return to the Home Screen since they had to traverse to screen.
    //This was also used as a way to check an error I was experiencing of an input being eaten when accessing reports
    //and then trying to get back to the Home Screen. This helped me realize that was happening.
    private static boolean returnToMainMenu() {
        System.out.print("Do you want to return to the main menu? (Y/N): ");
        String returnToMenu = scanner.nextLine().toUpperCase();
        return returnToMenu.equals("Y");

    }
    /* Ledger and Report Menu: Menus designed to manage all the functionality and display of both. Simplified the process of
    tracking what I was doing but also would work better to separate into a separate class that would hold all of this. As
    it is right now it is messy but something that will be worked on more.
     */
    private static void ledgerMenu() {

        int ledgerChoice;
        boolean returnToMain = false;

        do {

            System.out.println("\n=========================================Welcome to your Ledger===============================================\n");
            System.out.println("Use the options below to access any reports you may want to see       ");
            System.out.print("""
                                        
                    1. Display All
                    2. Display All Deposits
                    3. Display All Payments
                    4. Access Reports
                    5. Return to Main Menu
                                        
                    ______________________________________________________________________________________________________________\n
                    """);
            System.out.print("Are there any specific things you want to view? Enter a choice: ");

            ledgerChoice = scanner.nextInt();

            switch (ledgerChoice) {
                case 1:
                    System.out.println("\nDisplaying All Transactions:\n");
                    displayAllTransactions();
                    break;
                case 2:
                    System.out.println("\nDisplaying All Deposits\n");
                    displayAllDeposits();
                    break;
                case 3:
                    System.out.println("\nDisplaying All Payments\n");
                    displayAllPayments();
                    break;
                case 4:
                    System.out.println("\nAccessing the Reports Menu\n");
                    reportsMenu();
                    break;
                case 5:
                    System.out.println("Confirm you wish to return to the main menu");
                    scanner.nextLine();
                    returnToMainMenu();
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

            System.out.println("\n=========================================Welcome to your Reports=============================================\n");
            System.out.println("Use the options below to access any reports you may want to see       ");
            System.out.print("""
                                        
                    1. Month to Date:
                    2. Previous Month:
                    3. Year to Date:
                    4. Previous Year:
                    5. Custom Search:
                    6. Return to Ledger Menu
                                
                    ______________________________________________________________________________________________________________
                    """);
            System.out.print("Which report would you like to view? Enter a choice from the following: ");

            reportsChoice = scanner.nextInt();

            switch (reportsChoice) {

                case 1:
                    System.out.println("\nMonth to Date:\n");
                    displayTransactionsForMonthToDate();
                    break;
                case 2:
                    System.out.println("\nPrevious Month:\n");
                    displayTransactionsForPreviousMonth();
                    break;
                case 3:
                    System.out.println("\nYear to Date:\n");
                    displayTransactionsForYearToDate();
                    break;
                case 4:
                    System.out.println("\nPrevious Year:\n");
                    displayTransactionsForPreviousYear();
                    break;
                case 5:
                    System.out.println("\nCustom search:\n");
                    customSearch();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("\nInvalid choice. Please select again\n");
                    break;
            }

        } while (reportsChoice != 6);
    }

    /*
    Display Methods: Reading from transactions.txt and splitting the results, given the scope of this project and the fact that a file was always going
    to be created or present, I wanted to focus on using that data to build my  application around.
    */
    private static void displayAllTransactions() {
        try (BufferedReader bufreader = new BufferedReader(new FileReader("transactions.txt"))) {
            System.out.println("\n===================================Start of all the logged transactions=======================================\n");

            String line;
            bufreader.readLine();
            while ((line = bufreader.readLine()) != null) {
                System.out.println(line);
            }

            System.out.println("\n_____________________________________End of all the logged transactions_______________________________________");
        } catch (IOException e) {
            System.out.println("Error reading transactions file: " + e.getMessage());
        }

    }

    private static void displayAllDeposits() {
        try (BufferedReader bufreader = new BufferedReader(new FileReader("transactions.txt"))) {
            System.out.println("\n=======================================Start of all your deposits============================================\n");

            String line;
            bufreader.readLine();

            while ((line = bufreader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 5) {
                    try {
                        double price = Double.parseDouble(parts[4]);
                        if ((price > 0)) {
                            System.out.println(line);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error parsing amount in line: " + line);
                    }
                }
            }
            System.out.println("\n_______________________________________End of all your deposits_____________________________________________");
        } catch (IOException e) {
            System.out.println("Error reading transactions file: " + e.getMessage());
        }
    }

    private static void displayAllPayments() {
        try (BufferedReader bufreader = new BufferedReader(new FileReader("transactions.txt"))) {
            System.out.println("\n=======================================Start of all your payments============================================\n");

            String line;
            bufreader.readLine();

            while ((line = bufreader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 5) {
                    try {
                        double price = Double.parseDouble(parts[4]);
                        if ((price < 0)) {
                            System.out.println(line);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error parsing amount in line: " + line);
                    }
                }
            }
            System.out.println("\n_______________________________________End of all your payments______________________________________________");
        } catch (IOException e) {
            System.out.println("Error reading transactions file: " + e.getMessage());
        }
    }

    private static void loadTransactions() {
        try (BufferedReader bufreader = new BufferedReader(new FileReader("transactions.txt"))) {
            String line;
            bufreader.readLine();
            while ((line = bufreader.readLine()) != null) {
                transactions.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error loading inventory: " + e.getMessage());
        }
    }

    /*
    Displaying transactions:
    The following four static methods are utilized to display all the transactions. Utilizing BufferedReader I read
    from the file to acquire the dates of the transactions. displayTransactionsMethod is the primary method and the
    subsequent methods are devised using LocalDate and TemporalAdjusters which was researched on Oracle.
     */
    private static void displayTransactionsMethod(LocalDate startDate, LocalDate endDate) {
        try (BufferedReader bufreader = new BufferedReader(new FileReader("transactions.txt"))) {
            System.out.println("\n==============================================Transactions====================================================\n");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String line;
            bufreader.readLine();
            while ((line = bufreader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 1) {
                    try {
                        LocalDate transactionDate = LocalDate.parse(parts[0], formatter);
                        if (!transactionDate.isBefore(startDate) && !transactionDate.isAfter(endDate)) {
                            System.out.println(line);
                        }
                    } catch (Exception e) {
                        System.out.println("Error parsing date in line: " + line);
                    }
                }
            }
            System.out.println("\n__________________________________________End of Transactions________________________________________________");
        } catch (IOException e) {
            System.out.println("Error reading transactions file: " + e.getMessage());
        }
    }

    private static void displayTransactionsForMonthToDate() {
        LocalDate currentDate = LocalDate.now();
        LocalDate firstDayOfMonth = currentDate.with(TemporalAdjusters.firstDayOfMonth());
        displayTransactionsMethod(firstDayOfMonth, currentDate);
    }

    private static void displayTransactionsForPreviousMonth() {
        LocalDate currentDate = LocalDate.now();
        LocalDate firstDayOfPreviousMonth = currentDate.minusMonths(1).with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastDayOfPreviousMonth = firstDayOfPreviousMonth.with(TemporalAdjusters.lastDayOfMonth());
        displayTransactionsMethod(firstDayOfPreviousMonth, lastDayOfPreviousMonth);
    }

    private static void displayTransactionsForYearToDate() {
        LocalDate currentDate = LocalDate.now();
        LocalDate firstDayOfYear = LocalDate.of(currentDate.getYear(), 1, 1);
        displayTransactionsMethod(firstDayOfYear, currentDate);
    }

    private static void displayTransactionsForPreviousYear() {
        LocalDate currentDate = LocalDate.now();
        LocalDate firstDayOfPreviousYear = LocalDate.of(currentDate.minusYears(1).getYear(), 1, 1);
        LocalDate lastDayOfPreviousYear = LocalDate.of(currentDate.minusYears(1).getYear(), 12, 31);
        displayTransactionsMethod(firstDayOfPreviousYear, lastDayOfPreviousYear);
    }


    /*
    Custom Search:
    Implements BufferedReader to search through transactions.txt to pull matching criteria for implemented data.
    My version requires a minimum and maximum value, left this in since it may be necessary to have a range of prices,
    over a given time period. All other fields are not required. Hard coded to include variations due to issues I had where
    I could search for anything properly except for Description and Vendor.
     */
    private static void customSearch() {

        try (BufferedReader bufreader = new BufferedReader(new FileReader("transactions.txt"))) {
            System.out.println("\n================================================Custom Search=================================================\n");
            System.out.println("Please enter the search criteria:");

            System.out.print("Minimum Price (leave blank for any): ");
            String minPriceInput = scanner.nextLine().trim();
            double minPrice;
            if (minPriceInput.isEmpty()) {
                minPrice = 0;
            } else {
                minPrice = scanner.nextDouble();
            }
            scanner.nextLine();

            System.out.print("Maximum Price (leave blank for any): ");
            String maxPriceInput = scanner.nextLine().trim();
            double maxPrice;
            if (maxPriceInput.isEmpty()) {
                maxPrice = Double.POSITIVE_INFINITY;
            } else {
                maxPrice = Double.parseDouble(maxPriceInput);
            }


            LocalDate startDate = null;
            System.out.print("Start Date (yyyy-MM-dd) (leave blank for any): ");
            String startDateInput = scanner.nextLine().trim();
            if (!startDateInput.isEmpty()) {
                startDate = LocalDate.parse(startDateInput);
            }

            LocalDate endDate = null;
            System.out.print("End Date (yyyy-MM-dd) (leave blank for any): ");
            String endDateInput = scanner.nextLine().trim();
            if (!endDateInput.isEmpty()) {
                endDate = LocalDate.parse(endDateInput);
            }

            String description = "";
            System.out.print("Description (leave blank for any): ");
            String descriptionInput = scanner.nextLine().trim();
            if (!descriptionInput.isEmpty()) {
                description = descriptionInput.toLowerCase();
            }

            String vendor = "";
            System.out.print("Vendor (leave blank for any): ");
            String vendorInput = scanner.nextLine().trim();
            if (!vendorInput.isEmpty()) {
                vendor = vendorInput.toLowerCase();
            }

            System.out.println("\n============================================Filtered Transactions============================================\n");

            String line;
            bufreader.readLine();
            while ((line = bufreader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 5) {
                    LocalDate transactionDate = LocalDate.parse(parts[0]);
                    String transactionDescription = parts[2].toLowerCase();
                    String transactionVendor = parts[3].toLowerCase();
                    double transactionPrice = Double.parseDouble(parts[4]);

                    if ((startDate == null || transactionDate.isEqual(startDate) || transactionDate.isAfter(startDate)) &&
                            (endDate == null || transactionDate.isEqual(endDate) || transactionDate.isBefore(endDate)) &&
                            (description.isEmpty() || transactionDescription.toLowerCase().contains(description.toLowerCase())) &&
                            (vendor.isEmpty() || transactionVendor.toLowerCase().contains(vendor.toLowerCase())) &&
                            (transactionPrice >= minPrice && transactionPrice <= maxPrice)) {
                        System.out.println(line);
                    }
                }
            }
            System.out.println("\n______________________________________End of your filtered transactions_____________________________________\n");
        } catch (IOException e) {
            System.out.println("Error reading transactions file: " + e.getMessage());
        } catch (DateTimeParseException | NumberFormatException e) {
            System.out.println("Error parsing transaction data: " + e.getMessage());
        }
    }
}


