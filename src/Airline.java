import java.io.Serializable;

/**
 * An interface for the airline.
 *
 * @author Macie Wheeler and Keya Mahtani
 * @version November 18, 2019
 */

public interface Airline extends Serializable {

    int passengerMax = 100;

    String getNameOfAirline();

    int getFlightNumber();
}
