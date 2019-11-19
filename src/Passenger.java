import java.io.Serializable;

/**
 * A class to create a passenger.
 *
 * @author Macie Wheeler and Keya Mahtani
 * @version November 18, 2019
 */

public class Passenger implements Serializable {

    String firstName;
    String lastName;
    int age;

    public Passenger(String fname, String lname, int age) {
        this.firstName = fname;
        this.lastName = lname;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
