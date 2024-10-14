import java.awt.*;
import java.awt.event.*;
import java.math.BigInteger;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;


public class SignUpWindow extends StartingWindow implements ActionListener {

    private static JPanel panelSign;
    private static JDialog SignUpDialog;
    private static JLabel FirstName, LastName, Address, AddressNumber, Phone, Username, Password, ConfirmPassword, Email;
    private static JTextField textFirstName, textLastName, textAddress, textAddressNumber, textPhone, textUsername, textEmail;
    private static JPasswordField textPassword, textConfirmPassword;
    private static JToggleButton buttonPassword, buttonConfirmPassword;
    private static JButton buttonSign;
    private static int flag = 0;
    private static int customerID = 0;
    
    //Constructor
    SignUpWindow() {

        //Panel GUI
        panelSign = new JPanel();
        panelSign.setOpaque(true);
        panelSign.setBackground(c);
        panelSign.setLayout(null);

        //New window dialog (frame)
        SignUpDialog = new JDialog(frame, "Sign Up", true);
        SignUpDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        SignUpDialog.setLocation(x + 300, y + 50);
        SignUpDialog.setResizable(false);
        SignUpDialog.setSize(400, 600);
        SignUpDialog.add(panelSign);

        //Username GUI
        Username = new JLabel("Username:");
        Username.setBounds(10, 10, 120, 30);
        panelSign.add(Username);
        textUsername = new JTextField();
        textUsername.setBounds(100, 10, 200, 30);
        textUsername.setHorizontalAlignment(JTextField.CENTER);
        panelSign.add(textUsername);

        //First name GUI
        FirstName = new JLabel("First Name:");
        FirstName.setBounds(10, 60, 120, 30);
        panelSign.add(FirstName);
        textFirstName = new JTextField();
        textFirstName.setBounds(100, 60, 200, 30);
        textFirstName.setHorizontalAlignment(JTextField.CENTER);
        panelSign.add(textFirstName);

        //Last name GUI
        LastName = new JLabel("Last Name:");
        LastName.setBounds(10, 110, 120, 30);
        panelSign.add(LastName);
        textLastName = new JTextField();
        textLastName.setBounds(100, 110, 200, 30);
        textLastName.setHorizontalAlignment(JTextField.CENTER);
        panelSign.add(textLastName);
        
        //Address GUI
        Address = new JLabel("Address:");
        Address.setBounds(10, 160, 120, 30);
        panelSign.add(Address);
        textAddress = new JTextField();
        textAddress.setBounds(100, 160, 200, 30);
        textAddress.setHorizontalAlignment(JTextField.CENTER);
        panelSign.add(textAddress);
        
        //Address number GUI
        AddressNumber = new JLabel("Address No:");
        AddressNumber.setBounds(10, 210, 120, 30);
        panelSign.add(AddressNumber);
        textAddressNumber = new JTextField();
        textAddressNumber.setBounds(100, 210, 200, 30);
        textAddressNumber.setHorizontalAlignment(JTextField.CENTER);
        panelSign.add(textAddressNumber);
        
        //Phone GUI
        Phone = new JLabel("Phone:");
        Phone.setBounds(10, 260, 120, 30);
        panelSign.add(Phone);
        textPhone = new JTextField();
        textPhone.setBounds(100, 260, 200, 30);
        textPhone.setHorizontalAlignment(JTextField.CENTER);
        panelSign.add(textPhone);
        
        //Email GUI
        Email = new JLabel("Email:");
        Email.setBounds(10, 310, 120, 30);
        panelSign.add(Email);
        textEmail = new JTextField();
        textEmail.setBounds(100, 310, 200, 30);
        textEmail.setHorizontalAlignment(JTextField.CENTER);
        panelSign.add(textEmail);
        
        //Password GUI
        Password = new JLabel("Password:");
        Password.setBounds(10, 360, 120, 30);
        panelSign.add(Password);
        textPassword = new JPasswordField();
        textPassword.setBounds(100, 360, 200, 30);
        textPassword.setHorizontalAlignment(JPasswordField.CENTER);
        panelSign.add(textPassword);

        //Confirm password GUI
        ConfirmPassword = new JLabel("Confirm Pass:");
        ConfirmPassword.setBounds(10, 410, 120, 30);
        panelSign.add(ConfirmPassword);
        textConfirmPassword = new JPasswordField();
        textConfirmPassword.setBounds(100, 410, 200, 30);
        textConfirmPassword.setHorizontalAlignment(JPasswordField.CENTER);
        panelSign.add(textConfirmPassword);

        //Show - Hide password button GUI
        buttonPassword = new JToggleButton("Show");
        buttonPassword.setBounds(295, 363, 60, 25);
        panelSign.add(buttonPassword);

        buttonPassword.addActionListener(new ActionListener() {
            private boolean passwordVisible = false;
            public void actionPerformed(ActionEvent e) {
                passwordVisible = !passwordVisible;
                if (passwordVisible)
                    textPassword.setEchoChar((char) 0);
                else
                    textPassword.setEchoChar('●');
            }
        });
        buttonPassword.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if (buttonPassword.isSelected())
                    buttonPassword.setText("Hide");
                else
                    buttonPassword.setText("Show");
            }
        });

        //Show - Hide password button GUI
        buttonConfirmPassword = new JToggleButton("Show");
        buttonConfirmPassword.setBounds(295, 413, 60, 25);
        panelSign.add(buttonConfirmPassword);

        buttonConfirmPassword.addActionListener(new ActionListener() {
            private boolean confirmpasswordVisible = false;
            public void actionPerformed(ActionEvent e) {
                confirmpasswordVisible = !confirmpasswordVisible;
                if (confirmpasswordVisible)
                    textConfirmPassword.setEchoChar((char) 0);
                else
                    textConfirmPassword.setEchoChar('●');
            }
        });
        buttonConfirmPassword.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if (buttonConfirmPassword.isSelected())
                    buttonConfirmPassword.setText("Hide");
                else
                    buttonConfirmPassword.setText("Show");
            }
        });

        //Sign Up button GUI
        buttonSign = new JButton("Sign Up");
        buttonSign.setBounds(75, 480, 250, 40);
        buttonSign.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonSign.addActionListener(this);
        panelSign.add(buttonSign);

        UIManager.put("OptionPane.background", c);
        UIManager.put("Panel.background", c);

        //Dialog window visibility
        SignUpDialog.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == buttonSign) {
            if (SignUpTextIsValid()) {
                SignUpDialog.dispose();
                frame.dispose();
                @SuppressWarnings("unused")
                FoodWindow newWindow = new FoodWindow(customerID);
            } else {
                if (flag == 0)
                    JOptionPane.showMessageDialog(null, "You must fill all the fields to sign up!", "ERROR", JOptionPane.WARNING_MESSAGE);
                else if (flag == 1)
                    JOptionPane.showMessageDialog(null, "Password must contain at least 8 characters!", "ERROR", JOptionPane.WARNING_MESSAGE);
                else if (flag == 2)
                    JOptionPane.showMessageDialog(null, "Username mush contain characters and numbers only!", "ERROR", JOptionPane.WARNING_MESSAGE);
                else if (flag == 3)
                    JOptionPane.showMessageDialog(null, "First name must contain characters only!", "ERROR", JOptionPane.WARNING_MESSAGE);
                else if (flag == 4)
                    JOptionPane.showMessageDialog(null, "Last name must contain characters only!", "ERROR", JOptionPane.WARNING_MESSAGE);
                else if (flag == 5)
                    JOptionPane.showMessageDialog(null, "Address must contain characters only!", "ERROR", JOptionPane.WARNING_MESSAGE);
                else if (flag == 6)
                    JOptionPane.showMessageDialog(null, "Address number must contain numbers only (at least one, no more than three)!", "ERROR", JOptionPane.WARNING_MESSAGE);
                else if (flag == 7)
                    JOptionPane.showMessageDialog(null, "Phone must contain numbers only (starting with 69, following it with 8 numbers)!", "ERROR", JOptionPane.WARNING_MESSAGE);
                else if (flag == 8)
                    JOptionPane.showMessageDialog(null, "Email must contain characters and numbers only!", "ERROR", JOptionPane.WARNING_MESSAGE);
                else if (flag == 9)
                    JOptionPane.showMessageDialog(null, "Password must contain at least: an upper and a lower case letter, a number and a special character!", "ERROR", JOptionPane.WARNING_MESSAGE);
                else if (flag == 10)
                    JOptionPane.showMessageDialog(null, "Password must be identical with the previous field!", "ERROR", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public boolean SignUpTextIsValid() {

        boolean isValid = true;
        flag = 0;

        //changing border color to all the text borders to default
        textUsername.setBorder(new LineBorder(Color.WHITE));
        textFirstName.setBorder(new LineBorder(Color.WHITE));
        textLastName.setBorder(new LineBorder(Color.WHITE));
        textAddress.setBorder(new LineBorder(Color.WHITE));
        textAddressNumber.setBorder(new LineBorder(Color.WHITE));
        textPhone.setBorder(new LineBorder(Color.WHITE));
        textEmail.setBorder(new LineBorder(Color.WHITE));
        textPassword.setBorder(new LineBorder(Color.WHITE));
        textConfirmPassword.setBorder(new LineBorder(Color.WHITE));

        //checking username text field
        if (textUsername.getText().isEmpty()) {
            isValid = false;
            textUsername.setBorder(new LineBorder(Color.RED));
        } else if (!(textUsername.getText().matches("[a-zA-Z0-9]+"))) {
            isValid = false;
            flag = 2;
            textUsername.setBorder(new LineBorder(Color.RED));
        }

        //checking firstname text field
        if (textFirstName.getText().isEmpty()) {
            isValid = false;
            textFirstName.setBorder(new LineBorder(Color.RED));
        } else if (!(textFirstName.getText().matches("[a-zA-Z]+"))) {
            isValid = false;
            flag = 3;
            textFirstName.setBorder(new LineBorder(Color.RED));
        }

        //checking lastname text field
        if (textLastName.getText().isEmpty()) {
            isValid = false;
            textLastName.setBorder(new LineBorder(Color.RED));
        } else if (!(textLastName.getText().matches("[a-zA-Z]+"))) {
            isValid = false;
            flag = 4;
            textLastName.setBorder(new LineBorder(Color.RED));
        }

        //checking address text field
        if (textAddress.getText().isEmpty()) {
            isValid = false;
            textAddress.setBorder(new LineBorder(Color.RED));
        } else if (!(textAddress.getText().matches("[a-zA-Z]+"))) {
            isValid = false;
            flag = 5;
            textAddress.setBorder(new LineBorder(Color.RED));
        }

        //checking addressnumber text field
        if (textAddressNumber.getText().isEmpty()) {
            isValid = false;
            textAddressNumber.setBorder(new LineBorder(Color.RED));
        } else if (!(textAddressNumber.getText().matches("[0-9]{1,3}"))) {
            isValid = false;
            flag = 6;
            textAddressNumber.setBorder(new LineBorder(Color.RED));
        }

        //checking phone text field
        if (textPhone.getText().isEmpty()) {
            isValid = false;
            textPhone.setBorder(new LineBorder(Color.RED));
        } else if (!(textPhone.getText().matches("^69[0-9]{8}$"))) {
            isValid = false;
            flag = 7;
            textPhone.setBorder(new LineBorder(Color.RED));
        }

        //checking email text field
        if (textEmail.getText().isEmpty()) {
            isValid = false;
            textEmail.setBorder(new LineBorder(Color.RED));
        } else if (!(textEmail.getText().matches("[a-zA-Z0-9]+@[a-z]+.[a-z]{2,4}"))) {
            isValid = false;
            flag = 8;
            textEmail.setBorder(new LineBorder(Color.RED));
        }

        //checking password and confirmpassword text fields
        if (isValid) {
            char[] passwordChars = textPassword.getPassword();
            char[] passwordCharsConfirm = textConfirmPassword.getPassword();

            String password = new String(passwordChars);
            String passwordConfirm = new String(passwordCharsConfirm);

            if (textPassword.getPassword().length < 8) {
                isValid = false;
                flag = 1;
                textPassword.setBorder(new LineBorder(Color.RED));
            } else if (!(password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=.*[a-zA-Z\\d@#$%^&+=!]).{8,}$"))) {
                isValid = false;
                flag = 9;
                textPassword.setBorder(new LineBorder(Color.RED));
            }

            if (textConfirmPassword.getPassword().length < 8) {
                isValid = false;
                flag = 1;
                textConfirmPassword.setBorder(new LineBorder(Color.RED));
            } else if (!(passwordConfirm.equals(password))) {
                isValid = false;
                flag = 10;
                textConfirmPassword.setBorder(new LineBorder(Color.RED));
            }
        }
        
        if (isValid)
            addToDatabase(textUsername.getText(), textFirstName.getText(), textLastName.getText(), textAddress.getText(), textAddressNumber.getText(), textPhone.getText(), textEmail.getText(), textPassword.getPassword());

        return isValid;
    }

    public void addToDatabase(String username, String firstname, String lastname, String address, String addressno, String phone, String email, char[] password) {
        try {
            int addressnoFinal = Integer.parseInt(addressno);
            BigInteger phoneFinal = new BigInteger(phone);
            String passwordFinal = new String(password);

            String query = "INSERT INTO Customers (username, firstname, lastname, address, addressno, phone, email, password)" + "VALUES ('" + username + "'" + ", " + "'" + firstname  + "'" + ", " + "'" + lastname + "'" + ", " + "'" + address + "'" + ", " + addressnoFinal + ", " + phoneFinal + ", " + "'" + email + "'" + ", " + "'" + passwordFinal + "')";
            DB_Connection.st = DB_Connection.con.createStatement();
            DB_Connection.st.executeUpdate(query);
            DB_Connection.st.close();

            query = "SELECT id FROM Customers WHERE username = '" + username + "'" + " AND password = '" + passwordFinal + "'";
            DB_Connection.st = DB_Connection.con.createStatement();
            DB_Connection.rs = DB_Connection.st.executeQuery(query);
            DB_Connection.rs.next();
            customerID = DB_Connection.rs.getInt("id");

            if (customerID <= 0)
                System.exit(0);

        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }
    }
}
