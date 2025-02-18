package service;

import model.*;
import java.util.*;
import java.util.logging.Logger;

public class RailwayReservationSystem {
    private Map<String, Train> trains;
    private Map<String, User> users;
    private Map<String, Booking> bookings;
    private int bookingCounter;
    private static final Logger logger = Logger.getLogger(RailwayReservationSystem.class.getName());
    private Map<String, WaitingList> waitingLists; // Map of trainId -> WaitingList

    public RailwayReservationSystem() {
        this.trains = new HashMap<>();
        this.users = new HashMap<>();
        this.bookings = new HashMap<>();
        this.bookingCounter = 1;
        this.waitingLists = new HashMap<>();
    }

    public void addTrain(String trainId, String trainName, int totalSeats) {
        trains.put(trainId, new Train(trainId, trainName, totalSeats));
    }

    public void addUser(String userId, String name, String email, String phoneNumber) {
        users.put(userId, new User(userId, name, email, phoneNumber));
    }

    public String bookTicket(String userId, String trainId, int seatNumber, String date) {
        validateDate(date);
        validateSeatNumber(trainId, seatNumber);

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
        double amount  = 0.0; // Amount to be paid
        if (train.bookSeat(seatNumber, bookingId)) {
            Booking booking = new Booking(bookingId, trainId, userId, seatNumber, date);
            bookings.put(bookingId, booking);
            user.addBooking(booking);

            // Process payment
            Payment payment = new Payment("P" + bookingCounter++, bookingId, amount);
            Map<String, Payment> payments = new HashMap<>();
            payments.put(payment.getPaymentId(), payment);

            logger.info("Ticket booked successfully. Booking ID: " + bookingId);
            return bookingId;
        } else {
            WaitingList waitingList = waitingLists.computeIfAbsent(trainId, k -> new WaitingList());
            waitingList.addUser(userId);
            logger.info("Train is full. Added user to waiting list.");
            throw new IllegalStateException("Train is full. You have been added to the waiting list.");
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

    private void validateDate(String date) {
        if (!date.matches("\\d{4}-\\d{2}-\\d{2}")) {
            throw new IllegalArgumentException("Invalid date format. Use yyyy-MM-dd.");
        }
    }

    // Validate email format
    private void validateEmail(String email) {
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("Invalid email format.");
        }
    }

    // Validate phone number (10 digits)
    private void validatePhoneNumber(String phoneNumber) {
        if (!phoneNumber.matches("\\d{10}")) {
            throw new IllegalArgumentException("Invalid phone number. Must be 10 digits.");
        }
    }

    // Validate seat number (must be between 1 and totalSeats)
    private void validateSeatNumber(String trainId, int seatNumber) {
        Train train = trains.get(trainId);
        if (train == null) {
            throw new IllegalArgumentException("Train not found.");
        }
        if (seatNumber < 1 || seatNumber > train.getTotalSeats()) {
            throw new IllegalArgumentException("Invalid seat number. Must be between 1 and " + train.getTotalSeats() + ".");
        }
    }

    public List<Train> searchTrains(String startStation, String endStation, String date) {
        List<Train> availableTrains = new ArrayList<>();
        for (Train train : trains.values()) {
            if (train.getRoute().getStartStation().getStationName().equals(startStation) &&
                train.getRoute().getEndStation().getStationName().equals(endStation)) {
                availableTrains.add(train);
            }
        }
        return availableTrains;
    }
}