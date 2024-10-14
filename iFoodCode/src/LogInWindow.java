import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;


public class LogInWindow extends StartingWindow implements ActionListener {
    
    //declarations
    private static JPanel panelLog;
    private static JDialog LogInDialog;
    private static JLabel Username, Password;
    private static JTextField textUsername;
    private static JPasswordField textPassword;
    private static JToggleButton buttonPassword;
    protected static JButton buttonLog;
    private static int customerID = 0;

    //Constructor
    LogInWindow() {

        //Panel GUI
        panelLog = new JPanel();
        panelLog.setOpaque(true);
        panelLog.setBackground(c);
        panelLog.setLayout(null);

        //New window dialog (frame)
        LogInDialog = new JDialog(frame, "Log In", true);
        LogInDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        LogInDialog.setLocation(x + 300, y + 125);
        LogInDialog.setResizable(false);
        LogInDialog.setSize(400, 250);
        LogInDialog.add(panelLog);

        //Username GUI
        Username = new JLabel("Username:");
        Username.setBounds(10, 10, 120, 30);
        panelLog.add(Username);
        textUsername = new JTextField();
        textUsername.setBounds(100, 10, 200, 30);
        textUsername.setHorizontalAlignment(JTextField.CENTER);
        panelLog.add(textUsername);

        //Password GUI
        Password = new JLabel("Password:");
        Password.setBounds(10, 60, 120, 30);
        panelLog.add(Password);
        textPassword = new JPasswordField();
        textPassword.setBounds(100, 60, 200, 30);
        textPassword.setHorizontalAlignment(JPasswordField.CENTER);
        panelLog.add(textPassword);

        //Show - Hide password button GUI
        buttonPassword = new JToggleButton("Show");
        buttonPassword.setBounds(295, 63, 60, 25);
        panelLog.add(buttonPassword);

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

        //Log In button GUI
        buttonLog = new JButton("Log In");
        buttonLog.setBounds(75, 125, 250, 40);
        buttonLog.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonLog.addActionListener(this);
        panelLog.add(buttonLog);

        UIManager.put("OptionPane.background", c);
        UIManager.put("Panel.background", c);

        //Dialog window visibility
        LogInDialog.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == buttonLog) {
            if (LogInTextIsValid()) {
                LogInDialog.dispose();
                frame.dispose();
                @SuppressWarnings("unused")
                FoodWindow newWindow = new FoodWindow(customerID);
            }
            else
                JOptionPane.showMessageDialog(null, "The credentials are wrong!", "ERROR", JOptionPane.WARNING_MESSAGE);
        }
    }

    public boolean LogInTextIsValid() {

        //declarations
        boolean isValid = true;
        char[] passwordChars = textPassword.getPassword();
        String password = new String(passwordChars);
        String username = textUsername.getText();

        //Connecting to database to check username and password text fields
        try {
            if (!textUsername.getText().isEmpty() && !(textPassword.getPassword().length < 8)) {
                //SQL query and excecution
                String query = "SELECT * FROM Customers";
                DB_Connection.st = DB_Connection.con.createStatement();
                DB_Connection.rs = DB_Connection.st.executeQuery(query);
                isValid = false;

                while (DB_Connection.rs.next()) {
                    String usernameString = DB_Connection.rs.getString("username");
                    String passwordString = DB_Connection.rs.getString("password");
                    if (username.equals(usernameString) && password.equals(passwordString)) {
                        isValid = true;
                        customerID = DB_Connection.rs.getInt("id");
                        if (customerID <= 0)
                            System.exit(0);
                        break;
                    }
                }

                DB_Connection.rs.close();
                DB_Connection.st.close();
                
            } else {
                isValid = false;
            }
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }

        return isValid;
    }
}
