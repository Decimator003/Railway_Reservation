import service.RailwayReservationSystem;
import model.*;

public class Main {
    public static void main(String[] args) {
        RailwayReservationSystem system = new RailwayReservationSystem();

        // Add a train
        system.addTrain("T123", "Express", 100);

        // Add a user
        system.addUser("U1", "John Doe", "john@example.com", "1234567890");

        // Book a ticket
        String bookingId = system.bookTicket("U1", "T123", 1, "2023-10-25");
        System.out.println("Booking ID: " + bookingId);

        // Try to book another ticket on the same train (should fail)
        try {
            system.bookTicket("U1", "T123", 2, "2023-10-25");
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage()); // Output: User can book only one ticket per train.
        }

        // Cancel the ticket
        boolean cancellationStatus = system.cancelTicket(bookingId);
        System.out.println("Cancellation status: " + cancellationStatus);
    }
}