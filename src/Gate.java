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

    public Gate() {
        this.gateNumber = getGateNumber();
        this.terminal = getGateLetter();
    }

    public String getGateLetter() {
        Random rand = new Random();
        int gateLetter = rand.nextInt(3);
        String gateLetterAsString = "";
        switch (gateLetter) {
            case 0:
                gateLetterAsString = "A";
                break;
            case 1:
                gateLetterAsString = "B";
                break;
            case 2:
                gateLetterAsString = "C";
                break;
        }
        return gateLetterAsString;
    }

    public int getGateNumber() {
        Random rand = new Random();
        return rand.nextInt(18) + 1;
    }

    public String getGate() {
        return getGateLetter() + getGateNumber();
    }
}
