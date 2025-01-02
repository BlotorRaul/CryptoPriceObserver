package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String[] args) {
        CryptoSubject crypto = new CryptoSubject();

        JFrame frame = new JFrame("Crypto Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 700);
        frame.setLayout(new GridBagLayout());
        frame.getContentPane().setBackground(Color.BLACK);

        // Pretul initial
        double purchasePrice = 30 + new Random().nextDouble() * 25;

        // Create Observers
        PriceDisplay display = new PriceDisplay();
        PriceAlert alert = new PriceAlert(80.0);
        PriceHistory history = new PriceHistory();
        PriceGraph graph = new PriceGraph();
        PriceStats stats = new PriceStats();
        PriceBelowPurchaseAlert belowPurchaseAlert = new PriceBelowPurchaseAlert(purchasePrice);

        // Attach Observers to Subject
        crypto.attach(display);
        crypto.attach(alert);
        crypto.attach(history);
        crypto.attach(graph);
        crypto.attach(stats);
        crypto.attach(belowPurchaseAlert);

        // Define layout constraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Add Price Stats (row 0, column 0)
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 0.2;
        gbc.weighty = 0.1;
        frame.add(stats, gbc);

        // Add graph (row 0, column 1, spanning 2 columns)
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2; //Componenta se extinde pe 2 coloane
        gbc.weightx = 0.8;
        gbc.weighty = 0.7;
        JScrollPane scrollPane = new JScrollPane(graph, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(800, 200));
        frame.add(scrollPane, gbc);

        // Add Price History (row 1 & 2, column 0 spanning 2 rows)
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 2; //Componenta se extinde pe 2 coloane
        gbc.gridwidth = 1;
        gbc.weightx = 0.33;
        gbc.weighty = 0.6;
        frame.add(history, gbc);

        // Add Price Display (row 1, column 1)
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0.33;
        gbc.weighty = 0.15;
        frame.add(display, gbc);

        // Add Price Alert (row 1, column 2)
        gbc.gridx = 2;
        gbc.gridy = 1;
        frame.add(alert, gbc);

        // Add Price Below Purchase Alert (row 2, column 2)
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.33;
        frame.add(belowPurchaseAlert, gbc);

        // Simulate price changes
        Timer timer = new Timer();
        Random random = new Random();
        timer.scheduleAtFixedRate(new TimerTask() {
            double currentPrice = purchasePrice; // Set initial price to purchase price

            @Override
            public void run() {
                double change = random.nextDouble() * 5; // Valoare aleatoare pentru fluctuatie (0 - 5)

                // Random decide dacă pretul creste sau scade
                if (random.nextBoolean()) {
                    currentPrice += change; // Creste pretul
                } else {
                    currentPrice -= change; // Scade pretul
                }

                // daca pretul scade sub zero
                if (currentPrice < 0) {
                    currentPrice = 0; // Preturile negative nu sunt permise
                }

                // actualizare preț în Subject -> aici se declanseaza totul
                crypto.setPrice(currentPrice);
            }
        }, 0, 1000); // 1 secunda


        frame.setVisible(true);
    }
}
