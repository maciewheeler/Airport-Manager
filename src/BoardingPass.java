/**
 * A class that creates a boarding pass for each customer.
 *
 * @author Macie Wheeler and Keya Mahtani
 * @version November 18, 2019
 */

public class BoardingPass extends Passenger implements Airline {

    public BoardingPass(String firstName, String lastName, int age) {
        super(firstName, lastName, age);
    }

    public String writeBoardingPass() {
        String boardingPass = "----------------------------------------" + "\n" +
                "BOARDING PASS FOR FLIGHT 18000 WITH " + "\n" +
                "PASSENGER FIRST NAME: " + super.getFirstName() + "\n" +
                "PASSENGER LAST NAME: " + super.getLastName() + "\n" +
                "PASSENGER AGE: " + super.getAge() + "\n" +
                "You can now begin boarding at gate " + "\n" + 
                "----------------------------------------";
        return boardingPass;
    }
}

//possibly have this extend passenger and gate????
//and have implement airline???
