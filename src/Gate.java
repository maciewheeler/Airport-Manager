import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 *
 * @author Macie Wheeler and Keya Mahtani
 * @version November 18, 2019
 */

public class Gate implements Serializable {
    Random rand = new Random();
    int gateNumber = rand.nextInt(18);

    int gateLetter = rand.nextInt(3);
    String letter;
    public void convertLetter(int gateLetter) {
        switch (gateLetter) {
            case 1:
                letter = "A";
                break;
            case 2:
                letter = "B";
                break;
            case 3:
                letter = "C";
                break;
        }
    }

    public Gate(int gateNumber, String letter) {
        this.gateNumber = gateNumber;
        this.letter = letter;
    }

}
