import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class CartWindow {
    
    //declarations
    private static JPanel panelCart, contentPanel;
    private static JDialog CartDialog;
    private static JScrollPane panelScroll;
    private static JButton checkoutBtn;
    private static JLabel items, price, totalPrice, totalNumber;
    protected static int total = 0;

    //Constructor
    CartWindow() {

        //Panel GUI
        panelCart = new JPanel();
        panelCart.setOpaque(true);
        panelCart.setBackground(StartingWindow.c);
        panelCart.setLayout(null);

        //New window dialog (frame)
        CartDialog = new JDialog(FoodWindow.frameFood, "Cart", true);
        CartDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        CartDialog.setLocation(StartingWindow.x + 345, StartingWindow.y + 100);
        CartDialog.setResizable(false);
        CartDialog.setSize(300, 500);
        CartDialog.add(panelCart);

        //Scrolling panel GUI
        contentPanel = new JPanel(null);
        panelScroll = new JScrollPane(contentPanel);
        panelScroll.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 70)));
        panelScroll.getVerticalScrollBar().setUnitIncrement(10);
        panelScroll.setBounds(10, 30, 280, 380);
        panelCart.add(panelScroll);

        //Items label GUI
        items = new JLabel("Items");
        items.setBounds(20, 15, 35, 10);
        panelCart.add(items);

        //Price label GUI
        price = new JLabel("Price");
        price.setBounds(250, 15, 35, 10);
        panelCart.add(price);

        //Price label GUI
        totalPrice = new JLabel("Total Price:");
        totalPrice.setBounds(10, 410, 75, 20);
        panelCart.add(totalPrice);
        totalNumber = new JLabel();
        totalNumber.setText(Integer.toString(total) + "€");
        totalNumber.setBounds(260, 410, 50, 20);
        panelCart.add(totalNumber);

        //Checkout button
        checkoutBtn = new JButton("Checkout");
        checkoutBtn.setBounds(5, 430, 290, 40);
        checkoutBtn.setOpaque(false);
        checkoutBtn.setContentAreaFilled(false);
        checkoutBtn.setFocusPainted(false);
        checkoutBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        checkoutBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (total > 0) {
                    CartDialog.dispose();
                    @SuppressWarnings("unused")
                    PaymentWindow newWindow = new PaymentWindow();
                } else {
                    JOptionPane.showMessageDialog(null, "Your total price is 0€. You cannot continue!", "ERROR", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        panelCart.add(checkoutBtn);

        try {
            String query = "SELECT item, total_price FROM Orders JOIN Menus ON Orders.menu_id = Menus.id ORDER BY Orders.id DESC LIMIT " + RestaurantWindow.orderQuantity;
            DB_Connection.st = DB_Connection.con.createStatement();
            DB_Connection.rs = DB_Connection.st.executeQuery(query);

            int y = 5;
            int counter = RestaurantWindow.orderQuantity;

            while (DB_Connection.rs.next()) {

                String item1 = DB_Connection.rs.getString("item");
                int temp = DB_Connection.rs.getInt("total_price");
                String item2 = Integer.toString(temp);

                JLabel label1 = new JLabel(item1);
                JLabel label2 = new JLabel(item2 + "€");

                label1.setBounds(5, y, 260, 20);
                label2.setBounds(245, y, 40, 20);

                contentPanel.add(label1);
                contentPanel.add(label2);

                if (counter == RestaurantWindow.orderQuantity && FoodWindow.flag == true) {
                    total += temp;
                    totalNumber.setText(Integer.toString(total) + "€");
                    FoodWindow.flag = false;
                }

                counter--;
                y += 20;
            }

            DB_Connection.rs.close();
            DB_Connection.st.close();

        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }

        //Dialog window visibility
        CartDialog.setVisible(true);
    }
}
