import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class RestaurantWindow {
    
    //declarations
    private static JPanel panelRestaurant, contentPanel;
    private static JDialog RestaurantDialog;
    private static JScrollPane panelScroll;
    private static JLabel Quantity, QuantityNum;
    private static JButton decreaseBtn, increaseBtn;
    private static int number;
    protected static int orderQuantity = 0;

    //Constructor
    RestaurantWindow(String item, int customerID) {

        number = 1;

        //Panel GUI
        panelRestaurant = new JPanel();
        panelRestaurant.setOpaque(true);
        panelRestaurant.setBackground(StartingWindow.c);
        panelRestaurant.setLayout(null);

        //New window dialog (frame)
        RestaurantDialog = new JDialog(FoodWindow.frameFood, "Restaurants", true);
        RestaurantDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        RestaurantDialog.setLocation(StartingWindow.x + 300, StartingWindow.y + 125);
        RestaurantDialog.setResizable(false);
        RestaurantDialog.setSize(400, 270);
        RestaurantDialog.add(panelRestaurant);

        //Quantity GUI
        Quantity = new JLabel("Quantity:");
        Quantity.setBounds(10, 0, 60, 40);
        panelRestaurant.add(Quantity);
        QuantityNum = new JLabel(Integer.toString(number));
        QuantityNum.setBounds(110, 0, 20, 40);
        panelRestaurant.add(QuantityNum);

        //Decrease and Increase buttons GUI
        decreaseBtn = new JButton("-");
        increaseBtn = new JButton("+");
        decreaseBtn.setBounds(80, 10, 20, 20);
        increaseBtn.setBounds(130, 10, 20, 20);

        decreaseBtn.setOpaque(false);
        decreaseBtn.setContentAreaFilled(false);
        decreaseBtn.setFocusPainted(false);

        decreaseBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        decreaseBtn.setHorizontalTextPosition(JLabel.CENTER);
        decreaseBtn.setVerticalTextPosition(JLabel.CENTER);

        increaseBtn.setOpaque(false);
        increaseBtn.setContentAreaFilled(false);
        increaseBtn.setFocusPainted(false);

        increaseBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        increaseBtn.setHorizontalTextPosition(JLabel.CENTER);
        increaseBtn.setVerticalTextPosition(JLabel.CENTER);

        decreaseBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (number > 1) {
                    number--;
                    QuantityNum.setText(Integer.toString(number));
                }
            }
        });
        increaseBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (number < 99) {
                    number++;
                    QuantityNum.setText(Integer.toString(number));
                }
            }
        });
        panelRestaurant.add(decreaseBtn);
        panelRestaurant.add(increaseBtn);

        //Scrolling panel GUI
        contentPanel = new JPanel(new GridLayout(4, 1));
        panelScroll = new JScrollPane(contentPanel);
        panelScroll.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 70)));
        panelScroll.getVerticalScrollBar().setUnitIncrement(10);
        panelScroll.setBounds(10, 30, 380, 200);
        panelRestaurant.add(panelScroll);

        //Showing restaurants prices and menu ids based on the button that was pressed (connection with database for taking info for the restaurants, price, id)
        try {
            String query = "SELECT name, price, Menus.id FROM Menus JOIN Restaurants ON Menus.restaurant_id = Restaurants.id WHERE Menus.item = '" + item + "'";
            DB_Connection.st = DB_Connection.con.createStatement();
            DB_Connection.rs = DB_Connection.st.executeQuery(query);

            while (DB_Connection.rs.next()) {
                String item1 = DB_Connection.rs.getString("name");
                int item2 = DB_Connection.rs.getInt("price");
                int item3 = DB_Connection.rs.getInt("id");
                String finalItem = item1 + " - " + item2 + "€";
                JButton btn = new JButton(finalItem);

                btn.setOpaque(false);
                btn.setContentAreaFilled(false);
                btn.setFocusPainted(false);
                btn.setBorderPainted(true);
                btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                btn.setHorizontalTextPosition(JLabel.CENTER);
                btn.setVerticalTextPosition(JLabel.CENTER);

                btn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        int totalprice = item2 * number;
                        OrderItems(customerID, item3, number, totalprice);
                        RestaurantDialog.dispose();
                    }
                });

                contentPanel.add(btn);
            }

            DB_Connection.rs.close();
            DB_Connection.st.close();

        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }

        //Dialog window visibility
        RestaurantDialog.setVisible(true);
    }

    //Insert order info to database
    public void OrderItems(int customerID, int id, int quantity, int totalprice) {
        try {
            String query = "INSERT INTO Orders (customer_id, menu_id, quantity, total_price)" + "VALUES (" + customerID + ", " + id + ", " + quantity + ", " + totalprice + ")";
            DB_Connection.st = DB_Connection.con.createStatement();
            DB_Connection.st.executeUpdate(query);
            DB_Connection.st.close();
            orderQuantity++;
            FoodWindow.flag = true;
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }
    }
}
