package org.example;

/**
 * Defineste un contract standard, ceea ce permite Subject sa notifice observatorii fara sa cunoasca detaliile acestora
 *
 */
interface Observer {
    void update(double price);
}