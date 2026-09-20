import java.util.ArrayList;
import java.util.List;

public class ReservationSystem {

    private final List<Train> trains;
    private final List<Booking> bookings;

    private int nextBookingId;

    public ReservationSystem() {

        trains = new ArrayList<>();
        bookings = new ArrayList<>();

        nextBookingId = 1001;
    }

    public void addTrain(Train train) {
        trains.add(train);
    }

    public List<Train> getTrains() {
        return trains;
    }

    public Train findTrain(int trainNumber) {

        for (Train train : trains) {

            if (train.getTrainNumber() == trainNumber) {
                return train;
            }
        }

        return null;
    }

    public String getBerthType(int seatNumber) {

        int position = ((seatNumber - 1) % 8) + 1;

        switch (position) {

            case 1:
                return "Lower";

            case 2:
                return "Middle";

            case 3:
                return "Upper";

            case 4:
                return "Lower";

            case 5:
                return "Middle";

            case 6:
                return "Upper";

            case 7:
                return "Side Lower";

            case 8:
                return "Side Upper";

            default:
                return "Lower";
        }
    }

    public String getCoach(int seatNumber) {

        int coachNumber = ((seatNumber - 1) / 72) + 1;

        return "S" + coachNumber;
    }

    private boolean isSeatBooked(int trainNumber, int seatNumber) {

        for (Booking booking : bookings) {

            if (booking.getTrain().getTrainNumber() == trainNumber
                    && booking.getSeatNumber() == seatNumber) {

                return true;
            }
        }

        return false;
    }

    // ==================================================
    // OLD METHOD
    // Keeps the existing RailEaseServer.java working
    // ==================================================

    public void bookTicket(
            int trainNumber,
            Passenger passenger) {

        bookTicket(
                trainNumber,
                passenger,
                "Demo Payment"
        );
    }

    // ==================================================
    // NEW METHOD WITH PAYMENT
    // ==================================================

    public void bookTicket(
            int trainNumber,
            Passenger passenger,
            String paymentMethod) {

        Train train = findTrain(trainNumber);

        if (train == null) {

            System.out.println("Train not found.");
            return;
        }

        if (train.getAvailableSeats() <= 0) {

            System.out.println("No seats available.");
            return;
        }

        int seatNumber = -1;

        for (int i = 1; i <= train.getTotalSeats(); i++) {

            if (!isSeatBooked(trainNumber, i)) {

                seatNumber = i;
                break;
            }
        }

        if (seatNumber == -1) {

            System.out.println("No seats available.");
            return;
        }

        String coach = getCoach(seatNumber);

        String berthType = getBerthType(seatNumber);

        // Demo fare
        int fare = 500;

        // Demo payment status
        String paymentStatus = "PAID";

        System.out.println();
        System.out.println("================================");
        System.out.println("          DEMO PAYMENT");
        System.out.println("================================");
        System.out.println("Fare           : ₹" + fare);
        System.out.println("Payment Method : " + paymentMethod);
        System.out.println();
        System.out.println("Processing payment...");
        System.out.println("Payment successful!");
        System.out.println("Payment Status : " + paymentStatus);
        System.out.println("================================");

        Booking booking = new Booking(
                nextBookingId,
                train,
                passenger,
                seatNumber,
                coach,
                berthType,
                fare,
                paymentMethod,
                paymentStatus
        );

        bookings.add(booking);

        train.decreaseSeat();

        System.out.println();
        System.out.println("================================");
        System.out.println("       TICKET CONFIRMED");
        System.out.println("================================");

        System.out.println(
                "Booking ID   : "
                        + booking.getBookingId()
        );

        System.out.println(
                "Passenger    : "
                        + passenger.getName()
        );

        System.out.println(
                "Train        : "
                        + train.getTrainName()
        );

        System.out.println(
                "Train Number : "
                        + train.getTrainNumber()
        );

        System.out.println(
                "From         : "
                        + train.getSource()
        );

        System.out.println(
                "To           : "
                        + train.getDestination()
        );

        System.out.println(
                "Departure    : "
                        + train.getDepartureTime()
        );

        System.out.println(
                "Arrival      : "
                        + train.getArrivalTime()
        );

        System.out.println(
                "Coach        : "
                        + coach
        );

        System.out.println(
                "Berth Number : "
                        + seatNumber
        );

        System.out.println(
                "Berth Type   : "
                        + berthType
        );

        System.out.println(
                "Fare         : ₹"
                        + fare
        );

        System.out.println(
                "Payment      : "
                        + paymentMethod
        );

        System.out.println(
                "Status       : "
                        + paymentStatus
        );

        System.out.println("================================");

        nextBookingId++;
    }

    public Booking getLatestBooking() {

        if (bookings.isEmpty()) {
            return null;
        }

        return bookings.get(bookings.size() - 1);
    }

    public Booking findBooking(int bookingId) {

        for (Booking booking : bookings) {

            if (booking.getBookingId() == bookingId) {
                return booking;
            }
        }

        return null;
    }

    public Booking findBookingBySeat(
            int trainNumber,
            int seatNumber) {

        for (Booking booking : bookings) {

            if (booking.getTrain().getTrainNumber() == trainNumber
                    && booking.getSeatNumber() == seatNumber) {

                return booking;
            }
        }

        return null;
    }

    public void viewSeatMap(int trainNumber) {

        Train train = findTrain(trainNumber);

        if (train == null) {

            System.out.println("Train not found.");
            return;
        }

        System.out.println();
        System.out.println("================================");
        System.out.println("          SEAT MAP");
        System.out.println("================================");

        System.out.println(
                "Train: "
                        + train.getTrainName()
                        + " ("
                        + train.getTrainNumber()
                        + ")"
        );

        System.out.println();

        for (int i = 1; i <= train.getTotalSeats(); i++) {

            String coach = getCoach(i);

            String berthType = getBerthType(i);

            Booking booking =
                    findBookingBySeat(
                            trainNumber,
                            i
                    );

            String status;

            if (booking == null) {
                status = "Available";
            } else {
                status = "Booked";
            }

            System.out.println(
                    "Seat " + i
                            + " | Coach: " + coach
                            + " | Berth: " + berthType
                            + " | " + status
            );
        }

        System.out.println("================================");
    }

    public void viewBooking(int bookingId) {

        Booking booking = findBooking(bookingId);

        if (booking == null) {

            System.out.println("Booking not found.");
            return;
        }

        Train train = booking.getTrain();

        Passenger passenger =
                booking.getPassenger();

        System.out.println();
        System.out.println("================================");
        System.out.println("        BOOKING DETAILS");
        System.out.println("================================");

        System.out.println(
                "Booking ID   : "
                        + booking.getBookingId()
        );

        System.out.println(
                "Passenger    : "
                        + passenger.getName()
        );

        System.out.println(
                "Age          : "
                        + passenger.getAge()
        );

        System.out.println(
                "Gender       : "
                        + passenger.getGender()
        );

        System.out.println();

        System.out.println(
                "Train        : "
                        + train.getTrainName()
        );

        System.out.println(
                "Train Number : "
                        + train.getTrainNumber()
        );

        System.out.println(
                "From         : "
                        + train.getSource()
        );

        System.out.println(
                "To           : "
                        + train.getDestination()
        );

        System.out.println(
                "Departure    : "
                        + train.getDepartureTime()
        );

        System.out.println(
                "Arrival      : "
                        + train.getArrivalTime()
        );

        System.out.println();

        System.out.println(
                "Coach        : "
                        + booking.getCoach()
        );

        System.out.println(
                "Berth Number : "
                        + booking.getSeatNumber()
        );

        System.out.println(
                "Berth Type   : "
                        + booking.getBerthType()
        );

        System.out.println();

        System.out.println(
                "Fare         : ₹"
                        + booking.getFare()
        );

        System.out.println(
                "Payment      : "
                        + booking.getPaymentMethod()
        );

        System.out.println(
                "Status       : "
                        + booking.getPaymentStatus()
        );

        System.out.println("================================");
    }

    public void cancelBooking(int bookingId) {

        Booking booking = findBooking(bookingId);

        if (booking == null) {

            System.out.println("Booking not found.");
            return;
        }

        Train train = booking.getTrain();

        train.increaseSeat();

        bookings.remove(booking);

        System.out.println();
        System.out.println("================================");
        System.out.println("       BOOKING CANCELLED");
        System.out.println("================================");

        System.out.println(
                "Booking ID : "
                        + bookingId
        );

        System.out.println(
                "Seat "
                        + booking.getSeatNumber()
                        + " is now available."
        );

        System.out.println("================================");
    }

    public void viewAllTrains() {

        System.out.println();
        System.out.println("================================");
        System.out.println("          ALL TRAINS");
        System.out.println("================================");

        for (Train train : trains) {

            System.out.println(
                    train.getTrainNumber()
                            + " | "
                            + train.getTrainName()
                            + " | "
                            + train.getTrainType()
                            + " | "
                            + train.getSource()
                            + " -> "
                            + train.getDestination()
                            + " | Departure: "
                            + train.getDepartureTime()
                            + " | Arrival: "
                            + train.getArrivalTime()
                            + " | Seats: "
                            + train.getAvailableSeats()
                            + "/"
                            + train.getTotalSeats()
            );
        }

        System.out.println("================================");
    }
}