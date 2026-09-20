public class Booking {

    private int bookingId;
    private Train train;
    private Passenger passenger;
    private int seatNumber;
    private String coach;
    private String berthType;

    private int fare;
    private String paymentMethod;
    private String paymentStatus;

    Booking(int bookingId,
            Train train,
            Passenger passenger,
            int seatNumber,
            String coach,
            String berthType,
            int fare,
            String paymentMethod,
            String paymentStatus) {

        this.bookingId = bookingId;
        this.train = train;
        this.passenger = passenger;
        this.seatNumber = seatNumber;
        this.coach = coach;
        this.berthType = berthType;
        this.fare = fare;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
    }

    public int getBookingId() {
        return bookingId;
    }

    public Train getTrain() {
        return train;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public String getCoach() {
        return coach;
    }

    public String getBerthType() {
        return berthType;
    }

    public int getFare() {
        return fare;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }
}