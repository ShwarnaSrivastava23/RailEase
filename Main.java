import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ReservationSystem system = new ReservationSystem();

        // ==============================
        // ADD TRAINS
        // ==============================

        system.addTrain(new Train(
                101,
                "Gorakhpur Local",
                "Local",
                "Gorakhpur",
                "Deoria",
                "06:00 AM",
                "07:30 AM",
                72
        ));

        system.addTrain(new Train(
                102,
                "Delhi Local",
                "Local",
                "Delhi",
                "Agra",
                "07:00 AM",
                "10:00 AM",
                72
        ));

        system.addTrain(new Train(
                103,
                "Mumbai Local",
                "Local",
                "Mumbai",
                "Pune",
                "08:00 AM",
                "11:00 AM",
                72
        ));

        system.addTrain(new Train(
                104,
                "Kolkata Local",
                "Local",
                "Kolkata",
                "Durgapur",
                "06:30 AM",
                "08:30 AM",
                72
        ));

        system.addTrain(new Train(
                105,
                "Chennai Local",
                "Local",
                "Chennai",
                "Pondicherry",
                "07:30 AM",
                "10:30 AM",
                72
        ));

        Scanner sc = new Scanner(System.in);

        // ==============================
        // MAIN MENU
        // ==============================

        while (true) {

            System.out.println();
            System.out.println("================================");
            System.out.println("          RAIL EASE");
            System.out.println("================================");
            System.out.println("1. View All Trains");
            System.out.println("2. Search Train");
            System.out.println("3. Book Ticket");
            System.out.println("4. View Booking");
            System.out.println("5. View Seat Map");
            System.out.println("6. Cancel Booking");
            System.out.println("7. Exit");
            System.out.println("================================");

            System.out.print("Enter your choice: ");

            if (!sc.hasNextInt()) {

                System.out.println(
                        "Invalid input. Please enter a number from 1 to 7."
                );

                sc.nextLine();
                continue;
            }

            int choice = sc.nextInt();

            if (choice < 1 || choice > 7) {

                System.out.println(
                        "Invalid choice. Please enter a number from 1 to 7."
                );

                continue;
            }

            switch (choice) {

                // ==============================
                // 1. VIEW ALL TRAINS
                // ==============================

                case 1:

                    system.viewAllTrains();

                    break;

                // ==============================
                // 2. SEARCH TRAIN
                // ==============================

                case 2:

                    sc.nextLine();

                    System.out.print("Enter source: ");
                    String source = sc.nextLine().trim();

                    System.out.print("Enter destination: ");
                    String destination = sc.nextLine().trim();

                    if (source.isEmpty()
                            || destination.isEmpty()) {

                        System.out.println(
                                "Source and destination cannot be empty."
                        );

                        break;
                    }

                    boolean found = false;

                    System.out.println();
                    System.out.println("================================");
                    System.out.println("          TRAIN SEARCH");
                    System.out.println("================================");

                    for (Train train : system.getTrains()) {

                        if (train.getSource()
                                .equalsIgnoreCase(source)
                                && train.getDestination()
                                .equalsIgnoreCase(destination)) {

                            System.out.println();

                            System.out.println(
                                    "Train Number : "
                                            + train.getTrainNumber()
                            );

                            System.out.println(
                                    "Train Name   : "
                                            + train.getTrainName()
                            );

                            System.out.println(
                                    "Train Type   : "
                                            + train.getTrainType()
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
                                    "Available    : "
                                            + train.getAvailableSeats()
                            );

                            System.out.println(
                                    "--------------------------------"
                            );

                            found = true;
                        }
                    }

                    if (!found) {

                        System.out.println(
                                "No train found for this route."
                        );
                    }

                    break;

                // ==============================
                // 3. BOOK TICKET
                // ==============================

                case 3:

                    sc.nextLine();

                    System.out.print("Enter source: ");
                    String bookingSource =
                            sc.nextLine().trim();

                    System.out.print("Enter destination: ");
                    String bookingDestination =
                            sc.nextLine().trim();

                    if (bookingSource.isEmpty()
                            || bookingDestination.isEmpty()) {

                        System.out.println(
                                "Source and destination cannot be empty."
                        );

                        break;
                    }

                    boolean trainFound = false;

                    System.out.println();
                    System.out.println("================================");
                    System.out.println("       AVAILABLE TRAINS");
                    System.out.println("================================");

                    for (Train train : system.getTrains()) {

                        if (train.getSource()
                                .equalsIgnoreCase(bookingSource)
                                && train.getDestination()
                                .equalsIgnoreCase(bookingDestination)
                                && train.getAvailableSeats() > 0) {

                            System.out.println(
                                    train.getTrainNumber()
                                            + " | "
                                            + train.getTrainName()
                                            + " | "
                                            + train.getDepartureTime()
                                            + " - "
                                            + train.getArrivalTime()
                                            + " | Seats: "
                                            + train.getAvailableSeats()
                            );

                            trainFound = true;
                        }
                    }

                    if (!trainFound) {

                        System.out.println(
                                "No available train found for this route."
                        );

                        break;
                    }

                    System.out.println(
                            "================================"
                    );

                    System.out.print(
                            "Enter train number: "
                    );

                    if (!sc.hasNextInt()) {

                        System.out.println(
                                "Invalid train number."
                        );

                        sc.nextLine();
                        break;
                    }

                    int trainNumber = sc.nextInt();

                    Train selectedTrain =
                            system.findTrain(trainNumber);

                    if (selectedTrain == null) {

                        System.out.println(
                                "Train not found."
                        );

                        break;
                    }

                    if (!selectedTrain.getSource()
                            .equalsIgnoreCase(bookingSource)
                            || !selectedTrain.getDestination()
                            .equalsIgnoreCase(bookingDestination)) {

                        System.out.println(
                                "Selected train does not match the route."
                        );

                        break;
                    }

                    if (selectedTrain.getAvailableSeats() <= 0) {

                        System.out.println(
                                "No seats available on this train."
                        );

                        break;
                    }

                    // ==============================
                    // PASSENGER DETAILS
                    // ==============================

                    sc.nextLine();

                    System.out.print(
                            "Enter passenger name: "
                    );

                    String name =
                            sc.nextLine().trim();

                    if (name.isEmpty()) {

                        System.out.println(
                                "Passenger name cannot be empty."
                        );

                        break;
                    }

                    System.out.print(
                            "Enter passenger age: "
                    );

                    if (!sc.hasNextInt()) {

                        System.out.println(
                                "Invalid age."
                        );

                        sc.nextLine();
                        break;
                    }

                    int age = sc.nextInt();

                    if (age <= 0 || age > 120) {

                        System.out.println(
                                "Please enter a valid age."
                        );

                        break;
                    }

                    sc.nextLine();

                    System.out.print(
                            "Enter passenger gender: "
                    );

                    String gender =
                            sc.nextLine().trim();

                    if (gender.isEmpty()) {

                        System.out.println(
                                "Gender cannot be empty."
                        );

                        break;
                    }

                    Passenger passenger =
                            new Passenger(
                                    name,
                                    age,
                                    gender
                            );

                    // ==============================
                    // PAYMENT
                    // ==============================

                    System.out.println();
                    System.out.println("================================");
                    System.out.println("          PAYMENT");
                    System.out.println("================================");
                    System.out.println("Ticket Fare : ₹500");
                    System.out.println();
                    System.out.println("1. UPI");
                    System.out.println("2. Debit/Credit Card");
                    System.out.println("3. Net Banking");
                    System.out.println("4. Cash");
                    System.out.println("================================");

                    System.out.print(
                            "Select payment method: "
                    );

                    if (!sc.hasNextInt()) {

                        System.out.println(
                                "Invalid payment option."
                        );

                        sc.nextLine();
                        break;
                    }

                    int paymentChoice =
                            sc.nextInt();

                    // IMPORTANT:
                    // Initialize the variable so Java
                    // does not report an error.

                    String paymentMethod = "";

                    switch (paymentChoice) {

                        case 1:

                            paymentMethod = "UPI";

                            break;

                        case 2:

                            paymentMethod =
                                    "Debit/Credit Card";

                            break;

                        case 3:

                            paymentMethod =
                                    "Net Banking";

                            break;

                        case 4:

                            paymentMethod = "Cash";

                            break;

                        default:

                            System.out.println(
                                    "Invalid payment option."
                            );

                            break;
                    }

                    if (paymentChoice < 1
                            || paymentChoice > 4) {

                        break;
                    }

                    // ==============================
                    // BOOK TICKET
                    // ==============================

                    system.bookTicket(
                            trainNumber,
                            passenger,
                            paymentMethod
                    );

                    break;

                // ==============================
                // 4. VIEW BOOKING
                // ==============================

                case 4:

                    System.out.print(
                            "Enter booking ID: "
                    );

                    if (!sc.hasNextInt()) {

                        System.out.println(
                                "Invalid booking ID."
                        );

                        sc.nextLine();
                        break;
                    }

                    int bookingId = sc.nextInt();

                    system.viewBooking(bookingId);

                    break;

                // ==============================
                // 5. VIEW SEAT MAP
                // ==============================

                case 5:

                    System.out.print(
                            "Enter train number: "
                    );

                    if (!sc.hasNextInt()) {

                        System.out.println(
                                "Invalid train number."
                        );

                        sc.nextLine();
                        break;
                    }

                    int seatMapTrainNumber =
                            sc.nextInt();

                    system.viewSeatMap(
                            seatMapTrainNumber
                    );

                    break;

                // ==============================
                // 6. CANCEL BOOKING
                // ==============================

                case 6:

                    System.out.print(
                            "Enter booking ID: "
                    );

                    if (!sc.hasNextInt()) {

                        System.out.println(
                                "Invalid booking ID."
                        );

                        sc.nextLine();
                        break;
                    }

                    int cancelBookingId =
                            sc.nextInt();

                    system.cancelBooking(
                            cancelBookingId
                    );

                    break;

                // ==============================
                // 7. EXIT
                // ==============================

                case 7:

                    System.out.println();
                    System.out.println(
                            "Thank you for using RailEase!"
                    );
                    System.out.println();

                    sc.close();

                    return;
            }
        }
    }
}