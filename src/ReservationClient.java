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
 * Used various stack overflow posts for creating complex GUIs.
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

        try {
            socket = new Socket(hostname, port);
            socketWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    boolean run = true;

                    while (run) {
                        JFrame frame = new JFrame("Purdue University Flight Reservation System");
                        frame.setSize(600, 600);
                        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                        frame.setResizable(true);
                        frame.setLocationRelativeTo(null);

                        //first frame
                        JPanel panel1 = new JPanel();
                        JLabel openingText = new JLabel("Welcome to the Purdue University Airline Reservation" +
                                "\n" + "Management System!");
                        openingText.setFont(new Font("Courier", Font.BOLD, 24));
                        panel1.add(openingText, BorderLayout.CENTER); //maybe use this center thing???

                        JPanel panel2 = new JPanel();
                        JLabel imageLabel = new JLabel();
                        ImageIcon purdueImage = new ImageIcon(new ImageIcon("logo.jpg").getImage().
                                getScaledInstance(221, 270, Image.SCALE_DEFAULT));
                        imageLabel.setIcon(purdueImage);
                        panel2.add(imageLabel);

                        JPanel panel3 = new JPanel();
                        JButton exitButton = new JButton("Exit");
                        exitButton.addActionListener(e -> {
                            JOptionPane.showMessageDialog(null, "Thank you for using the " +
                                            "Purdue University Airline Management System!", "Thank You!",
                                    JOptionPane.PLAIN_MESSAGE);
//                            run = false;
//                            break;

                        });
                        JButton bookAFlightButton = new JButton("Book a Flight");
                        bookAFlightButton.addActionListener(e -> {

                        });
                        panel3.add(exitButton);
                        panel3.add(bookAFlightButton);

                        frame.add(panel1, BorderLayout.NORTH);
                        frame.add(panel2, BorderLayout.CENTER);
                        frame.add(panel3, BorderLayout.SOUTH);
                        frame.pack();
                        frame.setVisible(true); //do we need to make unvisible at some point??

                        //second frame
                        JLabel secondText = new JLabel("Do you want to book a flight today?");
                        secondText.setFont(new Font("Courier", Font.BOLD, 24));
                        panel1.add(secondText);

                        JButton yesIWantToBookAFlightButton = new JButton("Yes, I want to book a flight.");
                        panel3.add(exitButton);
                        panel3.add(yesIWantToBookAFlightButton);

                        frame.add(panel1, BorderLayout.NORTH);
                        frame.add(panel3, BorderLayout.SOUTH);
                        frame.pack();
                        frame.setVisible(true);

                        //third frame (changes based on what is clicked in drop down box??? - panel 2)
                        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

                        JLabel thirdText = new JLabel("Choose a flight from the drop down menu.");
                        thirdText.setFont(new Font("Courier", Font.BOLD, 24));
                        panel1.add(thirdText);

                        JComboBox<String> airlineNames = new JComboBox<>();
                        airlineNames.addItem("Delta");
                        airlineNames.addItem("Southwest");
                        airlineNames.addItem("Alaska");
                        panel2.add(airlineNames);

                        //panel 3 here needs to have the messages based on what is clicked

                        JPanel panel4 = new JPanel();
                        JButton chooseThisFlightButton = new JButton("Choose this flight");
                        panel4.add(exitButton);
                        panel4.add(chooseThisFlightButton);

                        frame.add(panel1);
                        frame.add(panel2);
                        frame.add(panel3);
                        frame.add(panel4);
                        frame.pack();
                        frame.setVisible(true);

                        //fourth frame
                        frame.setLayout(new BorderLayout());

                        JLabel fourthText = new JLabel("Are you sure that you want to book a flight on (selected airline)?"); //need to add name of selected airline somehow??
                        fourthText.setFont(new Font("Courier", Font.BOLD, 24));
                        panel1.add(fourthText);

                        JButton noIWantADifferentFlightButton = new JButton("No, I want a different flight.");
                        JButton yesIWantthisFlightButton = new JButton("Yes, I want this flight.");
                        panel3.add(exitButton);
                        panel3.add(noIWantADifferentFlightButton);
                        panel3.add(yesIWantthisFlightButton);

                        frame.add(panel1, BorderLayout.NORTH);
                        frame.add(panel3, BorderLayout.SOUTH);
                        frame.pack();
                        frame.setVisible(true);

                        //fifth frame
                        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

                        JPanel panel5 = new JPanel();
                        JPanel panel6 = new JPanel();
                        JPanel panel7 = new JPanel();
                        JPanel panel8 = new JPanel();

                        JLabel fifthText = new JLabel("Please input your information below.");
                        fifthText.setFont(new Font("Courier", Font.BOLD, 24));
                        panel1.add(fifthText);

                        JLabel firstNameText = new JLabel("What is your first name?");
                        panel2.add(firstNameText);
                        JTextArea firstNameTextArea = new JTextArea();
                        panel3.add(firstNameTextArea);

                        JLabel lastNameText = new JLabel("What is your last name?");
                        panel4.add(lastNameText);
                        JTextArea lastNameTextArea = new JTextArea();
                        panel5.add(lastNameTextArea);

                        JLabel ageText = new JLabel("What is your age?");
                        panel6.add(ageText);
                        JTextArea ageTextArea = new JTextArea();
                        panel7.add(ageTextArea);

                        JButton nextButton = new JButton("Next");
                        panel8.add(exitButton);
                        panel8.add(nextButton);

                        frame.add(panel1);
                        frame.add(panel2);
                        frame.add(panel3);
                        frame.add(panel4);
                        frame.add(panel5);
                        frame.add(panel6);
                        frame.add(panel7);
                        frame.add(panel8);
                        frame.pack();
                        frame.setVisible(true);

                        //sixth frame
                        String confirmMessage = "Are all the details you entered correct?" + "\n" +
                                "The passenger's name is (passenger first name and last name) and their age is" +
                                " (passenger age)." + "\n" +
                                "If all the information shown is correct, select the Yes" + "\n" +
                                "button below, otherwise, select the No button.";
                        int confirm = JOptionPane.showConfirmDialog(null, confirmMessage,
                                "Confirm Info", JOptionPane.YES_NO_OPTION);

                        //seventh frame
                        JLabel seventhText = new JLabel("Flight data displaying for (airline name)" + "\n" +
                                "Enjoy your flight!" + "\n" +
                                "Flight is now boarding at Gate (gate number)");
                        seventhText.setFont(new Font("Courier", Font.BOLD, 24));
                        panel1.add(seventhText);

                        //somehow get number of passengers (on scrollpane???)
                        
                        JScrollPane scrollPane1 = new JScrollPane(panel2);
                        //somehow get the arraylist in the scroll pane??
                        
                        //somehow get the boarding pass on panel3??
                        
                        JButton refreshFlightStatusButton = new JButton("Refresh Flight Status");
                        panel4.add(exitButton);
                        panel4.add(refreshFlightStatusButton);
                        
                        frame.add(panel1);
                        frame.add(panel2);
                        frame.add(panel3);
                        frame.add(panel4);
                        
                        //eighth frame
                        JOptionPane.showMessageDialog(null, "Thank you for using the " +
                                "Purdue University Airline Management System!", "Thank You!",
                                JOptionPane.PLAIN_MESSAGE);
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    } //main
}
class ResponseListener implements ActionListener {

    public void actionPerformed(ActionEvent e) {

    }
}


//panel.remove()

//how to store whether a button was pressed or not??

//make frame pop up on center of screen
//for first frame how to center text???
//figure out how to make background all white
//how to make sure jframe is always the same size each time??

//why is new Runnable gray??

//what is the responselistener class for??
