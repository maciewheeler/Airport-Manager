import java.io.Serializable;
import java.util.Random;

/**
 * A class to create a gate.
 *
 * @author Macie Wheeler and Keya Mahtani
 * @version November 18, 2019
 */

public class Gate implements Serializable {

    int gateNumber;
    String letter;

    public Gate(int gateNumber, String letter) {
        this.gateNumber = gateNumber;
        this.letter = letter;
    }

    public void getGateNumber() {
        Random rand = new Random();
        this.gateNumber = rand.nextInt(18);
    }

    public void getGateLetter() {
        Random rand = new Random();
        int gateLetter = rand.nextInt(3);
        switch (gateLetter) {
            case 1:
                this.letter = "A";
                break;
            case 2:
                this.letter = "B";
                break;
            case 3:
                this.letter = "C";
                break;
        }
    }
    
    public String getGate(int gateNumber, String letter) {
        return this.letter + this.gateNumber;
    }
}
