# Capstone One - Accounting Ledger

## Application Greeting and Welcome Screen
___
Upon entering the application, the user is requested to input a name and whether they are using
the application for business or personal transactions. 

![img.png](src/main/resources/img.png)

## Main Menu
___
The main menu allows to the user to choose from the four options of the application.
In this portion ***Add Deposit*** and ***Make a Payment*** will be covered, as the
ledger has more detail that will be covered in its own section.

![img_1.png](src/main/resources/img_1.png)

<details>

**<summary>Add Deposit</summary>**

When a user chooses to add a deposit they are given the following fields to log details on:

1. **Date/Time**: Immediately when choosing *Add Deposit*, user can automatically allow the 
application to log the exact date and time, if not they are allowed to enter a custom time.
2. **Description**: For the purposes of a deposit this can be whether it was a payment made to
the user or if they received money in some capacity,
3. **Vendor**: For the purpose of a deposit this would be who was paying out the transaction.
4. **Deposit Amount**: The monetary amount of the transaction.

Users will then be prompted if they want to enter another deposit, which allows them to continue.
If not they will be returned to the main menu

![img_2.png](src/main/resources/img_2.png)

</details>

<details>

**<summary>Make a Payment</summary>**
When a user chooses to add a deposit they are given the following fields to log details on:

1. Date/Time: Immediately when choosing *Make a Payment*, user can automatically allow the
   application to log the exact date and time, if not they are allowed to enter a custom time.
2. Description: For the purposes of a payment this can be the product that was purchased.
3. Vendor: For the purpose of a payment this would be who the product was purchased from.
4. Payment Amount: The monetary amount of the transaction.

![img_3.png](src/main/resources/img_3.png)

Users will then be prompted if they want to enter another payment, which allows them to continue.
If not they will be returned to the main menu

</details>

## Ledger
___
Accessing the Ledger menu offers a generalized scope of what a user may want to see.
It offers the option to print every transaction or print them out individually categorized by
_deposit_ or _payment_. A user may also access a separate ***Reports Menu*** or return to the main menu.

![img_6.png](src/main/resources/img_6.png)

<detail>

**<summary>Print Out All Transactions</summary>**

![img_11.png](src/main/resources/img_11.png)

</detail>

<detail>

**<summary>Print Out Deposits</summary>**

![img_12.png](src/main/resources/img_12.png)

</detail>

<detail>

**<summary>Print Out Payments</summary>**

![img_13.png](src/main/resources/img_13.png)

</detail>


## Access Reports
___

Accessing the Reports menu offers user further control over what they want to see. Here they
are able to print out highly specific reports from one of five options depending on what they
may be wanting to see.

![img_5.png](src/main/resources/img_5.png)

<details>

**<summary> Month to Date:</summary>**

![img_7.png](src/main/resources/img_7.png)

</details>

<details>

**<summary> Previous Month:</summary>**

![img_8.png](src/main/resources/img_8.png)


</details>

<details>

**<summary> Year to Date:</summary>**

![img_9.png](src/main/resources/img_9.png)


</details>

<details>

**<summary> Previous Year:</summary>**

![img_10.png](src/main/resources/img_10.png)


</details>

<details>

**<summary> Custom Search:</summary>**

For ___Custom Search___ the user can filter along each of the following options:
1. Minimum Price
2. Maximum Price
3. Start Date
4. End Date
5. Description
6. Vendor

![img_14.png](src/main/resources/img_14.png)

![img_15.png](src/main/resources/img_15.png)


</details>

## Error Handling

Inputting an invalid option on any of the screens will display an invalid choice message
and will re-prompt the user to input a valid option:

![img_16.png](src/main/resources/img_16.png)

Inputting an incorrect monetary value for a deposit or payment will reset the process,
ensuring it is logged correctly:

![img_17.png](src/main/resources/img_17.png)

## Interesting Piece of Code

The Temporal Adjuster class was used for to generate these particular ranges for the reports.
Given that it is specifically made for date adjustments it was useful in calculating the specific
dates required.

![img_18.png](src/main/resources/img_18.png)






