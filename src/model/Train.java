package model;

import java.util.HashMap;
import java.util.Map;

public class Train {
    private String trainId;
    private String trainName;
    private int totalSeats;
    private int availableSeats;
    private Map<Integer, String> bookedSeats; // SeatNumber -> BookingId

    public Train(String trainId, String trainName, int totalSeats) {
        this.trainId = trainId;
        this.trainName = trainName;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
        this.bookedSeats = new HashMap<>();
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public boolean bookSeat(int seatNumber, String bookingId) {
        if (availableSeats > 0 && !bookedSeats.containsKey(seatNumber)) {
            bookedSeats.put(seatNumber, bookingId);
            availableSeats--;
            return true;
        }
        return false;
    }

    public boolean cancelSeat(String bookingId) {
        for (Map.Entry<Integer, String> entry : bookedSeats.entrySet()) {
            if (entry.getValue().equals(bookingId)) {
                bookedSeats.remove(entry.getKey());
                availableSeats++;
                return true;
            }
        }
        return false;
    }
}