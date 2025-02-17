package model;

public class Payment {
    private String paymentId;
    private String bookingId;
    private double amount;
    private String paymentStatus; // SUCCESS or FAILED

    public Payment(String paymentId, String bookingId, double amount) {
        this.paymentId = paymentId;
        this.bookingId = bookingId;
        this.amount = amount;
        this.paymentStatus = "SUCCESS";
    }

    public String getPaymentId() {
        return paymentId;
    }

    public String getBookingId() {
        return bookingId;
    }

    public double getAmount() {
        return amount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }
}