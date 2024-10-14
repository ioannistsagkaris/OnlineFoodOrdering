import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.*;


public class FoodWindow extends StartingWindow {
    
    //declarations
    protected static JFrame frameFood;
    protected static JPanel panelFood, panelFoodBar, panelMenu;
    private static JScrollPane scrollPanel;
    private static JLabel logoImage;
    private static ImageIcon imageLogo, cartIcon, pizzaIcon, meatIcon, pastaIcon, homecookIcon, sweetsIcon, fastfoodIcon, burgerIcon, friesIcon;
    protected static JButton cartButton, pizzaButton, meatButton, pastaButton, homecookButton, sweetsButton, fastfoodButton, burgerButton, friesButton;
    protected static int customerID;
    protected static boolean flag = false;

    //constructor
    FoodWindow(int id) {

        customerID = id;

        //Panel GUI
        panelFood = new JPanel();
        panelFood.setOpaque(true);
        panelFood.setBackground(c);
        panelFood.setLayout(null);

        //Frame GUI
        frameFood = new JFrame("iFood");
        frameFood.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameFood.setResizable(false);
        frameFood.setSize(1000, 700);
        frameFood.setLocation(x, y);
        frameFood.add(panelFood);

        //Logo GUI
        imageLogo = new ImageIcon("./icons/ifood-mini.png");
        logoImage = new JLabel(imageLogo);
        logoImage.setBounds(0, 0, 100, 75);
        panelFood.add(logoImage);

        //Cart GUI
        cartIcon = new ImageIcon("./icons/cart-icon.png");
        cartButton = new JButton(cartIcon);
        cartButton.setBounds(930, 13, 50, 50);
        cartButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cartButton.setOpaque(false);
        cartButton.setContentAreaFilled(false);
        cartButton.setFocusPainted(false);
        cartButton.setBorderPainted(false);
        cartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                @SuppressWarnings("unused")
                CartWindow newWindow = new CartWindow();
            }
        });
        panelFood.add(cartButton);

        //Food bar panel GUI
        panelFoodBar = new JPanel();
        panelFoodBar.setOpaque(true);
        panelFoodBar.setBackground(c);
        panelFoodBar.setLayout(new BoxLayout(panelFoodBar, BoxLayout.X_AXIS));
        panelFoodBar.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 50)));
        panelFoodBar.setBounds(250, 13, 500, 48);
        panelFood.add(panelFoodBar);

        //Pizza button GUI
        pizzaIcon = new ImageIcon("./icons/pizza-icon.png");
        pizzaButton = new JButton(pizzaIcon);
        pizzaButton.setBorderPainted(false);
        pizzaButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pizzaButton.addActionListener(new ButtonPressed());
        panelFoodBar.add(pizzaButton);

        //Meat button GUI
        meatIcon = new ImageIcon("./icons/meat-icon.png");
        meatButton = new JButton(meatIcon);
        meatButton.setBorderPainted(false);
        meatButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        meatButton.addActionListener(new ButtonPressed());
        panelFoodBar.add(meatButton);

        //Pasta button GUI
        pastaIcon = new ImageIcon("./icons/pasta-icon.png");
        pastaButton = new JButton(pastaIcon);
        pastaButton.setBorderPainted(false);
        pastaButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pastaButton.addActionListener(new ButtonPressed());
        panelFoodBar.add(pastaButton);

        //Fast food button GUI
        fastfoodIcon = new ImageIcon("./icons/fastfood-icon.png");
        fastfoodButton = new JButton(fastfoodIcon);
        fastfoodButton.setBorderPainted(false);
        fastfoodButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        fastfoodButton.addActionListener(new ButtonPressed());
        panelFoodBar.add(fastfoodButton);

        //Burger button GUI
        burgerIcon = new ImageIcon("./icons/burger-icon.png");
        burgerButton = new JButton(burgerIcon);
        burgerButton.setBorderPainted(false);
        burgerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        burgerButton.addActionListener(new ButtonPressed());
        panelFoodBar.add(burgerButton);

        //Fries button GUI
        friesIcon = new ImageIcon("./icons/fries-icon.png");
        friesButton = new JButton(friesIcon);
        friesButton.setBorderPainted(false);
        friesButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        friesButton.addActionListener(new ButtonPressed());
        panelFoodBar.add(friesButton);

        //Sweets button GUI
        sweetsIcon = new ImageIcon("./icons/sweets-icon.png");
        sweetsButton = new JButton(sweetsIcon);
        sweetsButton.setBorderPainted(false);
        sweetsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        sweetsButton.addActionListener(new ButtonPressed());
        panelFoodBar.add(sweetsButton);

        //Homecook button GUI
        homecookIcon = new ImageIcon("./icons/homecook-icon.png");
        homecookButton = new JButton(homecookIcon);
        homecookButton.setBorderPainted(false);
        homecookButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        homecookButton.addActionListener(new ButtonPressed());
        panelFoodBar.add(homecookButton);

        //Menu panel GUI
        panelMenu = new JPanel();
        panelMenu.setOpaque(true);
        panelMenu.setBackground(c);
        panelMenu.setLayout(new GridLayout(20, 3, 20, 25));
        panelMenu.setBounds(50, 80, 900, 3000);

        //Scrolling panel GUI
        scrollPanel = new JScrollPane(panelMenu);
        scrollPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 70)));
        scrollPanel.getVerticalScrollBar().setUnitIncrement(16);
        scrollPanel.setBounds(50, 80, 900, 570);
        panelFood.add(scrollPanel);

        //Connecting to database to collect and print to the app all the foods
        try {
            String query = "SELECT item, images FROM Menus JOIN Images ON Menus.image_id = Images.id GROUP BY Menus.item";
            DB_Connection.st = DB_Connection.con.createStatement();
            DB_Connection.rs = DB_Connection.st.executeQuery(query);

            while (DB_Connection.rs.next()) {
                String item = DB_Connection.rs.getString("item");
                InputStream inStream = DB_Connection.rs.getBinaryStream("images");
                BufferedImage buffImage = ImageIO.read(inStream);
                ImageIcon icon = new ImageIcon(buffImage);
                JButton btn = new JButton(item, icon);

                btn.setOpaque(false);
                btn.setContentAreaFilled(false);
                btn.setFocusPainted(false);
                btn.setBorderPainted(false);
                btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                btn.setHorizontalTextPosition(JLabel.CENTER);
                btn.setVerticalTextPosition(JLabel.BOTTOM);

                btn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        @SuppressWarnings("unused")
                        RestaurantWindow newWindow = new RestaurantWindow(item, customerID);
                    }
                });

                panelMenu.add(btn);
            }

            DB_Connection.rs.close();
            DB_Connection.st.close();

        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }

        //Frame visibility
        frameFood.setVisible(true);
    }
}

//Showing foods based on the button that was pressed (connection with database for taking info for the food)
class ButtonPressed implements ActionListener {
    public void actionPerformed(ActionEvent event) {

        try {
            String query = "";
            DB_Connection.st = DB_Connection.con.createStatement();
            FoodWindow.panelMenu.setLayout(new GridLayout(3, 3, 20, 25));

            if (event.getSource() == FoodWindow.pizzaButton)
                query = "SELECT item, images FROM Menus JOIN Images ON Menus.image_id = Images.id WHERE Menus.item LIKE '%pizza%' GROUP BY Menus.item";
            else if (event.getSource() == FoodWindow.meatButton)
                query = "SELECT item, images FROM Menus JOIN Images ON Menus.image_id = Images.id WHERE (Menus.item NOT LIKE '%burger%' AND Menus.item NOT LIKE '%club%' AND Menus.item NOT LIKE '%BBQ%' AND Menus.item NOT LIKE '%fries%') AND (Menus.item LIKE '%chicken%' OR Menus.item LIKE '%pork%' OR Menus.item LIKE '%meatballs%') GROUP BY Menus.item";
            else if (event.getSource() == FoodWindow.pastaButton)
                query = "SELECT item, images FROM Menus JOIN Images ON Menus.image_id = Images.id WHERE Menus.item LIKE '%spaghetti%' OR Menus.item LIKE '%linguine%' OR Menus.item LIKE '%penne%' GROUP BY Menus.item";
            else if (event.getSource() == FoodWindow.fastfoodButton)
                query = "SELECT item, images FROM Menus JOIN Images ON Menus.image_id = Images.id WHERE Menus.item LIKE '%club%' GROUP BY Menus.item";
            else if (event.getSource() == FoodWindow.burgerButton)
                query = "SELECT item, images FROM Menus JOIN Images ON Menus.image_id = Images.id WHERE Menus.item LIKE '%burger%' GROUP BY Menus.item";
            else if (event.getSource() == FoodWindow.friesButton)
                query = "SELECT item, images FROM Menus JOIN Images ON Menus.image_id = Images.id WHERE (Menus.item NOT LIKE '%club%') AND (Menus.item LIKE '%fries%') GROUP BY Menus.item";
            else if (event.getSource() == FoodWindow.sweetsButton)
                query = "SELECT item, images FROM Menus JOIN Images ON Menus.image_id = Images.id WHERE Menus.restaurant_id = 6 GROUP BY Menus.item";
            else if (event.getSource() == FoodWindow.homecookButton)
                query = "SELECT item, images FROM Menus JOIN Images ON Menus.image_id = Images.id WHERE Menus.restaurant_id = 5 AND Menus.item NOT LIKE '%fries%' GROUP BY Menus.item";

            DB_Connection.rs = DB_Connection.st.executeQuery(query);
            FoodWindow.panelMenu.removeAll();
            FoodWindow.panelMenu.updateUI();
            
            while (DB_Connection.rs.next()) {
                String item = DB_Connection.rs.getString("item");
                InputStream inStream = DB_Connection.rs.getBinaryStream("images");
                BufferedImage buffImage = ImageIO.read(inStream);
                ImageIcon icon = new ImageIcon(buffImage);
                JButton btn = new JButton(item, icon);

                btn.setOpaque(false);
                btn.setContentAreaFilled(false);
                btn.setFocusPainted(false);
                btn.setBorderPainted(false);
                btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                btn.setHorizontalTextPosition(JLabel.CENTER);
                btn.setVerticalTextPosition(JLabel.BOTTOM);

                btn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        @SuppressWarnings("unused")
                        RestaurantWindow newWindow = new RestaurantWindow(item, FoodWindow.customerID);
                    }
                });

                FoodWindow.panelMenu.add(btn);
            }

            DB_Connection.rs.close();
            DB_Connection.st.close();

        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }
    }
}
