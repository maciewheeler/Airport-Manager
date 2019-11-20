import java.io.Serializable;

/**
 * A class to create a passenger.
 *
 * @author Macie Wheeler and Keya Mahtani
 * @version November 18, 2019
 */

public class Passenger implements Serializable {

    private String firstName;
    private String lastName;
    private int age;

    public Passenger() {
        this.firstName = getFirstName();
        this.lastName = getLastName();
        this.age = getAge();
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
