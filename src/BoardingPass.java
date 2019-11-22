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

    private Passenger passenger;
    private String  airline;
    private Gate gate;
    private String gateToString;
    private String airlineName;
    public BoardingPass(Passenger p, String  a) {
        this.passenger = p;
        this.airline = a;
        if (airline.equals("Delta")) {
            this.gate = Delta.getDeltaGate();
            this.gateToString = Delta.getDeltaGateString();
            this.airlineName = Delta.getNameOfAirline();
        }
        if (airline.equals("Southwest")) {
            this.gate = Southwest.getSouthwestGate();
            this.gateToString = Southwest.getSouthwestGateToString();
            this.airlineName = Southwest.getNameOfAirline();
        }
        if (airline.equals("Alaska")) {
            this.gate = Alaska.getAlaskaGate();
            this.gateToString = Alaska.getAlaskaGateToString();
            this.airlineName = Alaska.getNameOfAirline();
        }

    }
    public void setGate(String s) {
        this.gateToString = s;
    }


    public String writeBoardingPass() {
        String boardingPass = "<html><center>------------------------------------------" + "<br>" +
                "BOARDING PASS FOR FLIGHT 18000 WITH " + airlineName + "<br>" +
                "PASSENGER FIRST NAME: " + passenger.getFirstName() + "<br>" +
                "PASSENGER LAST NAME: " + passenger.getLastName() + "<br>" +
                "PASSENGER AGE: " + passenger.getAge() + "<br>" +
                "You can now begin boarding at gate " + gateToString + "<br>" +
                "----------------------------------------</center></html>";
        return boardingPass;
    }
}
