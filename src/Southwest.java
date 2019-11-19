import java.util.ArrayList;

/**
 * A class for the Southwest airline.
 *
 * @author Macie Wheeler and Keya Mahtani
 * @version November 18, 2019
 */
public class Southwest implements Airline {

    private ArrayList<Passenger> southwestPassengers = new ArrayList<>();
    private static Gate southwestGate = new Gate();
    private static String nameOfAirline = "Southwest Airlines";

    public void addPassenger(Passenger passenger) {
        southwestPassengers.add(passenger);
    }

    public ArrayList<Passenger> getDeltaPassengers() {
        return southwestPassengers;
    }

    public int getCurrentPassengerCount() {
        return southwestPassengers.size();
    }

    public int getMaxPassenger() {
        return passengerMax;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public int getAvailableSeats() {
        return passengerMax - southwestPassengers.size();
    }

    public static String getNameOfAirline() {
        return nameOfAirline;
    }

    public static String getSouthwestGateToString() {
        return southwestGate.getGate();
    }
    public static Gate getSouthwestGate() {
        return southwestGate;
    }

    public String getAirlineMessage() {
        return "Southwest Airlines is proud to offer flights to Purdue University." + "\n" +
                "We are happy to offer free inflight WiFi, as well as our amazing snacks." + "\n" +
                "In addition, we offer flights for much cheaper than other airlines, and offer two free checked" +
                " bags." + "\n" +
                "We hope you choose Southwest for your next flight.";
    }
}
