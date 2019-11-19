import java.util.ArrayList;

/**
 * A class for the Alaska airline.
 *
 * @author Macie Wheeler and Keya Mahtani
 * @version November 18, 2019
 */
public class Alaska implements Airline {

    private ArrayList<Passenger> alaskaPassengers = new ArrayList<>();
    private final Gate alaskaGate = new Gate();
    private String nameOfAirline = "Alaska Airlines";

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

    public String getNameOfAirline() {
        return nameOfAirline;
    }

    public String getDeltaGate() {
        return alaskaGate.getGate();
    }

    public String getAirlineMessage() {
        return "Alaska Airlines is proud to serve the strong and knowledgeable Boilermakers from Purdue" +
                " University." + "\n" +
                "We primarily westward, and often have stops in Alaska and California." + "\n" +
                "We have first class amenities, even in coach class." + "\n" +
                "We provide fun snacks such as pretzels and goldfish." + "\n" +
                "We also have comfortable seats and free WiFi." + "\n" +
                "We hope you choose Alaska Airlines for your next itinerary.";
    }
}
