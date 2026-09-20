public class Train {

    private int trainNumber;
    private String trainName;
    private String trainType;
    private String source;
    private String destination;
    private String departureTime;
    private String arrivalTime;
    private int totalSeats;
    private int availableSeats;

    Train(int trainNumber, String trainName, String trainType,
          String source, String destination,
          String departureTime, String arrivalTime,
          int totalSeats) {

        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.trainType = trainType;
        this.source = source;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
    }

    public int getTrainNumber() {
        return trainNumber;
    }

    public String getTrainName() {
        return trainName;
    }

    public String getTrainType() {
        return trainType;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void decreaseSeat() {
        if (availableSeats > 0) {
            availableSeats--;
        }
    }

    public void increaseSeat() {
        if (availableSeats < totalSeats) {
            availableSeats++;
        }
    }
}