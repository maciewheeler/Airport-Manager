import java.io.*;
import java.net.Socket;
import java.util.Objects;

/**
 * A request handler that processes the inputs from the client
 *
 * We used the code from HW11 as a base for this class
 *
 * @author Macie Wheeler and Keya Mahtani
 * @version November 18, 2019
 */
public class ClientHandler implements Runnable {
    /**
     * The client socket of this request handler.
     */
    private Socket clientSocket;

    /**
     * Constructs a newly allocated {@code ReservationRequestHandler} object with the specified client socket.
     *
     * @param clientSocket the client socket to be used in construction
     * @throws NullPointerException if the specified client socket is {@code null}
     */
    public ClientHandler(Socket clientSocket) throws NullPointerException {
        Objects.requireNonNull(clientSocket, "the specified client socket is null");

        this.clientSocket = clientSocket;
    }

    /**
     * Handles the requests sent by the client connected to this request handler's client socket.
     */
    @Override
    public void run() {
        BufferedReader reader = null;
        BufferedWriter writer = null;
        String line;

        try {
            reader = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));

            writer = new BufferedWriter(new OutputStreamWriter(this.clientSocket.getOutputStream()));

            line = reader.readLine();

            while (line != null) {
                writer.write(line);

                writer.newLine();

                writer.flush();

                line = reader.readLine();
            } //end while
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } //end try catch
            } //end if

            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } //end try catch
            } //end if
        } //end try catch finally
    } //run

    /**
     * Returns the hash code of this request handler.
     *
     * @return the hash code of this request handler
     */
    @Override
    public int hashCode() {
        int result = 23;

        result = result * 31 * Objects.hashCode(this.clientSocket);

        return result;
    } //hashCode

    /**
     * Determines whether or not the specified object is equal to this request handler. {@code true} is returned if and
     * only if the specified object is an instance of {@code CountdownRequestHandler} and its client socket is equal to
     * this request handler's.
     *
     * @param object the object to be used in the comparisons
     * @return {@code true}, if the specified object is equal to this request handler and {@code false} otherwise
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (object instanceof ClientHandler) {
            return Objects.equals(this.clientSocket, ((ClientHandler) object).clientSocket);
        } else {
            return false;
        }
    } //equals

    /**
     * Returns the {@code String} representation of this request handler. The returned {@code String} consists of this
     * request handler's client socket surrounded by this class' name and square brackets ("[]").
     *
     * @return the {@code String} representation of this request handler
     */
    @Override
    public String toString() {
        String format = "CountdownRequestHandler[%s]";

        return String.format(format, this.clientSocket);
    } //toString
}

//edit run method, i think that the actual stuff needs to go inside the client or the runner possibly??, i think the
//run here just needs to be edited from the code i input and we need to add the weird stuff about that text file
