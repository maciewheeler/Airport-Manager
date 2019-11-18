import java.io.IOException;

/**
 * A runner for a ReservationServer instance.
 *
 * Used code from homework 11's CountdownRunner as a base of our code.
 *
 * @author Macie Wheeler and Keya Mahtani
 * @version November 18, 2019
 */

public class ReservationRunner {

    /**
     * Creates a {@code CountdownServer} instance, then begins to serve clients.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ReservationServer server;

        try {
            server = new ReservationServer();
        } catch (IOException e) {
            e.printStackTrace();

            return;
        } //end try catch

        server.serveClients();
    } //main
}
