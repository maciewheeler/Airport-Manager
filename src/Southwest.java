import java.util.ArrayList;

/**
 * A class for the Southwest airline.
 *
 * @author Macie Wheeler and Keya Mahtani
 * @version November 18, 2019
 */
public class Southwest implements Airline {

    private static ArrayList<Passenger> southwestPassengers = new ArrayList<>();
    private final static Gate SOUTHWEST_GATE = new Gate();
    private static String nameOfAirline = "Southwest Airlines";

    public static void addPassenger(Passenger passenger) {
        southwestPassengers.add(passenger);
    }

    public ArrayList<Passenger> getDeltaPassengers() {
        return southwestPassengers;
    }

    public int getCurrentPassengerCount() {
        return southwestPassengers.size();
    }

    public int getMaxPassenger() {
        return PASSENGER_MAX;
    }

    public int getFlightNumber() {
        return FLIGHT_NUMBER;
    }

    public int getAvailableSeats() {
        return PASSENGER_MAX - southwestPassengers.size();
    }

    public static String getNameOfAirline() {
        return nameOfAirline;
    }

    public final static String getSouthwestGateToString() {
        return SOUTHWEST_GATE.getGate();
    }
    public final static Gate getSouthwestGate() {
        return SOUTHWEST_GATE;
    }

    public static String getAirlineMessage() {
        return "<html><center>Southwest Airlines is proud to offer flights to Purdue University." + "<br>" +
                "We are happy to offer free inflight WiFi, as well as our amazing snacks." + "<br>" +
                "In addition, we offer flights for much cheaper than other airlines, and offer two free checked" +
                " bags." + "<br>" +
                "We hope you choose Southwest for your next flight.</center></html>";
    }
}
