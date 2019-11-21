import java.io.Serializable;

/**
 * An interface for the airline.
 *
 * @author Macie Wheeler and Keya Mahtani
 * @version November 18, 2019
 */

public interface Airline extends Serializable {

    int passengerMax = 100;
    int flightNumber = 18000;

    void addPassenger(Passenger passenger);

    int getCurrentPassengerCount();

    int getMaxPassenger();

    int getFlightNumber();

    int getAvailableSeats();

}
//gates printing differently
//spacing of lines
//airline messages combobox
//if the button had to go back a frame 
//scroll pane
//refresh flight status
//text boxes not working
//back slash
//server client
