import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

/**
 * A client used to connect to a ReservationServer instance.
 *
 * Used code from homework 11's CountdownClient as a base of our code.
 *
 * Used https://stackoverflow.com/questions/22847148/java-text-formatting-bold for formatting text.
 *
 * @author Macie Wheeler and Keya Mahtani
 * @version November 18, 2019
 */

public final class ReservationClient {

    /**
     * Determines whether or not the specified String is parsable as a non-negative int.
     *
     * @param string the String to be used in the operation
     * @return true, if the specified String is parsable as a non-negative int and false otherwise
     */
    private static boolean isParsable(String string) {
        return string.chars()
                .mapToObj(Character::isDigit)
                .reduce(Boolean::logicalAnd)
                .orElse(Boolean.FALSE);
    } //isParsable

    /**
     * Connects to a ReservationServer instance and sends requests.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String hostname;
        String portString;
        int port;
        Socket socket;
        BufferedWriter socketWriter = null;
        BufferedReader socketReader = null;

        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        hostname =  JOptionPane.showInputDialog(null, "What is the hostname you'd" +
                    " like to connect to?", "Hostname?", JOptionPane.QUESTION_MESSAGE);

        if (hostname == null) {
            JOptionPane.showMessageDialog(null, "Thank you for using the Purdue" +
                    " University Airline Management System!", "Thank You!", JOptionPane.PLAIN_MESSAGE);

            return;
        }

        portString = JOptionPane.showInputDialog(null, "What is the port you'd" +
                    " like to connect to?", "Port?", JOptionPane.QUESTION_MESSAGE);

        if (portString == null) {
            JOptionPane.showMessageDialog(null, "Thank you for using the Purdue University" +
                    " Airline Management System!", "Thank You!", JOptionPane.PLAIN_MESSAGE);

            return;
        }

        while (!isParsable(portString)) {
            JOptionPane.showMessageDialog(null, "Error: The specified port is invalid!",
                    "Error", JOptionPane.ERROR_MESSAGE);

            portString = JOptionPane.showInputDialog(null, "What is the port you'd" +
                    " like to connect to?", "Port?", JOptionPane.QUESTION_MESSAGE);

            if (portString == null) {
                JOptionPane.showMessageDialog(null, "Thank you for using the Purdue" +
                        " University Airline Management System!", "Thank You!", JOptionPane.PLAIN_MESSAGE);

                return;
            }
        }

        port = Integer.parseInt(portString);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Purdue University Flight Reservation System");
                frame.setSize(800, 800);
                frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                JPanel panel1 = new JPanel(new BorderLayout());
                JTextField openingText = new JTextField("Welcome to the Purdue University Airline Reservation" +
                        " Management System!");
                openingText.setFont(new Font("Courier", Font.BOLD, 20));
                panel1.add(openingText);
                frame.add(panel1, BorderLayout.NORTH);
                frame.setVisible(true);
            }
        });

    } //main

//    public static void createPanels() {
//        JPanel panel1 = new JPanel();
//        panel1.setLayout(new BorderLayout());
//
//        JPanel panel2 = new JPanel();
//        panel2.setLayout(new BorderLayout());
//
//        JPanel panel3 = new JPanel();
//        panel3.setLayout(new BorderLayout());
//    } //createPanels
//
//    public static void createButtons() {
//        JButton button1 = new JButton();
//
//    } //createButtons
//
//    public static void createFrame() {
//        JFrame frame = new JFrame("Purdue University Flight Reservation System");
//        frame.setSize(500, 500);
//        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
//        frame.setVisible(true);
//    } //createFrame
}
class ResponseListener implements ActionListener {

    public void actionPerformed(ActionEvent e) {

    }
}
