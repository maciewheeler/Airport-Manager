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
    String terminal;


    public Gate(int gateNumber, String terminal) {
        this.gateNumber = gateNumber;
        this.terminal = terminal;
    }
    public void getGateLetter() {
        Random rand = new Random();
        int gateLetter = rand.nextInt(3);
        switch (gateLetter) {
            case 0:
                this.terminal = "A";
                break;
            case 1:
                this.terminal = "B";
                break;
            case 2:
                this.terminal = "C";
                break;
        }
    }


    public void getGateNumber() {
        Random rand = new Random();
        this.gateNumber = rand.nextInt(18);
    }

    public String getGate() {
        return this.terminal + this.gateNumber;
    }
}
