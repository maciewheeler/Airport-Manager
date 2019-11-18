/**
 * A class for the Southwest airline.
 *
 * @author Macie Wheeler and Keya Mahtani
 * @version November 18, 2019
 */

public class Southwest implements Airline {
    int passengerCounter = 0;
    boolean full = false; //true when flight is full

    public void statusChanger() {
        if (passengerCounter >= passengerMax) {
            full = true;
        }
    }

    public String getName() {
        return "Southwest";
    }
}
