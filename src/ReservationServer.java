import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

/**
 * A server that can handle multiple clients simultaneously. Tracks and records ticket sales by writing to
 * reservations.txt
 *
 * We used the code from HW11 as a base for this class.
 *
 * @author Macie Wheeler and Keya Mahtani
 * @version November 18, 2019
 */

public final class ReservationServer {

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
            }

            handler = new ClientHandler(clientSocket);

            handlerThread = new Thread(handler);

            handlerThread.start();

            System.out.printf("<Client %d connected...>%n", connectionCount);

            connectionCount++;
        }
    } //serveClients

    public void updateFile() throws FileNotFoundException, IOException {
        try {
            File f = new File("reservations.txt");
            FileReader fr = new FileReader(f);
            FileWriter fw = new FileWriter(f);
            BufferedReader bfr = new BufferedReader(fr);
            BufferedWriter bfw = new BufferedWriter(fw);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

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
        }
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

class ClientHandler implements Runnable {
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
