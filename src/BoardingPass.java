/**
 * A class that creates a boarding pass for each customer.
 *
 * @author Macie Wheeler and Keya Mahtani
 * @version November 18, 2019
 */

public class BoardingPass extends Passenger implements Airline {

    private String airlineName;
    private final int flightNumber = 18000;

    public BoardingPass(String firstName, String lastName, int age) {
        super(firstName, lastName, age);
        this.airlineName = getNameOfAirline();
    }

    public String getNameOfAirline() {
        return airlineName;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public String writeBoardingPass() {
        String boardingPass = "----------------------------------------" + "\n" +
                "BOARDING PASS FOR FLIGHT " + getFlightNumber() + " WITH " + getNameOfAirline() + "\n" +
                "PASSENGER FIRST NAME: " + super.getFirstName() + "\n" +
                "PASSENGER LAST NAME: " + super.getLastName() + "\n" +
                "PASSENGER AGE: " + super.getAge() + "\n" +
                "You can now begin boarding at gate " + "\n" +
                "----------------------------------------";
        return boardingPass;
    }
}
