package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import java.util.Objects;


public class PizzaBillGUI extends JFrame {

    private JComboBox<Pizza> pizzaBox;
    private JCheckBox cheeseBox, toppingsBox, drinkBox;
    private JTextArea billArea;
    private JLabel imageLabel;

    public PizzaBillGUI() {
        setTitle("Pizza Bill Generator");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set layout
        setLayout(new BorderLayout());

        // Background Panel with custom paintComponent
        JPanel backgroundPanel = new JPanel() {
            Image backgroundImage = new ImageIcon("images/blurred_background.png").getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(null);
        add(backgroundPanel);

        JLabel pizzaLabel = new JLabel("Select Pizza:");
        pizzaLabel.setBounds(30, 20, 100, 25);
        backgroundPanel.add(pizzaLabel);

        pizzaBox = new JComboBox<>(Pizza.values());
        pizzaBox.setBounds(140, 20, 150, 25);
        backgroundPanel.add(pizzaBox);

        cheeseBox = new JCheckBox("Extra Cheese (₹50)");
        cheeseBox.setBounds(30, 60, 200, 25);
        backgroundPanel.add(cheeseBox);

        toppingsBox = new JCheckBox("Extra Toppings (₹70)");
        toppingsBox.setBounds(30, 90, 200, 25);
        backgroundPanel.add(toppingsBox);

        drinkBox = new JCheckBox("Cold Drink (₹40)");
        drinkBox.setBounds(30, 120, 200, 25);
        backgroundPanel.add(drinkBox);

        JButton generateBtn = new JButton("Generate Bill");
        generateBtn.setBounds(30, 160, 130, 30);
        backgroundPanel.add(generateBtn);

        JButton resetBtn = new JButton("Reset");
        resetBtn.setBounds(180, 160, 100, 30);
        backgroundPanel.add(resetBtn);

        billArea = new JTextArea();
        billArea.setBounds(300, 40, 300, 150);
        billArea.setEditable(false);
        backgroundPanel.add(billArea);

        imageLabel = new JLabel();
        imageLabel.setBounds(300, 200, 300, 250);
        backgroundPanel.add(imageLabel);

        // Action Listeners
        generateBtn.addActionListener(e -> generateBill());
        resetBtn.addActionListener(e -> resetForm());

        setVisible(true);
    }

    private void generateBill() {
        Pizza selectedPizza = (Pizza) pizzaBox.getSelectedItem();
        boolean cheese = cheeseBox.isSelected();
        boolean toppings = toppingsBox.isSelected();
        boolean drink = drinkBox.isSelected();

        Order order = new Order(selectedPizza, cheese, toppings, drink);
        int total = PriceCalculator.calculateTotal(order);

        StringBuilder bill = new StringBuilder("----- PIZZA BILL -----\n");
        bill.append("Pizza: ").append(selectedPizza.name()).append(" - ₹").append(selectedPizza.getBasePrice()).append("\n");
        if (cheese) bill.append("Extra Cheese: ₹50\n");
        if (toppings) bill.append("Extra Toppings: ₹70\n");
        if (drink) bill.append("Cold Drink: ₹40\n");
        bill.append("---------------------------\n");
        bill.append("Total: ₹").append(total);

        billArea.setText(bill.toString());

        // Load image
        String imagePath = "images/" + selectedPizza.name().toLowerCase() + ".jpg";
        ImageIcon icon = new ImageIcon(imagePath);
        Image img = icon.getImage().getScaledInstance(280, 200, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(img));
    }

    private void resetForm() {
        pizzaBox.setSelectedIndex(0);
        cheeseBox.setSelected(false);
        toppingsBox.setSelected(false);
        drinkBox.setSelected(false);
        billArea.setText("");
        imageLabel.setIcon(null);
    }
}
