package org.example;
import javax.swing.*;
import java.awt.*;
class PriceBelowPurchaseAlert extends JPanel implements Observer {
    private JLabel alertLabel;
    private double purchasePrice;

    public PriceBelowPurchaseAlert(double purchasePrice) {
        this.purchasePrice = purchasePrice;


        alertLabel = new JLabel("No alerts yet.", SwingConstants.CENTER);
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(300, 50));
        alertLabel.setForeground(Color.RED);
        alertLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(alertLabel, BorderLayout.CENTER);
    }

    @Override
    public void update(double price) {
        if (price < purchasePrice) {
            alertLabel.setText(String.format("ALERT: Below purchase ($%.2f)!", purchasePrice));
        } else {
            alertLabel.setText("No alerts yet.");
        }

        // Scalam fontul pentru a ne asigura ca textul incape in layout
        if (alertLabel.getText().length() > 30) {
            alertLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        } else {
            alertLabel.setFont(new Font("Arial", Font.BOLD, 14));
        }
    }
}