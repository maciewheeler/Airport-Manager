/**
 * A class for the Delta airline.
 *
 * @author Macie Wheeler and Keya Mahtani
 * @version November 18, 2019
 */

public class Delta implements Airline {
    int passengerCounter = 0;
    boolean full = false; //true when flight is full
    final int flightNumber = 18000;

    public void statusChanger() {
        if (passengerCounter >= passengerMax) {
            full = true;
        }
    }

    public String getNameOfAirline() {
        return "Delta";
    }

    public int getFlightNumber() {
        return flightNumber;
    }
}
