package org.example;
import javax.swing.*;
import java.awt.*;

class PriceAlert extends JPanel implements Observer {
    private JLabel alertLabel;
    private double threshold;

    public PriceAlert(double threshold) {
        setBackground(Color.BLACK);
        this.threshold = threshold;
        alertLabel = new JLabel("No alerts yet.");
        alertLabel.setForeground(Color.RED);
        alertLabel.setFont(new Font("Arial", Font.BOLD, 16));
        setLayout(new FlowLayout());
        add(alertLabel);
    }

    @Override
    public void update(double price) {
        if (price > threshold) {
            alertLabel.setText(String.format("ALERT: Price exceeded $%.2f!", threshold));
        } else {
            alertLabel.setText("No alerts yet.");
        }
    }
}