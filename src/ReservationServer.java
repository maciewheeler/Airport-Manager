import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;
/**
 * A server that can handle multiple clients simultaneously. Tracks and records ticket sales by writing to
 * reservations.txt
 *
 * We used the code from HW11 as a base for this class
 * @author Macie Wheeler and Keya Mahtani
 * @version November 18, 2019
 */

public class ReservationServer {

    /**
     * The server socket of this server.
     */
    private ServerSocket serverSocket;

    /**
     * Constructs a newly allocated object.
     *
     * @throws IOException if an I/O exception occurs
     */
    public ReservationServer() throws IOException {
        this.serverSocket = new ServerSocket(0);
    } //ReservationServer

    /**
     * Serves the clients that connect to this server.
     */
    public void serveClients() {
        Socket clientSocket;
        ClientHandler handler;
        Thread handlerThread;
        int connectionCount = 0;

        System.out.printf("<Now serving clients on port %d...>%n", this.serverSocket.getLocalPort());

        while (true) {
            try {
                clientSocket = this.serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();

                break;
            } //end try catch

            handler = new ClientHandler(clientSocket);

            handlerThread = new Thread(handler);

            handlerThread.start();

            System.out.printf("<Client %d connected...>%n", connectionCount);

            connectionCount++;
        } //end while
    } //serveClients

    /**
     * Returns the hash code of this server.
     *
     * @return the hash code of this server
     */
    @Override
    public int hashCode() {
        int result = 23;

        result = result * 31 * Objects.hashCode(this.serverSocket);

        return result;
    } //hashCode

    /**
     * Determines whether or not the specified object is equal to this server. {@code true} is returned if and only if
     * the specified object is an instance of {@code ReservationServer} and its server socket is equal to this server's.
     *
     * @param object the object to be used in the comparisons
     * @return {@code true}, if the specified object is equal to this server and {@code false} otherwise
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (object instanceof ReservationServer) {
            return Objects.equals(this.serverSocket, ((ReservationServer) object).serverSocket);
        } else {
            return false;
        } //end if
    } //equals

    /**
     * Returns the {@code String} representation of this server. The returned {@code String} consists of this server's
     * server socket surrounded by this class' name and square brackets ("[]").
     *
     * @return the {@code String} representation of this server
     */
    @Override
    public String toString() {
        String format = "ReservationServer[%s]";

        return String.format(format, this.serverSocket);
    } //toString
}
}
