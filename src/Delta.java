import java.util.ArrayList;

/**
 * A class for the Delta airline.
 *
 * @author Macie Wheeler and Keya Mahtani
 * @version November 18, 2019
 */
public class Delta implements Airline {

    private ArrayList<Passenger> deltaPassengers = new ArrayList<>();
    private static Gate deltaGate = new Gate();
    private static String nameOfAirline = "Delta Airlines";

    public void addPassenger(Passenger passenger) {
        deltaPassengers.add(passenger);
    }

    public ArrayList<Passenger> getDeltaPassengers() {
        return deltaPassengers;
    }

    public int getCurrentPassengerCount() {
        return deltaPassengers.size();
    }

    public int getMaxPassenger() {
        return passengerMax;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public int getAvailableSeats() {
        return passengerMax - deltaPassengers.size();
    }

    public static String getNameOfAirline() {
        return nameOfAirline;
    }

    public static String getDeltaGateString() {
        return deltaGate.getGate();
    }
    public static Gate getDeltaGate() {
        return deltaGate;
    }

    public static String getAirlineMessage() {
        return "<html><center>Delta Airlines is proud to be one of the five premier Airlines at Purdue University. " + "\n" +
                "We offer extremely exceptional services, with free limited WiFi for all customers." + "\n" +
                "Passengers who use T-Mobile as a cell phone carrier get additional benefits." + "\n" +
                "We are also happy to offer power outlets in each seat for passenger use." + "\n" +
                "We hope you choose to fly Delta as your next Airline.</center></html>";
    }
}
