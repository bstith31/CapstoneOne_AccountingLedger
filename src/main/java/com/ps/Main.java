package com.ps;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

    //Globally declaring scanner
    static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {

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
                                                                    
                                    Please enter your choice: 
                                    """);
                    int businessChoice = scanner.nextInt();
                    scanner.nextLine();
                    switch (businessChoice) {
                        case 1:
                            System.out.println("1");
                            // Methods being worked on for deposits makeDeposit();
                            break;
                        case 2:
                            System.out.println("2");
                            // Methods being worked on for payments makePayment();
                            break;
                        case 3:
                            System.out.println("3");
                            //Access Ledger
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
                                                                    
                                    Please enter your choice:
                                    """);
                    int personalChoice = scanner.nextInt();
                    scanner.nextLine();
                    switch (personalChoice) {
                        case 1:
                            System.out.println("1");
                            // Methods being worked on for deposits makeDeposit();
                            break;
                        case 2:
                            System.out.println("2");
                            // Methods being worked on for payments makePayment();
                            break;
                        case 3:
                            System.out.println("3");
                            //Access Ledger
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

//    public static void makeDeposit() {
//
//        try (BufferedWriter bufreader = new BufferedWriter(new FileWriter("transactions.txt", true))) {
//            System.out.println("============================Welcome to the Deposits Menu============================");
//            System.out.println("      Please enter the following information to accurately log your deposit         ");
//
//            System.out.print("Please enter a deposit amount: $");
//            double depositAmount = scanner.nextDouble();
//            scanner.nextLine();
//
//            System.out.print("Will you be using the current date to log your transaction (Y/N): ");
//            String depositChoice = scanner.nextLine().toUpperCase();
//
//            String dateTime;
//            boolean validInput = true;
//            if (depositChoice.equals("Y")) {
//                // Using the current date and time
//                LocalDateTime now = LocalDateTime.now();
//                dateTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//            } else if (depositChoice.equals("N")) {
//                // Using the user's date and time
//                System.out.print("Please enter the date and time (yyyy-MM-dd HH:mm:ss): ");
//                dateTime = scanner.nextLine();
//            } else {
//                System.out.println("Invalid input. Please enter 'Y' or 'N'.");
//                validInput = false;
//                dateTime = null;
//            }
//
//            if (validInput) {
//                // Format the deposit information
//                String formattedDeposit = String.format("%s|%s|%.2f%n", dateTime, "Deposit", depositAmount);
//
////                // Write the formatted deposit information to the file
////                bufreader.write(formattedDeposit);
////                bufreader.flush();
//
//                System.out.println("Your deposit has been successfully logged.");
//            } else {
//                makeDeposit(); // Re-prompt
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }
}