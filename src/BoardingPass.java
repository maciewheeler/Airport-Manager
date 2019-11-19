import java.io.Serializable;

/**
 * A class that creates a boarding pass for each customer.
 *
 * @author Macie Wheeler and Keya Mahtani
 * @version November 18, 2019
 */

public class BoardingPass implements Serializable {

    private Passenger passenger;
    private Airline airline;
    private Gate gate;
    private String gateToString;
    private String airlineName;
    
    public BoardingPass(Passenger p, Airline a) {
        this.passenger = p;
        this.airline = a;
        
        if (airline instanceof Delta) {
            this.gate = Delta.getDeltaGate();
            this.gateToString = Delta.getDeltaGateString();
            this.airlineName = Delta.getNameOfAirline();
        } else if (airline instanceof Southwest) {
            this.gate = Southwest.getSouthwestGate();
            this.gateToString = Southwest.getSouthwestGateToString();
            this.airlineName = Southwest.getNameOfAirline();
        } else if (airline instanceof Alaska) {
            this.gate = Alaska.getAlaskaGate();
            this.gateToString = Alaska.getAlaskaGateToString();
            this.airlineName = Alaska.getNameOfAirline();
        }

    }


    public String writeBoardingPass() {
        String boardingPass = "----------------------------------------" + "\n" +
                "BOARDING PASS FOR FLIGHT 18000 WITH " + airlineName + "\n" +
                "PASSENGER FIRST NAME: " + passenger.getFirstName() + "\n" +
                "PASSENGER LAST NAME: " + passenger.getLastName() + "\n" +
                "PASSENGER AGE: " + passenger.getAge() + "\n" +
                "You can now begin boarding at gate " + gateToString + "\n" +
                "----------------------------------------";
        return boardingPass;
    }
}
