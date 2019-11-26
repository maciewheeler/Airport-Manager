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
                    frame.setSize(800,600);
                    frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    frame.setResizable(true);
                    frame.setLocationRelativeTo(null);

                    JPanel panel1 = new JPanel();
                    JPanel panel2 = new JPanel();
                    JPanel panel3 = new JPanel();
                    JPanel panel4 = new JPanel();
                    JPanel panel5 = new JPanel();
                    JComboBox<String> airlineNames = new JComboBox<>();
                    JButton exitButton = new JButton();
                    JButton bookAFlightButton = new JButton();
                    JButton yesIWantToBookAFlightButton = new JButton();
                    JButton chooseThisFlightButton = new JButton();
                    JLabel message = new JLabel();
                    JButton yesIWantThisFlightButton = new JButton();
                    JButton noIWantADifferentFlightButton = new JButton();
                    JButton nextButton = new JButton();
                    JTextField firstNameTextField = new JTextField();
                    JTextField lastNameTextField = new JTextField();
                    JTextField ageTextField = new JTextField();
                    String selectedAirline = "";

                    runGUI(frame, panel1, panel2, panel3, panel4, panel5, airlineNames, exitButton, bookAFlightButton,
                            yesIWantToBookAFlightButton, chooseThisFlightButton, message, yesIWantThisFlightButton,
                            noIWantADifferentFlightButton, nextButton, firstNameTextField, lastNameTextField,
                            ageTextField, selectedAirline);
                }
            }); //EDT
        } catch (IOException e) {
            e.printStackTrace();
        }
    } //main

    private static void firstFrame(JFrame frame, JComboBox<String> airlineNames, JButton chooseThisFlightButton,
                                   JButton noIWantADifferentFlightButton, JButton yesIWantThisFlightButton) {
        //first frame
        JPanel panel1 = new JPanel();
        JLabel openingText = new JLabel("<html><center>Welcome to the Purdue University" +
                " Airline Reservation" + "<br>" + "Management System!</center></html>");
        openingText.setFont(new Font("Courier", Font.BOLD, 24));
        panel1.add(openingText, BorderLayout.CENTER);

        JPanel panel2 = new JPanel();
        JLabel imageLabel = new JLabel();
        ImageIcon purdueImage = new ImageIcon(new ImageIcon("logo.jpg").getImage().
                getScaledInstance(442, 540, Image.SCALE_DEFAULT));
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
        frame.setVisible(true);

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seventhFrame(frame);
            }
        }); //exit button

        bookAFlightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                secondFrame(frame, panel1, panel2, panel3, exitButton, airlineNames, chooseThisFlightButton,
                        noIWantADifferentFlightButton, yesIWantThisFlightButton);
            }
        });
    }

    private static void secondFrame(JFrame frame, JPanel panel1, JPanel panel2, JPanel panel3, JButton exitButton,
                                    JComboBox<String> airlineNames, JButton chooseThisFlightButton,
                                    JButton noIWantADifferentFlightButton, JButton yesIWantThisFlightButton) {
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

        frame.setVisible(true);

        yesIWantToBookAFlightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                thirdFrame(frame, panel1, panel2, panel3, airlineNames, exitButton, chooseThisFlightButton,
                        noIWantADifferentFlightButton, yesIWantThisFlightButton);
            }
        });
    }

    private static void thirdFrame(JFrame frame, JPanel panel1, JPanel panel2, JPanel panel3,
        JComboBox<String> airlineNames, JButton exitButton, JButton chooseThisFlightButton,
                                   JButton noIWantADifferentFlightButton, JButton yesIWantThisFlightButton) {

        frame.setVisible(false);
        panel1.removeAll();
        panel2.removeAll();
        panel3.removeAll();

        //third frame
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        JLabel thirdText = new JLabel("Choose a flight from the drop down menu.");
        thirdText.setFont(new Font("Courier", Font.BOLD, 24));
        panel1.add(thirdText);

        airlineNames = new JComboBox<>();
        airlineNames.addItem("Delta");
        airlineNames.addItem("Southwest");
        airlineNames.addItem("Alaska");
        panel1.add(airlineNames);

        JLabel message = new JLabel();
        message.setText(Delta.getAirlineMessage());
        panel2.add(message);

        KeyStroke backslash = KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SLASH, 0, true);
        int backslashKeyCode = KeyEvent.VK_BACK_SLASH;
        setKeyBindings(frame.getContentPane(), backslashKeyCode, "openNewWindow",
        openNewWindow);

        chooseThisFlightButton = new JButton("Choose this flight");
        panel3.add(exitButton);
        panel3.add(chooseThisFlightButton);

        frame.setVisible(true);

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

        JComboBox<String> finalAirlineNames = airlineNames;
        JButton finalChooseThisFlightButton = chooseThisFlightButton;
        chooseThisFlightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fourthFrame(frame, panel1, panel2, panel3, finalAirlineNames, exitButton, finalChooseThisFlightButton,
                        noIWantADifferentFlightButton, yesIWantThisFlightButton);
            }
        });
    }

    private static void fourthFrame(JFrame frame, JPanel panel1, JPanel panel2, JPanel panel3,
                                    JComboBox<String> airlineNames, JButton exitButton, JButton chooseThisFlightButton,
                                    JButton noIWantADifferentFlightButton, JButton yesIWantThisFlightButton) {
        frame.setVisible(false);
        panel1.removeAll();
        panel2.removeAll();
        panel3.removeAll();

        //fourth frame
        frame.setLayout(new BorderLayout());
        String selectedAirline = (String) airlineNames.getSelectedItem();
        JLabel fourthText = new JLabel("<html><center>Are you sure that" +
        " you want to book a flight on " + "<br>" + selectedAirline +
        " Airlines?</center></html>");
        fourthText.setFont(new Font("Courier", Font.BOLD, 24));
        panel1.add(fourthText);

        noIWantADifferentFlightButton = new JButton("No, I want a" +
        " different flight.");
        yesIWantThisFlightButton = new JButton("Yes, I want this" +
        " flight.");
        panel3.add(exitButton);
        panel3.add(noIWantADifferentFlightButton);
        panel3.add(yesIWantThisFlightButton);

        frame.setVisible(true);

        yesIWantThisFlightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fifthFrame(frame, panel1, panel2, panel3, exitButton, selectedAirline);
            }
        });

        JButton finalNoIWantADifferentFlightButton = noIWantADifferentFlightButton;
        JButton finalYesIWantThisFlightButton = yesIWantThisFlightButton;
        noIWantADifferentFlightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                thirdFrame(frame, panel1, panel2, panel3, airlineNames, exitButton, chooseThisFlightButton,
                        finalNoIWantADifferentFlightButton, finalYesIWantThisFlightButton);
            }
        });
    }

    private static void fifthFrame(JFrame frame, JPanel panel1, JPanel panel2, JPanel panel3, JButton exitButton,
                                   String selectedAirline) {
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

        frame.add(panel4);
        frame.add(panel5);
        frame.setVisible(true);

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sixthFrame(frame, firstNameTextField, lastNameTextField, ageTextField, panel1, panel2, panel3, panel4,
                        panel5, selectedAirline, exitButton);
            }
        });
    }

    private static void sixthFrame(JFrame frame, JTextField firstNameTextField, JTextField lastNameTextField,
                                   JTextField ageTextField, JPanel panel1, JPanel panel2, JPanel panel3,
                                   JPanel panel4, JPanel panel5,
                                   String selectedAirline, JButton exitButton) {
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
        "Confirm Info",
        JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) { // yes button
        frame.setVisible(false);
        panel1.removeAll();
        panel2.removeAll();
        panel3.removeAll();
        panel4.removeAll();
        panel5.removeAll();
        frame.remove(panel5);

        Passenger passenger = new Passenger();
        passenger.setAge(Integer.parseInt(age));
        passenger.setFirstName(firstName);
        passenger.setLastName(lastName);
        //seventh frame
        BoardingPass boardingPass = null;
        String selectedGate = "";

        if (selectedAirline.equals("Delta")) {
        selectedGate = Delta.getDeltaGateString();
        boardingPass = new BoardingPass(passenger,
        "Delta");
        boardingPass.setGate(selectedGate);
        } else if (selectedAirline.equals("Southwest")) {
        selectedGate = Southwest.getSouthwestGateToString();
        boardingPass = new BoardingPass(passenger,
        "Southwest");
        boardingPass.setGate(selectedGate);
        } else if (selectedAirline.equals("Alaska")) {
        selectedGate = Alaska.getAlaskaGateToString();
        boardingPass = new BoardingPass(passenger,
        "Alaska");
        boardingPass.setGate(selectedGate);
        }

        JLabel seventhText = new JLabel("<html><center>" +
        "Flight data displaying for " +
        selectedAirline + " Airlines." +
        "<br>" + "Enjoy your flight!" + "<br>" +
        "Flight is now boarding at Gate "
        + selectedGate + "</center></html>");
        seventhText.setFont(new Font("Courier",
        Font.BOLD, 24));
        panel1.add(seventhText);

        JScrollPane scrollPane1 = new JScrollPane();
        //somehow get the arraylist in the scroll pane??
        //somehow get number of passengers (on scrollpane???)

        JLabel printBP = new JLabel(boardingPass.
        writeBoardingPass());
        panel3.add(printBP);
        JButton refreshFlightStatusButton = new JButton
        ("Refresh Flight Status");
        panel4.add(exitButton);
        panel4.add(refreshFlightStatusButton);

        frame.setVisible(true);
        } else if (confirm == JOptionPane.NO_OPTION) {
        //back to fifth frame
        frame.requestFocus();
        } // no button
    }

    private static void seventhFrame(JFrame frame) {
        //eighth frame
        JOptionPane.showMessageDialog(null, "Thank you for using the " +
                        "Purdue University Airline Management System!", "Thank You!",
                JOptionPane.PLAIN_MESSAGE);
        frame.dispose();
    }

    private static void actionListenersMethod(JFrame frame, JPanel panel1, JPanel panel2, JPanel panel3,
                                              JPanel panel4, JPanel panel5, JComboBox airlineNames, JButton exitButton,
                                              JButton bookAFlightButton, JButton yesIWantToBookAFlightButton,
                                              JButton chooseThisFlightButton, JLabel message,
                                              JButton yesIWantThisFlightButton, JButton noIWantADifferentFlightButton,
                                              JButton nextButton, JTextField firstNameTextField, JTextField
                                              lastNameTextField, JTextField ageTextField, String selectedAirline) {

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seventhFrame(frame);
            }
        }); //exit button

        bookAFlightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                secondFrame(frame, panel1, panel2, panel3, exitButton, airlineNames, chooseThisFlightButton,
                        noIWantADifferentFlightButton, yesIWantThisFlightButton);
            }
        });

        yesIWantToBookAFlightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                thirdFrame(frame, panel1, panel2, panel3, airlineNames, exitButton, chooseThisFlightButton,
                        noIWantADifferentFlightButton, yesIWantThisFlightButton);
            }
        });

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

        chooseThisFlightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fourthFrame(frame, panel1, panel2, panel3, airlineNames, exitButton, chooseThisFlightButton,
                        noIWantADifferentFlightButton, yesIWantThisFlightButton);
            }
        });


        yesIWantThisFlightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fifthFrame(frame, panel1, panel2, panel3, exitButton, selectedAirline);
            }
        });

        noIWantADifferentFlightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                thirdFrame(frame, panel1, panel2, panel3, airlineNames, exitButton, chooseThisFlightButton,
                        noIWantADifferentFlightButton, yesIWantThisFlightButton);
            }
        });

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sixthFrame(frame, firstNameTextField, lastNameTextField, ageTextField, panel1, panel2, panel3, panel4,
                        panel5, selectedAirline, exitButton);
            }
        });
    }

    private static void runGUI(JFrame frame, JPanel panel1, JPanel panel2, JPanel panel3,
                               JPanel panel4, JPanel panel5, JComboBox airlineNames, JButton exitButton,
                               JButton bookAFlightButton, JButton yesIWantToBookAFlightButton,
                               JButton chooseThisFlightButton, JLabel message,
                               JButton yesIWantThisFlightButton, JButton noIWantADifferentFlightButton,
                               JButton nextButton, JTextField firstNameTextField, JTextField
                                       lastNameTextField, JTextField ageTextField, String selectedAirline) {
        firstFrame(frame, airlineNames, chooseThisFlightButton, noIWantADifferentFlightButton,
                yesIWantThisFlightButton);
        actionListenersMethod(frame, panel1, panel2, panel3, panel4, panel5, airlineNames, exitButton
                ,bookAFlightButton, yesIWantToBookAFlightButton, chooseThisFlightButton, message,
                yesIWantThisFlightButton, noIWantADifferentFlightButton, nextButton, firstNameTextField,
                lastNameTextField, ageTextField, selectedAirline);
    }

    public static void setKeyBindings(Container comp, int keyCode, String id, ActionListener actionListener) {
        JPanel jp = (JPanel) comp;
        InputMap inMap = jp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap aMap = jp.getActionMap();
        KeyStroke ks = KeyStroke.getKeyStroke(keyCode, 0, true);
        inMap.put(ks, id);
        AbstractAction abstractAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                actionListener.actionPerformed(actionEvent);
            }
        };
        aMap.put(id, abstractAction);
    }

    public static void resetKeyBindings(KeyStroke ks, Container comp) {
        JPanel cp = ((JPanel) comp);
        ActionMap aMap = cp.getActionMap();
        InputMap inMap = cp.getInputMap();
        inMap.put(ks, null);
        aMap.put(ks, null);
    }

    public static Action openNewWindow = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            JFrame littleFrame = new JFrame();
            littleFrame.setSize(100, 100);
            littleFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            littleFrame.setResizable(true);

            JPanel panel1 = new JPanel();
            JPanel panel2 = new JPanel();
            JPanel panel3 = new JPanel();

            JLabel airlineName = new JLabel();

            JScrollPane sp = new JScrollPane();

            JButton exitButton = new JButton("Exit");
            panel3.add(exitButton);

            littleFrame.add(panel3);
            littleFrame.setVisible(true);
        }
    };
}

class ResponseListener {

}
//what is the responselistener class for??

//drop down box possibly move to another panel???

//KeyEvent.VK_Backslash
//comp = frame.getContentPane();
//id is openNewWindow

//resetKeyBindings(backslash, frame.getContentPane());
