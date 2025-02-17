package model;

public class Booking {
    private String bookingId;
    private String trainId;
    private String userId;
    private int seatNumber;
    private String date;
    private String status; // BOOKED or CANCELLED

    public Booking(String bookingId, String trainId, String userId, int seatNumber, String date) {
        this.bookingId = bookingId;
        this.trainId = trainId;
        this.userId = userId;
        this.seatNumber = seatNumber;
        this.date = date;
        this.status = "BOOKED";
    }

    public String getTrainId() {
        return trainId;
    }

    public String getDate() {
        return date;
    }

    public void cancelBooking() {
        this.status = "CANCELLED";
    }
}