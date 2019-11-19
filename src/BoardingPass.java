/**
 * A class that creates a boarding pass for each customer.
 *
 * @author Macie Wheeler and Keya Mahtani
 * @version November 18, 2019
 */

import java.io.Serializable;

/**
 * A class that creates a boarding pass for each customer.
 *
 * @author Macie Wheeler and Keya Mahtani
 * @version November 18, 2019
 */

public class BoardingPass implements Serializable {

    Passenger passenger;
    Airline airline;
    Gate gate;
    private String airlineName;
    private final int flightNumber = 18000;


    public BoardingPass(Passenger p, Airline a, Gate g) {
        this.passenger = p;
        this.airline = a;
        this.gate = g;
    }
    public String getNameOfAirline() {
        return airlineName;
    }

    public int getFlightNumber() {
        return flightNumber;
    }
    public void addPassenger(Passenger p) {
        this.airline.passengers.add(p);
    }



    public String writeBoardingPass() {
        String boardingPass = "----------------------------------------" + "\n" +
                "BOARDING PASS FOR FLIGHT 18000 WITH " + "\n" +
                "PASSENGER FIRST NAME: " + passenger.getFirstName() + "\n" +
                "PASSENGER LAST NAME: " + passenger.getLastName() + "\n" +
                "PASSENGER AGE: " + passenger.getAge() + "\n" +
                "You can now begin boarding at gate " + gate.getGate() + "\n" +
                "----------------------------------------";
        return boardingPass;
    }
}
