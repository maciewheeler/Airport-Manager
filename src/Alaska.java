/**
 * A class for the Alaska airline.
 *
 * @author Macie Wheeler and Keya Mahtani
 * @version November 18, 2019
 */
public class Alaska implements Airline {
   private boolean full = false; //true when flight is full
    private Gate gate;
    private int passengerCounter = passengers.size();
    final int flightNumber = 18000;
    public void statusChanger() {
        if (passengerCounter >= passengerMax) {
            full = true;
        }
    }
    public int getPassengerCounter() {
        return passengerCounter;
    }
    public void setGate() {
        if (passengerCounter > 0) {
            gate = new Gate (this.gate.gateNumber, this.gate.terminal);
        }
    }
    @Override
    public String getNameOfAirline() {
        return "Alaska";
    }

    public int getFlightNumber() {
        return flightNumber;
    }
}
