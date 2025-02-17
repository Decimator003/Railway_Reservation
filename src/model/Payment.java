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

    public String getPaymentStatus() {
        return paymentStatus;
    }
}