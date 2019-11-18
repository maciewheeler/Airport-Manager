/**
 * A class for the Delta airline.
 *
 * @author Macie Wheeler and Keya Mahtani
 * @version November 18, 2019
 */

public class Delta implements Airline {
    int passengerCounter = 0;
    boolean full = false; //true when flight is full

    public void statusChanger() {
        if (passengerCounter >= passengerMax) {
            full = true;
        }
    }
    
    public String getName() {
        return "Delta";
    }
}
