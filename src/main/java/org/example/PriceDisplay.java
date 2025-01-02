package org.example;
import javax.swing.*;
import java.awt.*;

class PriceDisplay extends JPanel implements Observer {
    private JLabel priceLabel;

    public PriceDisplay() {
        setBackground(Color.BLACK);
        priceLabel = new JLabel("Current Price: $0.00");
        priceLabel.setForeground(Color.ORANGE);
        priceLabel.setFont(new Font("Arial", Font.BOLD, 16));
        setLayout(new FlowLayout());
        add(priceLabel);
    }

    @Override
    public void update(double price) {
        priceLabel.setText(String.format("Current Price: $%.2f", price));
    }
}
