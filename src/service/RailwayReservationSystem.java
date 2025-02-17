package service;

import model.*;
import java.util.*;

public class RailwayReservationSystem {
    private Map<String, Train> trains;
    private Map<String, User> users;
    private Map<String, Booking> bookings;
    private int bookingCounter;

    public RailwayReservationSystem() {
        this.trains = new HashMap<>();
        this.users = new HashMap<>();
        this.bookings = new HashMap<>();
        this.bookingCounter = 1;
    }

    public void addTrain(String trainId, String trainName, int totalSeats) {
        trains.put(trainId, new Train(trainId, trainName, totalSeats));
    }

    public void addUser(String userId, String name, String email, String phoneNumber) {
        users.put(userId, new User(userId, name, email, phoneNumber));
    }

    public String bookTicket(String userId, String trainId, int seatNumber, String date) {
        if (!users.containsKey(userId)) {
            throw new IllegalArgumentException("User not found.");
        }

        if (!trains.containsKey(trainId)) {
            throw new IllegalArgumentException("Train not found.");
        }

        User user = users.get(userId);
        Train train = trains.get(trainId);

        // Check if the user already has a booking for this train and date
        for (Booking booking : user.getBookings()) {
            if (booking.getTrainId().equals(trainId) && booking.getDate().equals(date)) {
                throw new IllegalStateException("User can book only one ticket per train.");
            }
        }

        // Book the seat
        String bookingId = "B" + bookingCounter++;
        if (train.bookSeat(seatNumber, bookingId)) {
            Booking booking = new Booking(bookingId, trainId, userId, seatNumber, date);
            bookings.put(bookingId, booking);
            user.addBooking(booking);
            return bookingId;
        } else {
            throw new IllegalStateException("No seats available.");
        }
    }

    public boolean cancelTicket(String bookingId) {
        if (!bookings.containsKey(bookingId)) {
            throw new IllegalArgumentException("Booking not found.");
        }

        Booking booking = bookings.get(bookingId);
        Train train = trains.get(booking.getTrainId());

        if (train.cancelSeat(bookingId)) {
            booking.cancelBooking();
            return true;
        } else {
            return false;
        }
    }
}