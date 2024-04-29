package com.ps;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

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

        System.out.println
                ("_______________________________________________________________________________________________________");
        System.out.print
                ("Hello!! " + name + ", What is your purpose for using us today, please state your intended purpose for today. \n" +
                        "Is it for (B) Business or is it for (P) Personal, please press the corresponding letter to begin:\s");

    }
}