import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class RailEaseServer {

    private static ReservationSystem system = new ReservationSystem();

    public static void main(String[] args) throws Exception {

        addTrains();

        HttpServer server = HttpServer.create(
                new InetSocketAddress(8080), 0
        );

        server.createContext("/", RailEaseServer::handleHome);
        server.createContext("/style.css", RailEaseServer::handleCSS);
        server.createContext("/script.js", RailEaseServer::handleJS);
        server.createContext("/api/search", RailEaseServer::handleSearch);
        server.createContext("/api/book", RailEaseServer::handleBooking);

        server.setExecutor(null);
        server.start();

        System.out.println("======================================");
        System.out.println("       RailEase Server Started");
        System.out.println("======================================");
        System.out.println("Open: http://localhost:8080");
    }

    // =========================================================
    // TRAIN DATA
    // =========================================================

    private static void addTrains() {

        system.addTrain(new Train(
                101, "Delhi-Kanpur Express", "Express",
                "Delhi", "Kanpur",
                "06:30 AM", "11:15 AM", 72
        ));

        system.addTrain(new Train(
                102, "Delhi-Kanpur Intercity", "Express",
                "Delhi", "Kanpur",
                "08:00 AM", "12:30 PM", 72
        ));

        system.addTrain(new Train(
                103, "Delhi-Kanpur Superfast", "Superfast",
                "Delhi", "Kanpur",
                "02:00 PM", "06:15 PM", 72
        ));

        system.addTrain(new Train(
                104, "Delhi-Kanpur Night Express", "Express",
                "Delhi", "Kanpur",
                "09:30 PM", "02:30 AM", 72
        ));

        system.addTrain(new Train(
                105, "Delhi-Lucknow Express", "Express",
                "Delhi", "Lucknow",
                "07:00 AM", "03:00 PM", 72
        ));

        system.addTrain(new Train(
                106, "Delhi-Lucknow Superfast", "Superfast",
                "Delhi", "Lucknow",
                "10:00 PM", "06:30 AM", 72
        ));

        system.addTrain(new Train(
                107, "Delhi-Varanasi Express", "Express",
                "Delhi", "Varanasi",
                "06:00 AM", "06:00 PM", 72
        ));

        system.addTrain(new Train(
                108, "Delhi-Varanasi Superfast", "Superfast",
                "Delhi", "Varanasi",
                "08:30 PM", "08:00 AM", 72
        ));

        system.addTrain(new Train(
                109, "Delhi-Agra Express", "Express",
                "Delhi", "Agra",
                "07:30 AM", "10:00 AM", 72
        ));

        system.addTrain(new Train(
                110, "Delhi-Jaipur Express", "Express",
                "Delhi", "Jaipur",
                "06:45 AM", "12:30 PM", 72
        ));

        system.addTrain(new Train(
                111, "Delhi-Gorakhpur Express", "Express",
                "Delhi", "Gorakhpur",
                "04:00 PM", "07:30 AM", 72
        ));

        system.addTrain(new Train(
                112, "Delhi-Patna Express", "Express",
                "Delhi", "Patna",
                "05:30 PM", "07:00 AM", 72
        ));

        system.addTrain(new Train(
                201, "Delhi-Kanpur Vande Bharat", "Vande Bharat",
                "Delhi", "Kanpur",
                "06:00 AM", "09:00 AM", 72
        ));

        system.addTrain(new Train(
                202, "Delhi-Lucknow Vande Bharat", "Vande Bharat",
                "Delhi", "Lucknow",
                "06:30 AM", "12:45 PM", 72
        ));

        system.addTrain(new Train(
                203, "Delhi-Varanasi Vande Bharat", "Vande Bharat",
                "Delhi", "Varanasi",
                "06:00 AM", "02:00 PM", 72
        ));

        system.addTrain(new Train(
                204, "Delhi-Dehradun Vande Bharat", "Vande Bharat",
                "Delhi", "Dehradun",
                "07:00 AM", "11:30 AM", 72
        ));

        system.addTrain(new Train(
                205, "Delhi-Agra Vande Bharat", "Vande Bharat",
                "Delhi", "Agra",
                "08:00 AM", "10:00 AM", 72
        ));

        system.addTrain(new Train(
                301, "Delhi-Mumbai Rajdhani", "Rajdhani",
                "Delhi", "Mumbai",
                "04:55 PM", "08:35 AM", 72
        ));

        system.addTrain(new Train(
                302, "Delhi-Patna Rajdhani", "Rajdhani",
                "Delhi", "Patna",
                "05:00 PM", "07:40 AM", 72
        ));

        system.addTrain(new Train(
                303, "Delhi-Ranchi Rajdhani", "Rajdhani",
                "Delhi", "Ranchi",
                "04:30 PM", "09:00 AM", 72
        ));

        system.addTrain(new Train(
                304, "Delhi-Bhubaneswar Rajdhani", "Rajdhani",
                "Delhi", "Bhubaneswar",
                "05:00 PM", "10:30 AM", 72
        ));

        system.addTrain(new Train(
                401, "Delhi-Kanpur Shatabdi", "Shatabdi",
                "Delhi", "Kanpur",
                "06:00 AM", "10:00 AM", 72
        ));

        system.addTrain(new Train(
                402, "Delhi-Lucknow Shatabdi", "Shatabdi",
                "Delhi", "Lucknow",
                "06:10 AM", "12:30 PM", 72
        ));

        system.addTrain(new Train(
                403, "Delhi-Chandigarh Shatabdi", "Shatabdi",
                "Delhi", "Chandigarh",
                "07:00 AM", "10:30 AM", 72
        ));

        system.addTrain(new Train(
                404, "Delhi-Dehradun Shatabdi", "Shatabdi",
                "Delhi", "Dehradun",
                "07:20 AM", "11:30 AM", 72
        ));

        system.addTrain(new Train(
                501, "Lucknow-Varanasi Express", "Express",
                "Lucknow", "Varanasi",
                "07:00 AM", "01:30 PM", 72
        ));

        system.addTrain(new Train(
                502, "Lucknow-Gorakhpur Express", "Express",
                "Lucknow", "Gorakhpur",
                "08:00 AM", "01:00 PM", 72
        ));

        system.addTrain(new Train(
                503, "Kanpur-Prayagraj Express", "Express",
                "Kanpur", "Prayagraj",
                "09:00 AM", "12:00 PM", 72
        ));

        system.addTrain(new Train(
                504, "Varanasi-Gorakhpur Express", "Express",
                "Varanasi", "Gorakhpur",
                "06:30 AM", "11:00 AM", 72
        ));

        system.addTrain(new Train(
                505, "Mumbai-Ahmedabad Express", "Express",
                "Mumbai", "Ahmedabad",
                "07:00 AM", "01:00 PM", 72
        ));

        system.addTrain(new Train(
                506, "Mumbai-Pune Express", "Express",
                "Mumbai", "Pune",
                "08:00 AM", "11:30 AM", 72
        ));

        system.addTrain(new Train(
                601, "Kolkata-Delhi Express", "Express",
                "Kolkata", "Delhi",
                "06:00 PM", "06:00 PM", 72
        ));

        system.addTrain(new Train(
                602, "Jaipur-Delhi Express", "Express",
                "Jaipur", "Delhi",
                "07:00 AM", "01:00 PM", 72
        ));

        system.addTrain(new Train(
                603, "Amritsar-Delhi Express", "Express",
                "Amritsar", "Delhi",
                "06:30 AM", "01:30 PM", 72
        ));

        system.addTrain(new Train(
                604, "Patna-Delhi Express", "Express",
                "Patna", "Delhi",
                "05:00 PM", "07:00 AM", 72
        ));
    }

    // =========================================================
    // HOME PAGE
    // =========================================================

    private static void handleHome(HttpExchange exchange)
            throws IOException {

        File file = new File("web/index.html");

        if (!file.exists()) {
            sendResponse(
                    exchange,
                    "index.html not found",
                    404,
                    "text/plain"
            );
            return;
        }

        byte[] content = Files.readAllBytes(file.toPath());

        exchange.getResponseHeaders().set(
                "Content-Type",
                "text/html; charset=UTF-8"
        );

        exchange.sendResponseHeaders(200, content.length);

        OutputStream output = exchange.getResponseBody();
        output.write(content);
        output.close();
    }

    // =========================================================
    // CSS
    // =========================================================

    private static void handleCSS(HttpExchange exchange)
            throws IOException {

        File file = new File("web/style.css");

        if (!file.exists()) {
            sendResponse(
                    exchange,
                    "style.css not found",
                    404,
                    "text/plain"
            );
            return;
        }

        byte[] content = Files.readAllBytes(file.toPath());

        exchange.getResponseHeaders().set(
                "Content-Type",
                "text/css; charset=UTF-8"
        );

        exchange.sendResponseHeaders(200, content.length);

        OutputStream output = exchange.getResponseBody();
        output.write(content);
        output.close();
    }

    // =========================================================
    // JAVASCRIPT
    // =========================================================

    private static void handleJS(HttpExchange exchange)
            throws IOException {

        File file = new File("web/script.js");

        if (!file.exists()) {
            sendResponse(
                    exchange,
                    "script.js not found",
                    404,
                    "text/plain"
            );
            return;
        }

        byte[] content = Files.readAllBytes(file.toPath());

        exchange.getResponseHeaders().set(
                "Content-Type",
                "application/javascript; charset=UTF-8"
        );

        exchange.sendResponseHeaders(200, content.length);

        OutputStream output = exchange.getResponseBody();
        output.write(content);
        output.close();
    }

    // =========================================================
    // SEARCH TRAIN
    // =========================================================

    private static void handleSearch(HttpExchange exchange)
            throws IOException {

        Map<String, String> params =
                parseQuery(exchange.getRequestURI().getQuery());

        String source = params.get("source");
        String destination = params.get("destination");
        String trainType = params.get("trainType");

        StringBuilder json = new StringBuilder();
        json.append("[");

        boolean first = true;

        for (Train train : system.getTrains()) {

            boolean sourceMatch =
                    source == null
                            || source.isEmpty()
                            || train.getSource()
                            .equalsIgnoreCase(source);

            boolean destinationMatch =
                    destination == null
                            || destination.isEmpty()
                            || train.getDestination()
                            .equalsIgnoreCase(destination);

            boolean typeMatch =
                    trainType == null
                            || trainType.isEmpty()
                            || train.getTrainType()
                            .equalsIgnoreCase(trainType);

            if (sourceMatch && destinationMatch && typeMatch) {

                if (!first) {
                    json.append(",");
                }

                json.append("{");

                json.append("\"trainNumber\":")
                        .append(train.getTrainNumber())
                        .append(",");

                json.append("\"trainName\":\"")
                        .append(escapeJson(train.getTrainName()))
                        .append("\",");

                json.append("\"trainType\":\"")
                        .append(escapeJson(train.getTrainType()))
                        .append("\",");

                json.append("\"source\":\"")
                        .append(escapeJson(train.getSource()))
                        .append("\",");

                json.append("\"destination\":\"")
                        .append(escapeJson(train.getDestination()))
                        .append("\",");

                json.append("\"departureTime\":\"")
                        .append(escapeJson(train.getDepartureTime()))
                        .append("\",");

                json.append("\"arrivalTime\":\"")
                        .append(escapeJson(train.getArrivalTime()))
                        .append("\",");

                json.append("\"availableSeats\":")
                        .append(train.getAvailableSeats())
                        .append(",");

                json.append("\"totalSeats\":")
                        .append(train.getTotalSeats());

                json.append("}");

                first = false;
            }
        }

        json.append("]");

        sendResponse(
                exchange,
                json.toString(),
                200,
                "application/json"
        );
    }

    // =========================================================
    // BOOK TICKET + DEMO PAYMENT
    // =========================================================

    private static void handleBooking(HttpExchange exchange)
            throws IOException {

        Map<String, String> params =
                parseQuery(exchange.getRequestURI().getQuery());

        String trainNumberText = params.get("trainNumber");
        String name = params.get("name");
        String ageText = params.get("age");
        String gender = params.get("gender");
        String paymentMethod = params.get("paymentMethod");

        // -----------------------------
        // Validate passenger details
        // -----------------------------

        if (trainNumberText == null
                || name == null
                || ageText == null
                || gender == null
                || paymentMethod == null
                || name.trim().isEmpty()
                || gender.trim().isEmpty()
                || paymentMethod.trim().isEmpty()) {

            sendResponse(
                    exchange,
                    "{\"success\":false,\"message\":\"Please provide all passenger and payment details.\"}",
                    400,
                    "application/json"
            );

            return;
        }

        int trainNumber;
        int age;

        try {

            trainNumber =
                    Integer.parseInt(trainNumberText);

            age =
                    Integer.parseInt(ageText);

        } catch (NumberFormatException e) {

            sendResponse(
                    exchange,
                    "{\"success\":false,\"message\":\"Invalid train number or age.\"}",
                    400,
                    "application/json"
            );

            return;
        }

        if (age < 1 || age > 120) {

            sendResponse(
                    exchange,
                    "{\"success\":false,\"message\":\"Please enter a valid age.\"}",
                    400,
                    "application/json"
            );

            return;
        }

        // -----------------------------
        // Find train
        // -----------------------------

        Train selectedTrain =
                system.findTrain(trainNumber);

        if (selectedTrain == null) {

            sendResponse(
                    exchange,
                    "{\"success\":false,\"message\":\"Train not found.\"}",
                    404,
                    "application/json"
            );

            return;
        }

        // -----------------------------
        // Check seats
        // -----------------------------

        if (selectedTrain.getAvailableSeats() <= 0) {

            sendResponse(
                    exchange,
                    "{\"success\":false,\"message\":\"No seats available.\"}",
                    400,
                    "application/json"
            );

            return;
        }

        // -----------------------------
        // Passenger
        // -----------------------------

        Passenger passenger =
                new Passenger(
                        name.trim(),
                        age,
                        gender.trim()
                );

        // -----------------------------
        // Demo payment
        // -----------------------------

        int fare = 500;

        String finalPaymentMethod =
                paymentMethod.trim();

        String paymentStatus = "PAID";

        System.out.println();
        System.out.println("======================================");
        System.out.println("           DEMO PAYMENT");
        System.out.println("======================================");
        System.out.println("Fare           : ₹" + fare);
        System.out.println("Payment Method : " + finalPaymentMethod);
        System.out.println("Payment Status : " + paymentStatus);
        System.out.println("======================================");

        // -----------------------------
        // Create booking
        // -----------------------------

        system.bookTicket(
                trainNumber,
                passenger,
                finalPaymentMethod
        );

        Booking latestBooking =
                system.getLatestBooking();

        if (latestBooking == null) {

            sendResponse(
                    exchange,
                    "{\"success\":false,\"message\":\"Booking could not be completed.\"}",
                    500,
                    "application/json"
            );

            return;
        }

        // -----------------------------
        // JSON response
        // -----------------------------

        String json =
                "{"
                        + "\"success\":true,"
                        + "\"bookingId\":"
                        + latestBooking.getBookingId()
                        + ","
                        + "\"seatNumber\":"
                        + latestBooking.getSeatNumber()
                        + ","
                        + "\"coach\":\""
                        + escapeJson(latestBooking.getCoach())
                        + "\","
                        + "\"berthType\":\""
                        + escapeJson(latestBooking.getBerthType())
                        + "\","
                        + "\"trainNumber\":"
                        + selectedTrain.getTrainNumber()
                        + ","
                        + "\"trainName\":\""
                        + escapeJson(selectedTrain.getTrainName())
                        + "\","
                        + "\"source\":\""
                        + escapeJson(selectedTrain.getSource())
                        + "\","
                        + "\"destination\":\""
                        + escapeJson(selectedTrain.getDestination())
                        + "\","
                        + "\"departureTime\":\""
                        + escapeJson(selectedTrain.getDepartureTime())
                        + "\","
                        + "\"arrivalTime\":\""
                        + escapeJson(selectedTrain.getArrivalTime())
                        + "\","
                        + "\"passenger\":\""
                        + escapeJson(passenger.getName())
                        + "\","
                        + "\"fare\":"
                        + latestBooking.getFare()
                        + ","
                        + "\"paymentMethod\":\""
                        + escapeJson(
                                latestBooking.getPaymentMethod()
                        )
                        + "\","
                        + "\"paymentStatus\":\""
                        + escapeJson(
                                latestBooking.getPaymentStatus()
                        )
                        + "\""
                        + "}";

        System.out.println();
        System.out.println("======================================");
        System.out.println("          BOOKING CONFIRMED");
        System.out.println("======================================");

        System.out.println(
                "Booking ID    : "
                        + latestBooking.getBookingId()
        );

        System.out.println(
                "Passenger     : "
                        + passenger.getName()
        );

        System.out.println(
                "Train         : "
                        + selectedTrain.getTrainName()
        );

        System.out.println(
                "Train Number  : "
                        + selectedTrain.getTrainNumber()
        );

        System.out.println(
                "From          : "
                        + selectedTrain.getSource()
        );

        System.out.println(
                "To            : "
                        + selectedTrain.getDestination()
        );

        System.out.println(
                "Coach         : "
                        + latestBooking.getCoach()
        );

        System.out.println(
                "Berth Number  : "
                        + latestBooking.getSeatNumber()
        );

        System.out.println(
                "Berth Type    : "
                        + latestBooking.getBerthType()
        );

        System.out.println(
                "Fare          : ₹"
                        + latestBooking.getFare()
        );

        System.out.println(
                "Payment       : "
                        + latestBooking.getPaymentMethod()
        );

        System.out.println(
                "Status        : "
                        + latestBooking.getPaymentStatus()
        );

        System.out.println("======================================");

        sendResponse(
                exchange,
                json,
                200,
                "application/json"
        );
    }

    // =========================================================
    // QUERY STRING PARSER
    // =========================================================

    private static Map<String, String> parseQuery(String query) {

        Map<String, String> result =
                new HashMap<>();

        if (query == null || query.isEmpty()) {
            return result;
        }

        String[] pairs =
                query.split("&");

        for (String pair : pairs) {

            String[] parts =
                    pair.split("=", 2);

            if (parts.length == 2) {

                String key =
                        URLDecoder.decode(
                                parts[0],
                                StandardCharsets.UTF_8
                        );

                String value =
                        URLDecoder.decode(
                                parts[1],
                                StandardCharsets.UTF_8
                        );

                result.put(key, value);
            }
        }

        return result;
    }

    // =========================================================
    // JSON ESCAPE
    // =========================================================

    private static String escapeJson(String text) {

        if (text == null) {
            return "";
        }

        return text
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r");
    }

    // =========================================================
    // SEND RESPONSE
    // =========================================================

    private static void sendResponse(
            HttpExchange exchange,
            String response,
            int statusCode,
            String contentType) throws IOException {

        byte[] bytes =
                response.getBytes(
                        StandardCharsets.UTF_8
                );

        exchange.getResponseHeaders().set(
                "Content-Type",
                contentType + "; charset=UTF-8"
        );

        exchange.sendResponseHeaders(
                statusCode,
                bytes.length
        );

        OutputStream output =
                exchange.getResponseBody();

        output.write(bytes);
        output.close();
    }
}