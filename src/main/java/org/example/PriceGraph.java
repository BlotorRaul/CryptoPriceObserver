package org.example;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
class PriceGraph extends JPanel implements Observer {
    private List<Double> prices = new ArrayList<>();
    private int pointSpacing = 20;
    private int fixedHeight = 200;

    public PriceGraph() {
        setBackground(Color.BLACK);
    }

    @Override
    public void update(double price) {
        prices.add(price);
        // fixam dimensiunea doar pe latime pentru a permite derularea orizontala
        setPreferredSize(new Dimension(prices.size() * pointSpacing, fixedHeight));
        revalidate();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (prices.isEmpty()) {
            return;
        }

        int graphHeight = fixedHeight - 20;
        double maxPrice = prices.stream().max(Double::compareTo).orElse(1.0);
        double minPrice = prices.stream().min(Double::compareTo).orElse(0.0);

        g.setColor(Color.ORANGE);

        /*
        maxPrice- minPrice reprezinta intervalul total
        impartind diferenta(prices.get(i) - minPrice) la acest interval, obtinem pozitia relativa a
        pretului curent fata de minim si maxim( normalizare intre 0 si 1)
        -rezultatul normalizat din pasul anterior(intre 0 si 1) este multiplicat cu inaltimea graficului

        valoare peste min-> normalizare intre min si max -> scalare la dimensiune grafic

        pentru a desena graficul astfel incat preturile mari sa fie sus si cele mici jos, inversam valoarea
        obtinuta( in java swing Y incepe de la 0 (sus) si creste pe masura ce cobori
         */

        for (int i = 0; i < prices.size() - 1; i++) {
            int x1 = i * pointSpacing;
            int y1 = graphHeight - (int) ((prices.get(i) - minPrice) / (maxPrice - minPrice) * graphHeight);
            int x2 = (i + 1) * pointSpacing;
            int y2 = graphHeight - (int) ((prices.get(i + 1) - minPrice) / (maxPrice - minPrice) * graphHeight);

            g.drawLine(x1, y1, x2, y2);
        }
    }
}
