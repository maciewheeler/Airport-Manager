import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;

/**
 * A client used to connect to a ReservationServer instance.
 * <p>
 * Used code from homework 11's CountdownClient as a base of our code.
 * <p>
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

        hostname = JOptionPane.showInputDialog(null, "What is the hostname you'd" +
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

                    JFrame frame = new JFrame("Purdue University Flight Reservation System");
                    frame.setSize(600,600);
                    frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    frame.setResizable(true);
                    frame.setLocationRelativeTo(null);

                    //first frame
                    JPanel panel1 = new JPanel();
                    JLabel openingText = new JLabel("Welcome to the Purdue University Airline Reservation" +
                            "\n" + "Management System!");
                    openingText.setFont(new Font("Courier", Font.BOLD, 24));
                    panel1.add(openingText, BorderLayout.CENTER);

                    JPanel panel2 = new JPanel();
                    JLabel imageLabel = new JLabel();
                    ImageIcon purdueImage = new ImageIcon(new ImageIcon("logo.jpg").getImage().
                            getScaledInstance(221, 270, Image.SCALE_DEFAULT));
                    imageLabel.setIcon(purdueImage);
                    panel2.add(imageLabel);

                    JPanel panel3 = new JPanel();
                    JButton exitButton = new JButton("Exit");
                    panel3.add(exitButton);
                    JButton bookAFlightButton = new JButton("Book a Flight");
                    panel3.add(bookAFlightButton);
                    frame.add(panel1, BorderLayout.NORTH);
                    frame.add(panel2, BorderLayout.CENTER);
                    frame.add(panel3, BorderLayout.SOUTH);
                    //frame.pack();
                    frame.setVisible(true);

                    exitButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            //eighth frame
                            JOptionPane.showMessageDialog(null, "Thank you for using the " +
                                            "Purdue University Airline Management System!", "Thank You!",
                                    JOptionPane.PLAIN_MESSAGE);
                            frame.dispose();
                        }
                    }); //exit button

                    bookAFlightButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            frame.setVisible(false);
                            panel1.removeAll();
                            panel2.removeAll();
                            panel3.removeAll();

                            //second frame
                            JLabel secondText = new JLabel("Do you want to book a flight today?");
                            secondText.setFont(new Font("Courier", Font.BOLD, 24));
                            panel1.add(secondText);

                            JButton yesIWantToBookAFlightButton = new JButton("Yes, I want to book a flight.");
                            panel3.add(exitButton);
                            panel3.add(yesIWantToBookAFlightButton);

//                            frame.add(panel1, BorderLayout.NORTH);
//                            frame.add(panel2, BorderLayout.CENTER);
//                            frame.add(panel3, BorderLayout.SOUTH);
//                            frame.pack();
                            frame.setVisible(true);

                            yesIWantToBookAFlightButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    frame.setVisible(false);
                                    panel1.removeAll();
                                    panel2.removeAll();
                                    panel3.removeAll();

                                    //third frame (changes based on what is clicked in drop down box??? - panel 2)
                                    frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

                                    JLabel thirdText = new JLabel("Choose a flight from the drop down menu.");
                                    thirdText.setFont(new Font("Courier", Font.BOLD, 24));
                                    panel1.add(thirdText);

                                    JComboBox<String> airlineNames = new JComboBox<>();
                                    airlineNames.addItem("Delta");
                                    airlineNames.addItem("Southwest");
                                    airlineNames.addItem("Alaska");
                                    panel1.add(airlineNames);

                                    JLabel message = new JLabel();
                                    message.setText(Delta.getAirlineMessage());
                                    panel2.add(message);

                                    airlineNames.addItemListener(listener -> {
                                        String choice;
                                        JComboBox getSelection = (JComboBox) listener.getSource();
                                        choice = (String) getSelection.getSelectedItem();
                                        if (choice.equals("Delta")) {
                                            message.setText(Delta.getAirlineMessage());
                                            panel2.add(message);
                                        } else if (choice.equals("Southwest")) {
                                            message.setText(Southwest.getAirlineMessage());
                                            panel2.add(message);
                                        } else if (choice.equals("Alaska")) {
                                            message.setText(Alaska.getAirlineMessage());
                                            panel2.add(message);
                                        }
                                    });

                                    JButton chooseThisFlightButton = new JButton("Choose this flight");
                                    panel3.add(exitButton);
                                    panel3.add(chooseThisFlightButton);

//                                    frame.add(panel1);
//                                    frame.add(panel2);
//                                    frame.add(panel3);
//                                    frame.pack();
                                    frame.setVisible(true);

                                    chooseThisFlightButton.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            frame.setVisible(false);
                                            panel1.removeAll();
                                            panel2.removeAll();
                                            panel3.removeAll();

                                            //fourth frame
                                            frame.setLayout(new BorderLayout());
                                            String selectedAirline = (String) airlineNames.getSelectedItem();
                                            JLabel fourthText = new JLabel("Are you sure that you want to" +
                                                    " book a flight on " + selectedAirline + " Airlines?");
                                            fourthText.setFont(new Font("Courier", Font.BOLD, 24));
                                            panel1.add(fourthText);

                                            JButton noIWantADifferentFlightButton = new JButton("No, I want a" +
                                                    " different flight.");
                                            JButton yesIWantthisFlightButton = new JButton("Yes, I want this" +
                                                    " flight.");
                                            panel3.add(exitButton);
                                            panel3.add(noIWantADifferentFlightButton);
                                            panel3.add(yesIWantthisFlightButton);

//                                            frame.add(panel1, BorderLayout.NORTH);
//                                            frame.add(panel3, BorderLayout.SOUTH);
//                                            frame.pack();
                                            frame.setVisible(true);

                                            yesIWantthisFlightButton.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    frame.setVisible(false);
                                                    panel1.removeAll();
                                                    panel3.removeAll();

                                                    //fifth frame
                                                    frame.setLayout(new BoxLayout(frame.getContentPane(),
                                                            BoxLayout.Y_AXIS));

                                                    JPanel panel4 = new JPanel();
                                                    JPanel panel5 = new JPanel();

                                                    JLabel fifthText = new JLabel("Please input your" +
                                                            " information below.");
                                                    fifthText.setFont(new Font("Courier", Font.BOLD, 24));
                                                    panel1.add(fifthText);

                                                    JLabel firstNameText = new JLabel("What is your first name?");
                                                    panel2.add(firstNameText);
                                                    JTextField firstNameTextField = new JTextField(10);
                                                    panel2.add(firstNameTextField);


                                                    JLabel lastNameText = new JLabel("What is your last name?");
                                                    panel3.add(lastNameText);
                                                    JTextField lastNameTextField = new JTextField(10);
                                                    panel3.add(lastNameTextField);


                                                    JLabel ageText = new JLabel("What is your age?");
                                                    panel4.add(ageText);
                                                    JTextField ageTextField = new JTextField(10);
                                                    panel4.add(ageTextField);

                                                    JButton nextButton = new JButton("Next");
                                                    panel5.add(exitButton);
                                                    panel5.add(nextButton);

//                                                    frame.add(panel1);
//                                                    frame.add(panel2);
//                                                    frame.add(panel3);
                                                    frame.add(panel4);
                                                    frame.add(panel5);
                                                   // frame.pack();
                                                    frame.setVisible(true);
                                                    //put words and textbox on same panel!! so we only use 3 or maybe 4



                                                    nextButton.addActionListener(new ActionListener() {
                                                        @Override
                                                        public void actionPerformed(ActionEvent e) {
                                                            String firstName  = firstNameTextField.getText();
                                                            String lastName = lastNameTextField.getText();
                                                            String age = ageTextField.getText();
                                                            //sixth frame
                                                            String confirmMessage = "Are all the details you" +
                                                                    " entered correct?" + "\n" +
                                                                    "The passenger's name is " + firstName + " " +
                                                                    lastName + " and their age is " +
                                                                    age + "." + "\n" +
                                                                    "If all the information shown is correct, select" +
                                                                    " the Yes" + "\n" +
                                                                    "button below, otherwise, select the No button.";
                                                            int confirm = JOptionPane.showConfirmDialog
                                                                    (null, confirmMessage,
                                                                            "Confirm Info", JOptionPane.YES_NO_OPTION);

                                                            if (confirm == JOptionPane.YES_OPTION) {
                                                                frame.setVisible(false);
                                                                panel1.removeAll();
                                                                panel2.removeAll();
                                                                panel3.removeAll();
                                                                panel4.removeAll();
                                                                panel5.removeAll();
                                                                frame.remove(panel5);

                                                                Passenger passenger = new Passenger();
                                                                passenger.setAge(Integer.valueOf(age));
                                                                passenger.setFirstName(firstName);
                                                                passenger.setLastName(lastName);
                                                                //seventh frame
                                                                BoardingPass boardingPass = null;
                                                                String selectedGate = "";

                                                                if (selectedAirline.equals("Delta")) {
                                                                    selectedGate = Delta.getDeltaGateString();
                                                                    boardingPass = new BoardingPass(passenger, "Delta");
                                                                } else if (selectedAirline.equals("Southwest")) {
                                                                    selectedGate = Southwest.getSouthwestGateToString();
                                                                    boardingPass = new BoardingPass(passenger,
                                                                            "Southwest");
                                                                } else if (selectedAirline.equals("Alaska")) {
                                                                    selectedGate = Alaska.getAlaskaGateToString();
                                                                    boardingPass = new BoardingPass(passenger, "Alaska");
                                                                }
                                                                JLabel seventhText = new JLabel("Flight data" +
                                                                        " displaying for " + selectedAirline +
                                                                        " Airlines" +
                                                                        "\n" + "Enjoy your flight!" + "\n" +
                                                                        "Flight is now boarding at Gate "
                                                                        + selectedGate);
                                                                seventhText.setFont(new Font("Courier",
                                                                        Font.BOLD, 24));
                                                                panel1.add(seventhText);
                                                                //try changing gates to final inside each airline class

                                                                JScrollPane scrollPane1 = new JScrollPane(panel2);
                                                                //somehow get the arraylist in the scroll pane??
                                                                //somehow get number of passengers (on scrollpane???)

                                                                JLabel printBP = new JLabel(boardingPass.
                                                                        writeBoardingPass());
                                                                panel3.add(printBP);
                                                                //fix gate on boarding pass??


                                                                JButton refreshFlightStatusButton = new JButton
                                                                        ("Refresh Flight Status");
                                                                panel4.add(exitButton);
                                                                panel4.add(refreshFlightStatusButton);

//                                                                frame.add(panel1);
//                                                                frame.add(panel2);
//                                                                frame.add(panel3);
//                                                                frame.add(panel4);
//                                                                frame.pack();
                                                                frame.setVisible(true);
                                                            } else if (confirm == JOptionPane.NO_OPTION) {
                                                                //back to fifth frame
                                                            }
                                                        }
                                                    }); // next button
                                                }
                                            }); // yes i want this flight button

                                            noIWantADifferentFlightButton.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    frame.setVisible(false);
                                                    panel1.removeAll();
                                                    panel3.removeAll();

                                                    //back to third frame

                                                }
                                            }); // no i want a different flight button
                                        }
                                    }); //choose this flight button
                                }
                            }); // yes i want to book a flight button
                        }
                    }); //book a flight button
                }
            }); //EDT
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
