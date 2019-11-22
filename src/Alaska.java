import java.util.ArrayList;

/**
 * A class for the Alaska airline.
 *
 * @author Macie Wheeler and Keya Mahtani
 * @version November 18, 2019
 */
public class Alaska implements Airline {

    private ArrayList<Passenger> alaskaPassengers = new ArrayList<>();
    private static Gate alaskaGate = new Gate();
    private static String nameOfAirline = "Alaska Airlines";

    public void addPassenger(Passenger passenger) {
        alaskaPassengers.add(passenger);
    }

    public ArrayList<Passenger> getAlaskaPassengers() {
        return alaskaPassengers;
    }

    public int getCurrentPassengerCount() {
        return alaskaPassengers.size();
    }

    public int getMaxPassenger() {
        return passengerMax;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public int getAvailableSeats() {
        return passengerMax - alaskaPassengers.size();
    }

    public static String getNameOfAirline() {
        return nameOfAirline;
    }

    public static String getAlaskaGateToString() {
        return alaskaGate.getGate();
    }
    public static Gate getAlaskaGate() {
        return alaskaGate;
    }

    public static String getAirlineMessage() {
        return "<html><center>Alaska Airlines is proud to serve the strong and knowledgeable Boilermakers from Purdue" +
                " University." + "<br>" +
                "We primarily westward, and often have stops in Alaska and California." + "<br>" +
                "We have first class amenities, even in coach class." + "<br>" +
                "We provide fun snacks such as pretzels and goldfish." + "<br>" +
                "We also have comfortable seats and free WiFi." + "<br>" +
                "We hope you choose Alaska Airlines for your next itinerary.</center></html>";
    }
}
