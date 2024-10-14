import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class StartingWindow {

    //declarations
    private static JPanel panel;
    protected static JFrame frame;
    protected static JButton SignUpButton, LogInButton;
    private static ImageIcon image;
    private static JLabel logo;
    protected static Color c = new Color(137, 147, 158);
    protected static int x = 0;
    protected static int y = 0;
    private static Dimension dimension;

    public static void main(String[] args) {

        //Panel GUI
        panel = new JPanel();
        panel.setOpaque(true);
        panel.setBackground(c);
        panel.setLayout(null);

        //Frame GUI
        frame = new JFrame("iFood");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(1000, 700);
        dimension = Toolkit.getDefaultToolkit().getScreenSize();
        x = (dimension.width - 1000) / 2;
        y = (dimension.height - 700) / 2;
        frame.setLocation(x, y);
        frame.add(panel);

        //Image GUI
        image = new ImageIcon("./icons/ifood.png");
        image.setImage(image.getImage().getScaledInstance(600, 400, Image.SCALE_DEFAULT));
        logo = new JLabel(image);
        logo.setBounds(200, 20, 600, 400);
        panel.add(logo);

        //Database connection
        @SuppressWarnings("unused")
        DB_Connection newConnection = new DB_Connection();

        //Sign Up button GUI (action listener call to class SignInPressed)
        SignUpButton = new JButton("Sign Up");
        SignUpButton.setBounds(200, 430, 300, 35);
        SignUpButton.setFocusPainted(false);
        SignUpButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        ActionListener sign = new SignUpPressed();
        SignUpButton.addActionListener(sign);
        panel.add(SignUpButton);

        //Log In button GUI (action listener call to class LogInPressed)
        LogInButton = new JButton("Log In");
        LogInButton.setBounds(500, 430, 300, 35);
        LogInButton.setFocusPainted(false);
        LogInButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        ActionListener log = new LogInPressed();
        LogInButton.addActionListener(log);
        panel.add(LogInButton);

        //Frame visibility
        frame.setVisible(true);
    }
}


//calling the Sign up window to appear
class SignUpPressed extends StartingWindow implements ActionListener {

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == SignUpButton) {
            @SuppressWarnings("unused")
            SignUpWindow newWindow = new SignUpWindow();
        }
    }
}


//calling the Log in window to appear
class LogInPressed extends StartingWindow implements ActionListener {

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == LogInButton) {
            @SuppressWarnings("unused")
            LogInWindow newWindow = new LogInWindow();
        }
    }
}
