package org.example;
import javax.swing.*;
import java.awt.*;


// Observer 5: Display price stats (max and min values)
class PriceStats extends JPanel implements Observer {
    private JLabel maxLabel;
    private JLabel minLabel;
    private double maxPrice = Double.MIN_VALUE;
    private double minPrice = Double.MAX_VALUE;

    public PriceStats() {
        setBackground(Color.BLACK);
        maxLabel = new JLabel("Max: $0.00");
        minLabel = new JLabel("Min: $0.00");

        maxLabel.setForeground(Color.YELLOW);
        minLabel.setForeground(Color.YELLOW);

        maxLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        minLabel.setFont(new Font("Arial", Font.PLAIN, 12));

        setLayout(new GridLayout(2, 1));
        add(maxLabel);
        add(minLabel);
    }

    @Override
    public void update(double price) {
        if (price > maxPrice) {
            maxPrice = price;
        }
        if (price < minPrice) {
            minPrice = price;
        }
        maxLabel.setText(String.format("Max: $%.2f", maxPrice));
        minLabel.setText(String.format("Min: $%.2f", minPrice));
    }
}

