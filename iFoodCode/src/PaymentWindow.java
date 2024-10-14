import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;


public class PaymentWindow {

    //declarations
    private static JPanel panelPayment;
    private static JDialog PaymentDialog;
    private static JRadioButton btnCash, btnCard;
    private static ButtonGroup group;
    private static JButton btnNext;
    private static JLabel method;

    //Constructor
    PaymentWindow() {

        //Panel GUI
        panelPayment = new JPanel();
        panelPayment.setOpaque(true);
        panelPayment.setBackground(StartingWindow.c);
        panelPayment.setLayout(null);

        //New window dialog (frame)
        PaymentDialog = new JDialog(FoodWindow.frameFood, "Payment", true);
        PaymentDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        PaymentDialog.setLocation(StartingWindow.x + 345, StartingWindow.y + 125);
        PaymentDialog.setResizable(false);
        PaymentDialog.setSize(300, 250);
        PaymentDialog.add(panelPayment);

        //Label GUI
        method = new JLabel("Please choose a payment method:");
        method.setBounds(40, 25, 220, 30);
        panelPayment.add(method);

        //Cash button GUI
        btnCash = new JRadioButton("Cash");
        btnCash.setBounds(60, 70, 65, 30);
        btnCash.setFocusPainted(false);
        btnCash.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnCash.setSelected(true);

        //Credit card button GUI
        btnCard = new JRadioButton("Credit Card");
        btnCard.setBounds(60, 110, 105, 30);
        btnCard.setFocusPainted(false);
        btnCard.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        //Group the two buttons
        group = new ButtonGroup();
        group.add(btnCash);
        group.add(btnCard);
        panelPayment.add(btnCash);
        panelPayment.add(btnCard);

        //Next button GUI
        btnNext = new JButton("Next");
        btnNext.setBounds(200, 180, 90, 35);
        btnNext.setOpaque(false);
        btnNext.setContentAreaFilled(false);
        btnNext.setFocusPainted(false);
        btnNext.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnNext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (btnCash.isSelected()) {
                    OrderSuccessful();
                } else if (btnCard.isSelected()) {
                    CardInfo();
                }
            }
        });
        panelPayment.add(btnNext);

        //Dialog window visibility
        PaymentDialog.setVisible(true);
    }

    public void OrderSuccessful() {

        ImageIcon icon = new ImageIcon("./icons/check-icon.png");

        JLabel label = new JLabel(icon);
        JLabel text = new JLabel("The order was successful!");

        panelPayment.removeAll();

        label.setBounds(110, 30, 80, 80);
        panelPayment.add(label);

        text.setBounds(69, 120, 165, 30);
        panelPayment.add(text);

        panelPayment.updateUI();

        PaymentDialog.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent event) {
                System.exit(0);
            }
        });
    }

    public void CardInfo() {

        JLabel name = new JLabel("Card Name:");
        JLabel number = new JLabel("Card Number:");

        JTextField textName = new JTextField();
        JTextField textNumber = new JTextField();

        JButton next = new JButton("Next");

        panelPayment.removeAll();

        name.setBounds(20, 20, 75, 30);
        panelPayment.add(name);

        number.setBounds(20, 90, 90, 30);
        panelPayment.add(number);

        textName.setBounds(18, 45, 190, 30);
        panelPayment.add(textName);

        textNumber.setBounds(18, 115, 190, 30);
        panelPayment.add(textNumber);

        next.setBounds(200, 180, 90, 35);
        next.setOpaque(false);
        next.setContentAreaFilled(false);
        next.setFocusPainted(false);
        next.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (TextIsValid(textName, textNumber)) {
                    OrderSuccessful();
                } else {
                    JOptionPane.showMessageDialog(null, "The credentials are wrong!", "ERROR", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        panelPayment.add(next);

        panelPayment.updateUI();
    }

    public boolean TextIsValid(JTextField textName, JTextField textNumber) {

        boolean isValid = true;

        //changing border color to all the text borders to default
        textName.setBorder(new LineBorder(Color.WHITE));
        textNumber.setBorder(new LineBorder(Color.WHITE));

        if (textName.getText().isEmpty()) {
            isValid = false;
            textName.setBorder(new LineBorder(Color.RED));
        } else if (!(textName.getText().matches("[a-zA-Z ]+"))) {
            isValid = false;
            textName.setBorder(new LineBorder(Color.RED));
        }

        if (textNumber.getText().isEmpty()) {
            isValid = false;
            textNumber.setBorder(new LineBorder(Color.RED));
        } else if (!(textNumber.getText().matches("(?<=^|[^0-9])[0-9]{16}(?=[^0-9]|$)|[0-9]{4}[-| |_][0-9]{4}[-| |_][0-9]{4}[-| |_][0-9]{4}"))) {
           isValid = false;
            textNumber.setBorder(new LineBorder(Color.RED));
        }

        return isValid;
    }
}
