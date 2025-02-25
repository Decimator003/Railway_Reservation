package model;

public class Seat {
    private int seatNumber;
    private String trainId;
    private boolean isBooked;

    public Seat(int seatNumber, String trainId) {
        this.seatNumber = seatNumber;
        this.trainId = trainId;
        this.isBooked = false;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public String getTrainId() {
        return trainId;
    }

    public void bookSeat() {
        this.isBooked = true;
    }
}