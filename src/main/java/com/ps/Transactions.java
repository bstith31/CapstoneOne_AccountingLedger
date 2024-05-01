package com.ps;

public class Transactions {

    private String date;
    private String time;
    private String description;
    private String vendor;
    private double price;

    public Transactions() {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.price = price;
    }

    public Transactions(String date, String time, String description, String vendor) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVendor() {
        return vendor;
    }
    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    @Override
    public String toString() {
        return "Transactions{" +
                "date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", description='" + description + '\'' +
                ", vendor='" + vendor + '\'' +
                ", price=" + price +
                '}';
    }


    //Greg's static example
//    Transaction transaction1 = new Transaction(
//            LocalDate.of(2024, 3, 14),
//            LocalTime.of(14, 43, 05, 0),
//            "Monstera Plant",
//            "Amazon",
//            39.99f
//    );
    // Transaction transaction1 = new Transaction (Pass it everything in main)
    //brute

    // ArrayList<Transactions> transaction = new ArrayList<>();
    // transaction.add(transaction1);
    // transaction.add(transaction2);

    //Generate a report - Month to Date

    //for (Transaction transaction : transaction){
        // LocalDate transactionDate = transaction.getDate();
        // LocalDate dateNow = transaction.getDate();
        // if(transactionData.getMonthValue == dateNow.getMonthValue())
            //sout format of report


}
