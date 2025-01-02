package org.example;
import javax.swing.*;
import java.awt.*;

class PriceHistory extends JPanel implements Observer {
    private JTextArea historyArea;

    public PriceHistory() {
        setBackground(Color.BLACK);
        historyArea = new JTextArea(10, 20);
        historyArea.setForeground(Color.WHITE);
        historyArea.setBackground(Color.DARK_GRAY);
        historyArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        historyArea.setEditable(false);
        setLayout(new BorderLayout());
        add(new JScrollPane(historyArea), BorderLayout.CENTER);
    }

    @Override
    public void update(double price) {
        historyArea.append(String.format("Price updated to: $%.2f%n", price));
    }
}
