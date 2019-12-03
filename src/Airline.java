import java.io.Serializable;

/**
 * An interface for the airline.
 *
 * @author Macie Wheeler and Keya Mahtani
 * @version November 18, 2019
 */

public interface Airline extends Serializable {

    int PASSENGER_MAX = 100;
    int FLIGHT_NUMBER = 18000;

    int getCurrentPassengerCount();

    int getMaxPassenger();

    int getFlightNumber();

    int getAvailableSeats();

}
