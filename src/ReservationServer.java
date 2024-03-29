import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
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
        int port = this.serverSocket.getLocalPort();
        Socket clientSocket;
        ClientHandler handler;
        Thread handlerThread;
        int clientCount = 1;

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

            System.out.printf("<Client %d connected...>%n", clientCount);

            clientCount++;
        }
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

    /**
     * Creates a CountdownServer instance, then begins to serve clients.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ReservationServer server;
        try {
            server = new ReservationServer();
            server.serveClients();
        } catch (IOException e) {
            e.printStackTrace();

            return;
        }

    } //main
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

        try {
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());

            ArrayList<String> passengers = new ArrayList<>();
            FileReader fr = new FileReader("reservations.txt");
            BufferedReader bfr = new BufferedReader(fr);

            String line;
            while (true) {
                line = bfr.readLine();
                if (line != null) {
                    passengers.add(line);
                } else {
                    break;
                }
            }

//            for (int i = 0; i < passengers.size(); i++) {
//                oos.writeObject(passengers.get(i));
//                oos.flush();
//            }

            ArrayList<String> alaskaPassengers = new ArrayList<>();
            ArrayList<String> deltaPassengers = new ArrayList<>();
            ArrayList<String> southwestPassengers = new ArrayList<>();
            int deltaIndex = 0;
            int southwestIndex = 0;

            for (int i = 0; i < passengers.size(); i++) {
                if (passengers.get(i).equals("DELTA")) {
                    deltaIndex = i;
                } else if (passengers.get(i).equals("SOUTHWEST")) {
                    southwestIndex = i;
                }
            }

            for (int i = 0; i < deltaIndex; i++) {
                alaskaPassengers.add(passengers.get(i));
            }

            for (int i = deltaIndex; i < southwestIndex; i++) {
                deltaPassengers.add(passengers.get(i));
            }

            for (int i = southwestIndex; i < passengers.size(); i++) {
                southwestPassengers.add(passengers.get(i));
            }

            String passenger = ois.readObject().toString();
            if (passenger.contains("DELTA")) {
                deltaPassengers.add(passenger);
            } else if (passenger.contains("SOUTHWEST")) {
                southwestPassengers.add(passenger);
            } else if (passenger.contains("ALASKA")) {
                alaskaPassengers.add(passenger);
            }

            PrintWriter pw = new PrintWriter("reservations.txt");
            pw.print("");
            pw.close();

            FileWriter fw = new FileWriter("reservations.txt");
            for (int i = 0; i < alaskaPassengers.size(); i++) {
                fw.write(alaskaPassengers.get(i));
            }

            for (int i = 0; i < deltaPassengers.size(); i++) {
                fw.write(deltaPassengers.get(i));
            }

            for (int i = 0; i < southwestPassengers.size(); i++) {
                fw.write(southwestPassengers.get(i));
            }
            fw.close();
            passengers.clear();

            String newLine;
            while (true) {
                newLine = bfr.readLine();
                if (newLine != null) {
                    passengers.add(newLine);
                } else {
                    break;
                }
            }
            bfr.close();

            for (int i = 0; i < passengers.size(); i++) {
                oos.writeObject(passengers.get(i));
                oos.flush();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.getStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
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

//add the reservations.txt
//server reads in reservations.txt and returns it to the client, client updates the text in the gui with the new passenger
//printwriter and printreader

//reservationserver
//edit serveClients

//clienthandler
//edit run method

//putting run method in server?? main method???

//swingworker???/

//Every time a client connects - they should be put on their own thread.
//Each time that a GUI action is made, it should be made on the EDT.
//Every time that the client requests for specific information, it will send a word or phrase, representative of what
// it wants. For example, PASS can be sent to the server to indicate that the server needs to send back the passenger
// list. COU could be sent for passenger count, and CAP could be sent for passenger capacity. You'll have to think
// about all the different keywords you'll have to implement for this project. But, sending these requests, which
// are called queries, should each be made on its own thread, and sent to the server, and wait until they receive data
// back from the server before changing any variables or anything in the client-side.

//only send strings and passenger objects to the server, but can send ints/chars/etc. as strings and parse them on the server side

//private Delta delta = new Delta();
//    private Southwest southwest = new Southwest();
//    private Alaska alaska = new Alaska();
//    private Gate gate = new Gate();
//    private Passenger passenger = new Passenger();
//    private BoardingPass deltaBoardingPass = new BoardingPass(passenger, "Delta");
//    private BoardingPass alaskaBoardingPass = new BoardingPass(passenger, "Alaska");
//    private BoardingPass southwestBoardingPass = new BoardingPass(passenger, "Southwest");
//    ArrayList<Passenger> alaskaPassengers = new ArrayList<>();
//    ArrayList<Passenger> deltaPassengers = new ArrayList<>();
//    ArrayList<Passenger> southwestPassengers = new ArrayList<>();


//all read and writing here???

//make sure to update counter in the reservations.txt file
